package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class AddressBookController {

    @Autowired
    private AddressBookRepository repository;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("/index")
    public ModelAndView index(Model model) {
        Iterable<AddressBook> it = repository.findAll();
        ArrayList<AddressBook> ab = new ArrayList<AddressBook>();
        for(AddressBook e: it){
            ab.add(e);
        }
        model.addAttribute("ab",ab);
        return new ModelAndView("AddressBook/index");
    }

    @GetMapping("/json/index")
    public ArrayList<AddressBook> indexJson() {
        Iterable<AddressBook> it = repository.findAll();
        ArrayList<AddressBook> ab = new ArrayList<AddressBook>();
        for(AddressBook e: it){
            ab.add(e);
        }
        return ab;
    }

    @GetMapping("/json/create")
    public AddressBook createJSON() {
        AddressBook addressBook = new AddressBook();
        repository.save(addressBook);
        return addressBook;
    }



    @GetMapping("/create")
    public ModelAndView create(Model model) {
        AddressBook addressBook = new AddressBook();
        repository.save(addressBook);
        model.addAttribute(addressBook);
        return new ModelAndView("AddressBook/new");
    }

    @GetMapping("/{id}")
    public ModelAndView showAddressBook(@PathVariable String id, Model model) {
        AddressBook addressBook = repository.findById(Long.parseLong(id));
        model.addAttribute(addressBook);
        return new ModelAndView("AddressBook/show");
    }

    @GetMapping("json/{id}")
    public AddressBook jsonShowAddressBook(@PathVariable String id) {
        return repository.findById(Long.parseLong(id));
    }

    @GetMapping("/spa")
    public ModelAndView singlePageApplication() {
        return new ModelAndView("spa");
    }

    @GetMapping("/{id}/new")
    public ModelAndView newBuddyForm(@PathVariable String id, Model model) {
        model.addAttribute("buddy", new BuddyInfo());
        model.addAttribute("id",id);
        return new ModelAndView("BuddyInfo/new");
    }

    @PostMapping("/new")
    public ModelAndView newBuddySubmit(@RequestParam(name = "id") String id, @ModelAttribute BuddyInfo buddy, Model model) {
        AddressBook addressBook = repository.findById(Long.parseLong(id));
        addressBook.addBuddies(buddy);
        repository.save(addressBook);
        model.addAttribute("buddy", buddy);
        model.addAttribute(addressBook);
        return new ModelAndView("BuddyInfo/show");
    }

    @PostMapping("spa/json/new")
    public BuddyInfo jsonBuddySubmit(@RequestParam(name = "id") String id, @RequestBody BuddyInfo buddy) {
        AddressBook addressBook = repository.findById(Long.parseLong(id));
        addressBook.addBuddies(buddy);
        repository.save(addressBook);
        return buddy;
    }

    @GetMapping("/{id}/{buddyId}")
    public ModelAndView showBuddy(@PathVariable String id, @PathVariable String buddyId,  Model model) {
        AddressBook addressBook = repository.findById(Long.parseLong(id));
        for(BuddyInfo b: addressBook.getBuddies()){
            if(b.getId() == Long.parseLong(buddyId)){
                model.addAttribute("buddy",b);
            }
        }
        model.addAttribute("addressBook",addressBook);
        return new ModelAndView("BuddyInfo/show");
    }



}
