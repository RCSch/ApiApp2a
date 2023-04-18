package com.example.app2;

public class Person {
    int PersonId;
    String Navn;
    String Descrip;
    Boolean Fav;
    int Nationalitet;


    public Person() {}

    public Person(int id, String navn, String descrip, Boolean fav, int nationalitet) {
        super();
        this.PersonId = id;
        this.Navn = navn;
        this.Descrip = descrip;
        this.Fav = fav;
        this.Nationalitet = nationalitet;


    }

    // Getter Setter for ID
    public int getPerID() {return PersonId;} //Er de her egentlig nødvendige? Den skulle gerne sætte ID selv.
    public void setPerID(int personId) {this.PersonId = personId;}

    // Getter Setter for Navn
    public String getNavn() {
        return Navn;
    }

    public void setNavn(String navn) {
        Navn = navn;
    }

    // Getter Setter for Describ
    public String getDescrip() {
        return Descrip;
    }
    public void setDescrip(String descrip) {
        this.Descrip = descrip;
    }


    // Getter Setter for Favourite
    public boolean isFavorite() {return Fav;}
    public void setFavorite(boolean fav) {this.Fav = fav;}

    // Getter Setter for Nationalitet
    public int getNationalitet() {
        return Nationalitet;
    }
    public void setNationalitet(int nationalitet) {
        this.Nationalitet = nationalitet;
    }


}