package com.example.e_commerceapp.Model;

import java.util.Date;

public class Trajet {

    public String depart;
    public String destination;
    public Date date;
    public String nbrePlace;
    public String car;
    public String route;


    public Trajet(String depart, String destination, Date date, String nbrePlace, String car, String route) {
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.nbrePlace = nbrePlace;
        this.car = car;
        this.route = route;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }

    public String getNbrePlace() {
        return nbrePlace;
    }

    public String getCar() {
        return car;
    }

    public String getRoute() {
        return route;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNbrePlace(String nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
