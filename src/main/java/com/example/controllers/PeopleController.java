package com.example.controllers;

import com.example.dao.PersonDAO;
import com.example.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        try {
            model.addAttribute("person", personDAO.show(id));
            return "people/show";
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "people/new";
        }

        personDAO.save(person);
        return ("redirect:/people");
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        try {
            model.addAttribute("person", personDAO.show(id));
            return "people/edit";
        } catch (Exception ex) {
            return null;
        }
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute Person person, @PathVariable @Valid int id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/edit";
        }

        person.setId(id);
        personDAO.update(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
