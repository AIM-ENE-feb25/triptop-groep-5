package nl.triptop.betaalmethode.demo.factory;
import nl.triptop.betaalmethode.demo.strategie.BetaalStrategie;
import nl.triptop.betaalmethode.demo.strategie.CreditcardBetaling;
import nl.triptop.betaalmethode.demo.strategie.IdealBetaling;
import nl.triptop.betaalmethode.demo.strategie.PaypalBetaling;

public class BetaalStrategieFactory {
    public static BetaalStrategie maakStrategie(String methode){
        return switch (methode){
            case "ideal" -> new IdealBetaling();
            case "paypal" -> new PaypalBetaling();
            case "creditcard" -> new CreditcardBetaling();
            default -> throw new IllegalArgumentException("Onbekende betaalmethode: " + methode);
        };
    }
}
