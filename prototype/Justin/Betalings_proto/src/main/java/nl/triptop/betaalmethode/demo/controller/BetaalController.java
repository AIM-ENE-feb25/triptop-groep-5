package nl.triptop.betaalmethode.demo.controller;

import nl.triptop.betaalmethode.demo.factory.BetaalStrategieFactory;
import nl.triptop.betaalmethode.demo.model.BetalingRequest;
import nl.triptop.betaalmethode.demo.strategie.BetaalStrategie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/betalen")
public class BetaalController {

    @PostMapping
    public String verwerkBetaling(@RequestBody BetalingRequest request) {
        try {
            BetaalStrategie strategie = BetaalStrategieFactory.maakStrategie(request.getMethode());
            strategie.verwerkBetaling(request);
            return "Betaling succesvol gestart via " + request.getMethode();
        } catch (IllegalArgumentException e) {
            return "Fout: " + e.getMessage();
        }
    }
}