package com.example.controller;

import com.example.tree.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.model.Person;
import com.example.service.PersonService;

import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/")
    public String listPeople(Map<String, Object> map) {

        return "index";
    }

    @RequestMapping(value = "/parse")
    public @ResponseBody TermResponse addPerson(@RequestParam(required = false) String formula) {
        Term x = Term.getTermFromString(formula);
        TermResponse response = new TermResponse(x, null);
        return response;
    }

    @RequestMapping("/delete/{personId}")
    public String deletePerson(@PathVariable("personId") Integer personId) {
        Term x = Term.getTermFromString(personId+"");

        personService.removePerson(personId);

        return "redirect:/people/";
    }
}
