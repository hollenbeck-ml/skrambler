package com.eeminder.skrambler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AjaxController {

    @PostMapping(value="/testAjax",consumes={"application/x-www-form-urlencoded"},produces={MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody  Map<String,Object> testAjax(@RequestParam Map<String,Object> in) {

        System.out.println(in.toString());
        HashMap ret = new HashMap();
        return ret;
    }
}
