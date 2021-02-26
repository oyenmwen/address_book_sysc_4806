package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository repository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Iterable<AddressBook> it = repository.findAll();
        ArrayList<AddressBook> ab = new ArrayList<AddressBook>();
        for(AddressBook e: it){
            ab.add(e);
        }
        model.addAttribute("ab",ab);
        return "AddressBook/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        AddressBook addressBook = new AddressBook();
        repository.save(addressBook);
        model.addAttribute(addressBook);
        return "AddressBook/new";
    }

    @GetMapping("/{id}")
    public String showAddressBook(@PathVariable String id, Model model) {
        AddressBook addressBook = repository.findById(Long.parseLong(id));
        model.addAttribute(addressBook);
        return "AddressBook/show";
    }


    @GetMapping("/{id}/new")
    public String newBuddyForm(@PathVariable String id, Model model) {
        model.addAttribute("buddy", new BuddyInfo());
        model.addAttribute("id",id);
        return "BuddyInfo/new";
    }

    @PostMapping("/new")
    public String newBuddySubmit(@RequestParam(name = "id") String id, @ModelAttribute BuddyInfo buddy, Model model) {
        AddressBook addressBook = repository.findById(Long.parseLong(id));
        addressBook.addBuddies(buddy);
        repository.save(addressBook);
        model.addAttribute("buddy", buddy);
        model.addAttribute(addressBook);
        return "BuddyInfo/show";
    }

    @GetMapping("/{id}/{buddyId}")
    public String showBuddy(@PathVariable String id, @PathVariable String buddyId,  Model model) {
        AddressBook addressBook = repository.findById(Long.parseLong(id));
        for(BuddyInfo b: addressBook.getBuddies()){
            if(b.getId() == Long.parseLong(buddyId)){
                model.addAttribute("buddy",b);
            }
        }
        model.addAttribute("addressBook",addressBook);
        return "BuddyInfo/show";
    }



}
