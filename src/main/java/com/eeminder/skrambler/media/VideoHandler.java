package com.eeminder.skrambler.media;

import com.thebuzzmedia.exiftool.ExifTool;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class VideoHandler extends MediaHandler {

    public VideoHandler(Part p) {
        super(p);
    }

    @Override
    public void generateThumbnail() {
        System.out.print(String.valueOf(this.media));
        String[] args = {
            "ffmpeg",
            "-y","-i",fileName(),
            "-vframes","1",
            "-vf",String.format("scale=%d:%d",320,240),
            new File(path+"/thumb.jpg").getAbsolutePath(),
        };
        execFFmpeg(args);
    }
    private void execFFmpeg(String[] args) {
        ProcessBuilder p = new ProcessBuilder(args);
        p.redirectErrorStream(true);
        p.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("/tmp/vproc")));

        try {
            int code = p.start().waitFor();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catindex.ftlch block
            e.printStackTrace();
        }
    }
}
