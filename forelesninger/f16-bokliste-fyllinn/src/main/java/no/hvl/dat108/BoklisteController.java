package no.hvl.dat108;

import static no.hvl.dat108.Boker.alleBoker;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoklisteController {

    //Metode som kjøres ved GET localhost:8080/.../alleboker
    //Metode som kjøres ved GET localhost:8080/.../noenboker?forfatter=xxx
}
