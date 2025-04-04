package Prototype.ExternalAccomodationAdapter;

import Prototype.ExternalAccomodationAdapter.api.NewBookingReviewAPI;
import Prototype.ExternalAccomodationAdapter.api.OldBookingReviewAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

//You have to run this prototype from main

@Service
public class ExternalAccommodationAdapter implements ServiceAdapter {
    private final OldBookingReviewAPI oldBookingReviewAPI;
    private final NewBookingReviewAPI newBookingReviewAPI;
    private final boolean useOldAPI;

    public ExternalAccommodationAdapter(OldBookingReviewAPI oldBookingReviewAPI, NewBookingReviewAPI newBookingReviewAPI, boolean useOldAPI) {
        this.oldBookingReviewAPI = oldBookingReviewAPI;
        this.newBookingReviewAPI = newBookingReviewAPI;
        this.useOldAPI = useOldAPI;
//        this.useOldAPI = true; //until the new api is available and the application can switch over
    }

    @Override
    public void request() {
        String jsonResponse = useOldAPI ? oldBookingReviewAPI.externalRequest() : newBookingReviewAPI.externalRequest();
        transformDataToOutput(jsonResponse, useOldAPI);
    }

    public void transformDataToOutput(String jsonString, boolean isOldAPI) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            System.out.println("TransformedData");

            if (isOldAPI) {
                // Handle Old API Response
                JsonNode hotelNode = jsonNode.get("data");

                System.out.println("Hotel ID: " + hotelNode.get("hotel_id").asText());

                JsonNode reviewsArray = hotelNode.get("reviews");
                for (JsonNode review : reviewsArray) {
                    System.out.println("\nReview ID: " + review.get("id").asText());
                    System.out.println("Date: " + review.get("date").asText());
                    System.out.println("Language: " + review.get("language").asText());
                    System.out.println("Score: " + review.get("score").asInt() + "/10");
                    System.out.println("Summary: -"); //no summary in older API
                    System.out.println("Positive: " + review.get("positive").asText());
                    System.out.println("Negative: " + review.get("negative").asText());

                    JsonNode reviewer = review.get("reviewer");
                    System.out.println("Reviewer: " + reviewer.get("name").asText() + " (" + reviewer.get("country").asText() + ")");
                }
            } else {
                // Handle New API Response
                JsonNode hotelNode = jsonNode.get("data").get(0);
                String hotelUrl = hotelNode.get("url").asText();

                System.out.println("Hotel Reviews: " + hotelUrl);

                JsonNode reviewsArray = hotelNode.get("reviews");
                for (JsonNode review : reviewsArray) {
                    System.out.println("\nReview ID: " + review.get("id").asText());
                    System.out.println("Date: " + review.get("date").asText());
                    System.out.println("Language: " + review.get("language").asText());
                    System.out.println("Score: " + review.get("score").asInt() + "/10");
                    System.out.println("Summary: " + review.get("summary").asText());
                    System.out.println("Positive: " + review.get("positive").asText());
                    System.out.println("Negative: " + review.get("negative").asText());

                    JsonNode reviewer = review.get("reviewer");
                    System.out.println("Reviewer: " + reviewer.get("name").asText() + " (" + reviewer.get("country").asText() + ")");
                    System.out.println("Purpose: " + reviewer.get("travel_purpose").asText());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}




//package Prototype.ExternalAccomodationAdapter;
//
//import Prototype.ExternalAccomodationAdapter.api.NewBookingReviewAPI;
//import Prototype.ExternalAccomodationAdapter.api.OldBookingReviewAPI;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class ExternalAccommodationAdapter implements ServiceAdapter {
//    private final OldBookingReviewAPI oldBookingReviewAPI;
//    private final NewBookingReviewAPI newBookingReviewAPI;
//    private final boolean useOldAPI;
//
//    public ExternalAccommodationAdapter(OldBookingReviewAPI oldBookingReviewAPI, NewBookingReviewAPI newBookingReviewAPI) {
//        this.oldBookingReviewAPI = oldBookingReviewAPI;
//        this.newBookingReviewAPI = newBookingReviewAPI;
//        this.useOldAPI = true; // Default or configurable value
//    }
//
//    @Override
//    public void request() {
//        if (useOldAPI) {
//            oldBookingReviewAPI.externalRequest();
//        } else {
//            newBookingReviewAPI.externalRequest();
//        }
//    }
//
//    public void transformDataToOutput() {
//        System.out.println("Input translated to output");
//    }
//}

