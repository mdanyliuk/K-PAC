package com.kolay.controller;

import com.google.gson.*;
import com.kolay.model.Kpac;
import com.kolay.model.KpacDto;
import com.kolay.model.KsetDto;
import com.kolay.service.KpacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("errorMessage", "");
        return "newpacpage";
    }

    @RequestMapping(value = {"/newpac"}, method = RequestMethod.POST)
    public String savePac(@ModelAttribute("kpac") @Valid Kpac kpac, BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("kpac", kpac);
            model.addAttribute("errorMessage", "Title must not be empty");
            return "newpacpage";
        }
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
        model.addAttribute("kpacs",
                gson.toJson(kpacService.findPacsBySetId(id)));
        return "pacsOfSet";
    }

    @RequestMapping(value = {"/newset"}, method = RequestMethod.GET)
    public String newSetForm(Model model) {
        model.addAttribute("kset", initializeNewKsetDto());
        model.addAttribute("errorMessage", "");
        return "newset";
    }

    private KsetDto initializeNewKsetDto() {
        KsetDto newKsetDto = new KsetDto();
        List<Kpac> allKpacs = kpacService.findAllKpacs();
        List<KpacDto> dtos = new ArrayList<>();
        for (Kpac kpac : allKpacs) {
            KpacDto dto = new KpacDto();
            dto.setId(kpac.getId());
            dto.setTitle(kpac.getTitle());
            dto.setSelected(false);
            dtos.add(dto);
        }
        newKsetDto.setKpacs(dtos);
        return newKsetDto;
    }

    @RequestMapping(value = {"/newset"}, method = RequestMethod.POST)
    public String saveSet(@ModelAttribute("kset") @Valid KsetDto kset, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("kset", initializeNewKsetDto());
            model.addAttribute("errorMessage", "Title must not be empty");
            return "newset";
        }
        kpacService.addKset(kset);
        return "redirect:/sets/";
    }

    @RequestMapping(value = {"/set/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteSet(@PathVariable("id") Integer id) {
        kpacService.deleteSetById(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    class LocalDateAdapter implements JsonSerializer<LocalDate> {

        public JsonElement serialize(LocalDate date, Type typeOfSrc,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }
    }
}
