package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.repositories.JobtitelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultJobtitelService implements JobtitelService {
    private final JobtitelRepository jobtitelRepository;

    public DefaultJobtitelService(JobtitelRepository jobtitelRepository) {
        this.jobtitelRepository = jobtitelRepository;
    }

    @Override
    public List<Jobtitel> findAll() {
        return jobtitelRepository.findAll();
    }
}
