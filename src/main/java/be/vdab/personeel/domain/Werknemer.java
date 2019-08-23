package be.vdab.personeel.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "werknemers")
public class Werknemer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String familienaam;
    @NotBlank
    private String voornaam;
    @Email
    private String email;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chefid")
    private Werknemer chef;
    @OneToMany(mappedBy = "chef")
    @OrderBy("familienaam")
    private Set<Werknemer> werknemers;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobtitelid")
    private Jobtitel jobtitel;
    @NotNull
    private BigDecimal salaris;
    @NotBlank
    private String paswoord;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate geboorte;
    @NotNull
    private long rijksregisternr;
    @Version
    private int versie;

    public long getId() {
        return id;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmail() {
        return email;
    }

    public Werknemer getChef() {
        return chef;
    }

    public Set<Werknemer> getWerknemers() {
        return Collections.unmodifiableSet(werknemers);
    }

    public Jobtitel getJobtitel() {
        return jobtitel;
    }

    public BigDecimal getSalaris() {
        return salaris;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public LocalDate getGeboorte() {
        return geboorte;
    }

    public long getRijksregisternr() {
        return rijksregisternr;
    }

    public int getVersie() {
        return versie;
    }
}
