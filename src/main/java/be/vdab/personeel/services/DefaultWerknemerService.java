package be.vdab.personeel.services;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultWerknemerService implements WerknemerService{
    private final WerknemerRepository werknemerRepository;

    public DefaultWerknemerService(WerknemerRepository werknemerRepository) {
        this.werknemerRepository = werknemerRepository;
    }

    @Override
    public Werknemer findCEO() {
        return werknemerRepository.findByChefIsNull();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void saveOpslag(Werknemer werknemer, BigDecimal opslag) {
        werknemer.geefOpslag(opslag);
        werknemerRepository.save(werknemer);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void saveRijksregisternummer(Werknemer werknemer, long rijksregisternummer) {
        werknemer.setRijksregisternr(rijksregisternummer);
        werknemerRepository.save(werknemer);
    }
}
