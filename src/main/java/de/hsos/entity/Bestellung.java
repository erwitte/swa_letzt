package de.hsos.entity;

import java.util.List;

public record Bestellung(
        List<Bestellposten> bestellposten
) {
}
