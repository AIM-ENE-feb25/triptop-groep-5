package nl.triptop.betaalmethode.demo.strategie;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.*;

public class PaypalBetaling implements BetaalStrategie {
    private final APIContext apiContext;

    public PaypalBetaling() {
        this.apiContext = new APIContext("ARLVdtJ3Zb6PJk7tnKna8UxPcgXPul1Url4-Bc1XOaTaejkg5-f6dYlif2hTfQuMvHNtOzGTvhP4NnXB", "EOFNiNxTYfQco2Oei9LNOVK0BWiMswptasNRfGXdJP00ReKtHouukQytZkFjhG1zFLp-1WIi4MjgFnS_", "sandbox");
    }

    @Override
    public void verwerkBetaling(nl.triptop.betaalmethode.demo.model.BetalingRequest request) {
        Amount amount = new Amount("EUR", String.format("%.2f", request.getBedrag()));
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Boeking via PayPal");

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(List.of(transaction));

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://jouwapp.nl/geannuleerd");
        redirectUrls.setReturnUrl("https://jouwapp.nl/betaald");
        payment.setRedirectUrls(redirectUrls);

        try {
            Payment created = payment.create(apiContext);
            System.out.println("[PayPal API] Redirect gebruiker naar: " +
                    created.getLinks().get(1).getHref());
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
    }
}
