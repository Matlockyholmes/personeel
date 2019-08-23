package be.vdab.personeel.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "jobtitels")
public class Jobtitel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @OneToMany(mappedBy = "jobtitel")
    @OrderBy("familienaam")
    private Set<Werknemer> werknemers;
    @Version
    private int versie;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Werknemer> getWerknemers() {
        return werknemers;
    }

    public int getVersie() {
        return versie;
    }
}
