package be.vdab.personeel.services;

import be.vdab.personeel.domain.Werknemer;

import java.math.BigDecimal;

public interface WerknemerService {
    Werknemer findCEO();
    void saveOpslag(Werknemer werknemer, BigDecimal opslag);
}
