package nl.triptop.betaalmethode.demo.strategie;

import nl.triptop.betaalmethode.demo.model.BetalingRequest;

public interface BetaalStrategie {
    void verwerkBetaling(BetalingRequest request);
}

