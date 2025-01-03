package de.hsos.boundary.DTOs;

public class StandardPizzenDTO {
    public String name;
    public double preis;
    public int index;

    public StandardPizzenDTO(String name, double preis, int index) {
        this.name = name;
        this.preis = preis;
        this.index = index;
    }
}
