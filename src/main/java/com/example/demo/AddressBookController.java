package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        model.addAttribute(ab);
        return "addresbooks";
    }

    @GetMapping("/create")
    public String create(Model model) {
        AddressBook addressBook = new AddressBook();
        repository.save(addressBook);
        model.addAttribute(addressBook);
        return "create";
    }

//    @GetMapping("/{id}/buddies")
//    public List<BuddyInfo> get(@PathVariable String id) {
//
//        Long abId = Long.parseLong(id);
//        Optional<AddressBook> ab = repository.findById(abId);
//
//        if(ab.isPresent()){
//            return ab.get().getBuddies();
//        }
//        return null;
//    }

//    @PostMapping ("/{id}/create")
//    public List<BuddyInfo> createBuddy(@PathVariable String id) {
//
//        Long abId = Long.parseLong(id);
//        Optional<AddressBook> ab = repository.findById(abId);
//
//        if(ab.isPresent()){
//            return ab.get().getBuddies();
//        }
//        return null;
//    }




}
