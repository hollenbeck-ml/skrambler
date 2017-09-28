package com.eeminder.skrambler.media;

import com.eeminder.skrambler.model.Media;
import com.eeminder.skrambler.model.dao.MediaDAO;
import com.thebuzzmedia.exiftool.ExifTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Configurable
public abstract class MediaHandler {

    protected Media media;
    protected String path;
    protected String ext;

    public static MediaHandler get(Part upload) {
        if (upload.getContentType().startsWith("image")) {
            return new ImageHandler(upload);
        } else {
            return new VideoHandler(upload);
        }
    }
    public MediaHandler(Part upload) {

        this.media = new Media();
        media.setType(upload.getContentType());
        ext = upload.getSubmittedFileName().substring(upload.getSubmittedFileName().lastIndexOf(".")+1);
        media.setFile("original."+ext);
        path = getUploadPath(media);

        try {
            upload.write(path+"/"+media.getFile());
        } catch(IOException io) {

        }
    }
    public Media processUpload() {
        generateThumbnail();
        setMetaData();
        return media;
    }
    private String getUploadPath(Media media) {
        String path = String.format(
                "%s/%s/",
                "uploads",
                media.getUuid()
        );
        File f = new File(path);
        f.mkdirs();
        return f.getAbsolutePath();
    }

    public abstract void generateThumbnail();
    public void setMetaData() {
        ExifTool eft = new ExifTool();
        File f = new File(path+"/"+media.getFile());
        int width=0,height=0;
        try {
            Map<ExifTool.Tag, String> valueMap = eft.getImageMeta(f, ExifTool.Tag.IMAGE_HEIGHT, ExifTool.Tag.IMAGE_WIDTH);
            width = Integer.parseInt(valueMap.get(ExifTool.Tag.IMAGE_WIDTH));
            height = Integer.parseInt(valueMap.get(ExifTool.Tag.IMAGE_HEIGHT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        media.setWidth(width);
        media.setHeight(height);
    }
    protected String fileName() {
        return path+"/"+media.getFile();
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
