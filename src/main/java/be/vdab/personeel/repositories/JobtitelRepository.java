package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobtitelRepository extends JpaRepository<Jobtitel, Long> {
    List<Jobtitel> findAll();
}
