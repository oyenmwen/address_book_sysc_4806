package com.example.demo;

import javax.persistence.*;


@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;
    private String number;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="addressBook_ID")
    private AddressBook addressBook;

    protected BuddyInfo() {}

    public BuddyInfo(String name, String number, AddressBook addressBook) {
        this.name = name;
        this.number = number;
        this.addressBook = addressBook;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BuddyInfo: {" + ",\n"+
                "   id: " + id + ",\n"+
                "   name: '" + this.getName() + '\'' + ",\n"+
                "   number: '" + this.getNumber() + '\'' + ",\n"+
                '}';
    }

    public static void main(String[] args) {
        AddressBook b = new AddressBook();
        BuddyInfo a = new BuddyInfo("bob","383838",b);
//        a.addBuddies(new BuddyInfo("bernice","0987656",a));
//        a.addBuddies(new BuddyInfo("osowolo","123456",a));
        System.out.println(a);
    }

}
