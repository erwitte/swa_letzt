package de.hsos.Bestellung.entity;

import java.util.List;

public record Bestellung(
        List<Bestellposten> bestellposten
) {
}
