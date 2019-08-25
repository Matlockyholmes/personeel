package be.vdab.personeel.controllers;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.services.JobtitelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("jobtitels")
public class JobtitelController {
    private final JobtitelService jobtitelService;

    public JobtitelController(JobtitelService jobtitelService) {
        this.jobtitelService = jobtitelService;
    }

    @GetMapping
    public ModelAndView toonAlleJobtitels(){
        return new ModelAndView("jobtitels", "jobtitels", jobtitelService.findAll());
    }

    @GetMapping("{optionalJobtitel}")
    public ModelAndView toonWerknemersJobtitel(@PathVariable Optional<Jobtitel> optionalJobtitel){
        ModelAndView modelAndView = new ModelAndView("jobtitels","jobtitels", jobtitelService.findAll());
        optionalJobtitel.ifPresent(jobtitel -> modelAndView.addObject(jobtitel));
        return modelAndView;
    }
}
