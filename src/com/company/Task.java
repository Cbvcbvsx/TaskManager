package com.company;


import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String name;
    private String descript;
    private Date date;
   private ArrayList <String> contact;

    public Task(String name, String descript, Date date, ArrayList <String> contact)
    {
        this.name=name;
        this.descript=descript;
        this.date=date;
        this.contact=contact;
    }

    public String getDescript() { return this.descript; }
    public String getName() { return this.name; }
    public Date getDate() { return this.date; }
    public ArrayList <String> getContact() { return this.contact; }
    public void setName(String name) { this.name=name; }
    public void setDescript(String descript) { this.descript=descript; }
    public void setDate(Date date) { this.date=date; }
    public void setContact(ArrayList <String> contact){ this.contact=contact; }
}
