package com.eeminder.skrambler.controller;

import com.eeminder.skrambler.model.Media;
import com.eeminder.skrambler.model.User;
import com.eeminder.skrambler.model.dao.MediaDAO;
import com.eeminder.skrambler.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class PublicController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    MediaDAO mediaDAO;
    }
    @RequestMapping("/")
    String index() {
        return "index";
    }

    //@RequestMapping("/error")
    String errorPage() {
        return "error";
    }

    @RequestMapping("/media/{id}")
    ModelAndView getMedia(@PathVariable(value="id") String id) {
        ModelAndView mav = new ModelAndView("media");
        Media m = mediaDAO.get(Integer.parseInt(id));
        mav.addObject("media",m);
        return mav;
    }
}
