package de.hsos.Bestellung.boundary.DTOs;

public class ZutatDTO {
    public String name;
    public double preis;
    public int index = 1;

    public ZutatDTO(String name, double preis, int index ){
        this.index = index;
        this.name = name;
        this.preis = preis;
    }
}
