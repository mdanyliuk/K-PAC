package com.kolay.controller;

import com.google.gson.*;
import com.kolay.service.KpacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @RequestMapping(path = "/kpacs", method = RequestMethod.GET)
    public String getAllKpacs(Model model) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        model.addAttribute("kpacs", gson.toJson(kpacService.findAllKpacs()));
        return "index";
    }

    class LocalDateAdapter implements JsonSerializer<LocalDate> {

        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }
    }
}
