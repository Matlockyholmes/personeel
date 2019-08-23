package be.vdab.personeel.controllers;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("Werknemershierarchie")
public class WerknemerController {
    private final WerknemerService werknemerService;

    public WerknemerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping
    public ModelAndView toonCEO(){
        return new ModelAndView("werknemers", "werknemer", werknemerService.findCEO());
    }

    @GetMapping("{optionalWerknemer}")
    public ModelAndView toonWerknemer(@PathVariable Optional<Werknemer> optionalWerknemer){
        ModelAndView modelAndView = new ModelAndView("werknemers");
        optionalWerknemer.ifPresent(werknemer -> modelAndView.addObject(werknemer));
        return modelAndView;
    }
}
