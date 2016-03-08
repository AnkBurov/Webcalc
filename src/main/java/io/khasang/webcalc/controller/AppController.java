package io.khasang.webcalc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import io.khasang.webcalc.model.Calc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class AppController {
    @Autowired
    private Calc calc;

    public void setCalc(Calc calc) {
        this.calc = calc;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String home(Model model, @RequestParam(value = "expression", required = false) String expression) {
        model.addAttribute("calcExpression", calc.launchCalc(expression));
        return "home";
    }
}