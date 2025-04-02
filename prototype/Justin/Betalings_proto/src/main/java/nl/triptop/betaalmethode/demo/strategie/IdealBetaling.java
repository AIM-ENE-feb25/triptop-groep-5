package nl.triptop.betaalmethode.demo.strategie;
import nl.triptop.betaalmethode.demo.model.BetalingRequest;

public class IdealBetaling implements BetaalStrategie {
    @Override
    public void verwerkBetaling(BetalingRequest request) {
        System.out.println("Ideal betaling verwerken");
    }
}
