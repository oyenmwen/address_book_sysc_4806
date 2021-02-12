package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "addressBook",cascade = CascadeType.PERSIST)
    private List<BuddyInfo> buddies;


    public AddressBook() {
        buddies = new ArrayList<BuddyInfo>();
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    @Autowired
    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddressBook: {" + "\n"+
                "   id: " + id + ",\n" +
                "   BuddyInfo: " + buddies + ",\n" +
                '}';
    }

    public void addBuddies(BuddyInfo buddy) {
        buddies.add(buddy);
    }

    public static void main(String[] args) {
        AddressBook a = new AddressBook();
        a.addBuddies(new BuddyInfo("osayi","383838",a));
        a.addBuddies(new BuddyInfo("bernice","0987656",a));
        a.addBuddies(new BuddyInfo("osowolo","123456",a));
        System.out.println(a);
    }


}
