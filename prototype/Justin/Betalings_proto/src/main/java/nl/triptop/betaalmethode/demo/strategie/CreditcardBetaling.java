package nl.triptop.betaalmethode.demo.strategie;
import nl.triptop.betaalmethode.demo.model.BetalingRequest;

public class CreditcardBetaling implements BetaalStrategie {
    @Override
    public void verwerkBetaling(BetalingRequest request) {
        System.out.println("[Stripe simulatie] Creditcardbetaling van: €" + request.getBedrag());
    }
}
