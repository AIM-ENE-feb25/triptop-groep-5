package Prototype.ExternalAccomodationAdapter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class OldBookingReviewAPI {

    public String externalRequest() {
        try {
            // Mock // couldn't actually fint an old api so made one up based on the new api
            Map<String, Object> reviewer = new HashMap<>();
            reviewer.put("name", "Steve Oldapi");
            reviewer.put("country", "ch");

            Map<String, Object> review = new HashMap<>();
            review.put("id", 2130645894);
            review.put("date", "2023-04-12");
            review.put("language", "en-gb");
            review.put("negative", "Entrance not clean.");
            review.put("positive", "Great location.");
            review.put("score", 9);
            review.put("reviewer", reviewer);

            List<Map<String, Object>> reviews = Collections.singletonList(review);

            Map<String, Object> hotelData = new HashMap<>();
            hotelData.put("hotel_id", 10004);
            hotelData.put("reviews", reviews);

            Map<String, Object> response = new HashMap<>();
            response.put("request_id", "old-api-req-123");
            response.put("data", hotelData);

            // to JSONObject
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return "{\"error\": \"Failed to generate JSON\"}";
        }
    }
}

