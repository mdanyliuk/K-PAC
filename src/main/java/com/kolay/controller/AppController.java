package com.kolay.controller;

import com.google.gson.*;
import com.kolay.model.Kpac;
import com.kolay.service.KpacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AppController {

    @Autowired
    private KpacService kpacService;

    @RequestMapping(value = {"/", "/kpacs"}, method = RequestMethod.GET)
    public String getAllKpacs(Model model) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        model.addAttribute("kpacs", gson.toJson(kpacService.findAllKpacs()));
        return "index";
    }

    @RequestMapping(value = {"/newpac"}, method = RequestMethod.GET)
    public String newPacForm(Model model) {
        Kpac newKpac = new Kpac();
        model.addAttribute("kpac", newKpac);
        return "newpac";
    }

    @RequestMapping(value = {"/newpac"}, method = RequestMethod.POST)
    public String savePac(@ModelAttribute("kpac") Kpac kpac, BindingResult result) {
        kpacService.saveKpac(kpac);
        return "redirect:/kpacs/";
    }

    @RequestMapping(value = {"/kpac/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deletePac(@PathVariable("id") Integer id) {
        kpacService.deleteKpacById(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/sets"}, method = RequestMethod.GET)
    public String getAllSets(Model model) {
        Gson gson = new Gson();
        model.addAttribute("sets", gson.toJson(kpacService.findAllSets()));
        return "sets";
    }

    @RequestMapping(value = {"/set/{id}"}, method = RequestMethod.GET)
    public String getPacsOfSet(@PathVariable("id") Integer id, Model model) {
        Gson gson = new Gson();
        model.addAttribute("setName", kpacService.findSetById(id).getTitle());
        model.addAttribute("kpacs", gson.toJson(kpacService.findPacsBySetId(id)));
        return "pacsOfSet";
    }

    class LocalDateAdapter implements JsonSerializer<LocalDate> {

        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }
    }
}
