package com.example.controller;

import com.example.tree.Term;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/parse")
public class ApiParseController {

    @RequestMapping(method = RequestMethod.POST)
    public TermResponse getShopInJSON(@PathVariable String formula) {

        Term x = Term.getTermFromString(formula);

        return new TermResponse(x, null);
    }
}
