package be.vdab.personeel.controllers;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.forms.OpslagForm;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("werknemershierarchie")
public class WerknemerController {
    private final WerknemerService werknemerService;
    private static final String REDIRECT_NA_SUCCES_OPSLAG = "redirect:/werknemershierarchie/{id}";
    private static final String REDIRECT_NA_FAAL_OPSLAG = "redirect:/werknemershierarchie/opslag/{id}";

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

    @GetMapping("opslag/{optionalWerknemer}")
    public ModelAndView toonOpslag(@PathVariable Optional<Werknemer> optionalWerknemer){
        ModelAndView modelAndView = new ModelAndView("opslag").addObject(new OpslagForm(null));
        optionalWerknemer.ifPresent(werknemer -> modelAndView.addObject(werknemer));
        return modelAndView;
    }

    @PostMapping("opslag/{optionalWerknemer}")
    public ModelAndView geefOpslag(@PathVariable Optional<Werknemer> optionalWerknemer, @Valid OpslagForm opslagForm, Errors errors, RedirectAttributes redirectAttributes){
       // redirectAttributes.addAttribute("id", optionalWerknemer.get().getId());
        ModelAndView modelAndView = new ModelAndView("opslag");
        if(!errors.hasErrors()){
            optionalWerknemer.ifPresent(werknemer -> werknemerService.saveOpslag(werknemer, opslagForm.getOpslag()));
           // return REDIRECT_NA_SUCCES_OPSLAG;
        }
        optionalWerknemer.ifPresent(werknemer -> modelAndView.addObject(werknemer));
        return modelAndView;
       // return REDIRECT_NA_FAAL_OPSLAG;
    }

}
