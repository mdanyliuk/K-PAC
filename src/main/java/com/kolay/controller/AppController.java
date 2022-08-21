package com.kolay.controller;

import com.kolay.service.KpacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

    @Autowired
    private KpacService kpacService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", kpacService.findBySetId().get(0).toString()
                + " " + kpacService.findBySetId().get(1).toString());
        return "index";
    }
}
