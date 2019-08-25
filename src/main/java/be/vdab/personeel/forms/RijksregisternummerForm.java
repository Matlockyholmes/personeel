package be.vdab.personeel.forms;

import be.vdab.personeel.constraints.Rijksregisternummer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Rijksregisternummer
public class RijksregisternummerForm {
    private final Long rijksregisternummer;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private final LocalDate geboorteDatum;

    public RijksregisternummerForm(Long rijksregisternummer, LocalDate geboorteDatum) {
        this.rijksregisternummer = rijksregisternummer;
        this.geboorteDatum = geboorteDatum;
    }

    public Long getRijksregisternummer() {
        return rijksregisternummer;
    }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }
}
