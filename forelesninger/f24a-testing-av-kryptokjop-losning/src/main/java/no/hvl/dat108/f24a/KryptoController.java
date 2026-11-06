package no.hvl.dat108.f24a;

import no.hvl.dat108.f24a.solution.Kryptokalkulator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class KryptoController {
	
	private final Kryptokalkulator kryptokalkulator;

    public KryptoController(Kryptokalkulator kryptokalkulator) {
        this.kryptokalkulator = kryptokalkulator;
    }

    @GetMapping("/kjopekrypto") //For å få opp skjemaet
	public String skjema(Model model) {
		model.addAttribute("kryptokoder", Krypto.values());
		model.addAttribute("fiatkoder", Fiat.values());
		return "kjopekrypto"; //.html - Thymeleaf View
	}

	@PostMapping("/kjopekrypto") //For å behandle ønsket kjøp
	public String kjope(Model model,
			@RequestParam Double antall,
			@RequestParam String krypto, 
			@RequestParam String fiat) {
		
		 /* Det er denne metoden vi ønsker å enhetsteste!
		 *  Beregner den riktig beløp gitt inputdata og kurser?
		 */
		double belop = kryptokalkulator.kjope(antall,
                Krypto.valueOf(krypto), Fiat.valueOf(fiat));

		String belopToDesimaler = String.format("%,.2f", belop);

        /*
         * Nå burde jeg selvfølgelig gjort en redirect i stedet, for å
         * hindre dobbeltposting, men pytt ...
         */
        model.addAttribute("antall", antall);
        model.addAttribute("krypto", krypto);
        model.addAttribute("belopToDesimaler", belopToDesimaler);
        model.addAttribute("fiat", fiat);

		return "resultat"; //.html - Thymeleaf View
	}
}
