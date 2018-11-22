package com.company;


import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String name;
    private String description;
    private Date date;
   private ArrayList <String> contacts;

    public Task(String name, String description, Date date, ArrayList <String> contacts)
    {
        this.name=name;
        this.description=description;
        this.date=date;
        this.contacts=contacts;
    }
    public Task(){}

    public String getDescription() { return this.description; }
    public String getName() { return this.name; }
    public Date getDate() { return this.date; }
    public ArrayList <String> getContacts() { return this.contacts; }
    public void setName(String name) { this.name=name; }
    public void setDescription(String description) { this.description=description; }
    public void setDate(Date date) { this.date=date; }
    public void setContacts(ArrayList <String> contacts){ this.contacts=contacts; }
    public String arrayListToString()
    {
        String s="";
        for(int i=0; i<contacts.size()-1; i++)
        {
            s+=contacts.get(i) + ", ";
        }
        s+=contacts.get(contacts.size()-1);
        return s;
    }
}
