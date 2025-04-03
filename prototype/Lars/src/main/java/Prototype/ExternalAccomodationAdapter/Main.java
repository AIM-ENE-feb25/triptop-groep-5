package Prototype.ExternalAccomodationAdapter;

import Prototype.ExternalAccomodationAdapter.api.NewBookingReviewAPI;
import Prototype.ExternalAccomodationAdapter.api.OldBookingReviewAPI;

public class Main {
    public static void main(String[] args) {
        OldBookingReviewAPI oldAPI = new OldBookingReviewAPI();
        NewBookingReviewAPI newAPI = new NewBookingReviewAPI();

//        boolean useOldAPI = true;

        ExternalAccommodationAdapter adapter = new ExternalAccommodationAdapter(oldAPI, newAPI, true); //uses old api

        TripService tripService = new TripService(adapter);


        System.out.println("Old API");
        tripService.requestAvailability();
        System.out.println("------------------------------------------------------------------");


        ExternalAccommodationAdapter adapter2 = new ExternalAccommodationAdapter(oldAPI, newAPI, false); //uses new api


        TripService tripService2 = new TripService(adapter2);

        System.out.println("New API");
        tripService2.requestAvailability();
        System.out.println("-------------------------------------------------------------------");
    }
}
