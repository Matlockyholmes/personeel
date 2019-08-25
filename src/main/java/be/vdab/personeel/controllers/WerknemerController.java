package be.vdab.personeel.controllers;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.forms.OpslagForm;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("werknemershierarchie")
public class WerknemerController {
    private final WerknemerService werknemerService;
    private static final String REDIRECT_NA_SUCCES = "redirect:/werknemershierarchie/{id}";
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
    public ModelAndView toonOpslag(@PathVariable Optional<Werknemer> optionalWerknemer, Model model){
        ModelAndView modelAndView = new ModelAndView("opslag");
        if (!model.containsAttribute("opslagForm")){
            model.addAttribute("opslagForm", new OpslagForm(null));
            modelAndView.addObject(new OpslagForm(null));
        }
        optionalWerknemer.ifPresent(werknemer -> modelAndView.addObject(werknemer));
        return modelAndView;
    }

    @PostMapping("opslag/{optionalWerknemer}")
    public String geefOpslag(@PathVariable Optional<Werknemer> optionalWerknemer, @Valid @ModelAttribute("opslagForm") OpslagForm opslagForm,
                             BindingResult binding, RedirectAttributes redirectAttributes){
        AtomicLong id = new AtomicLong();
        optionalWerknemer.ifPresent(werknemer -> id.set(werknemer.getId()));
        redirectAttributes.addAttribute("id", id);
        if (binding.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.opslagForm", binding);
            redirectAttributes.addFlashAttribute("opslagForm", opslagForm);
            return REDIRECT_NA_FAAL_OPSLAG;
        }
        optionalWerknemer.ifPresent(werknemer -> werknemerService.saveOpslag(werknemer, opslagForm.getOpslag()));
        return REDIRECT_NA_SUCCES;
    }

    @GetMapping("rijksregisternummer/{optionalWerknemer}")
    public ModelAndView toonRijksregisternummer(@PathVariable Optional<Werknemer> optionalWerknemer){
        ModelAndView modelAndView = new ModelAndView("rijksregisternummer");
        optionalWerknemer.ifPresent(werknemer -> modelAndView.addObject(werknemer));
        return modelAndView;
    }
}
