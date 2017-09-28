package com.eeminder.skrambler.media;

import com.thebuzzmedia.exiftool.ExifTool;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageHandler extends MediaHandler {

    public ImageHandler(Part p) {
        super(p);
    }

    @Override
    public void generateThumbnail() {

    }
}
