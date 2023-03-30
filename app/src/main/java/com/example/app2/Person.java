package com.example.app2;

public class Person {
    int PersonId;
    String Navn;
    String Descrip;


    public Person() {}

    public Person(int id, String navn, String descrip) {
        super();
        this.PersonId = id;
        this.Navn = navn;
        this.Descrip = descrip;

    }

    public String getNavn() {
        return Navn;
    }

    public void setNavn(String navn) {
        Navn = navn;
    }

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String descrip) {
        Descrip = descrip;
    }



}