package com.eeminder.skrambler.controller;

import com.eeminder.skrambler.media.IMediaHandler;
import com.eeminder.skrambler.media.MediaHandler;
import com.eeminder.skrambler.model.Media;
import com.eeminder.skrambler.model.dao.MediaDAO;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    MediaDAO mediaDAO;

    @RequestMapping("/admin/control")
    public String control_home() {
        return "admin/index";
    }
    @RequestMapping("/admin/media")
    public String media_home() {
        return "admin/media";
    }
    @RequestMapping(value="/admin/upload",method=RequestMethod.POST)
    public String uploadFile(HttpServletRequest request) {
        try {
            Part part = request.getPart("file");
            Media mh = MediaHandler.get(part).processUpload();
            mediaDAO.save(mh);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "uploaded";
    }
}
