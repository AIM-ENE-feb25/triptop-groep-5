package Prototype.ExternalAccomodationAdapter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class NewBookingReviewAPI {

    public String externalRequest() {
        try {
            // Mock
            Map<String, Object> reviewer = new HashMap<>();
            reviewer.put("country", "ch");
            reviewer.put("name", "Becca Newapi");
            reviewer.put("travel_purpose", "leisure");
            reviewer.put("type", "couple");

            Map<String, Object> review = new HashMap<>();
            review.put("id", 2130645894);
            review.put("date", "2023-04-12");
            review.put("language", "en-gb");
            review.put("negative", "Front entrance is not clean. Breakfast was served not like a buffet.");
            review.put("positive", "Beautiful hotel in the central area.");
            review.put("reviewer", reviewer);
            review.put("score", 9);
            review.put("summary", "Fantastic hotel with kind staff providing excellent service.");
            review.put("text_meets_guidelines", true);

            List<Map<String, Object>> reviews = Collections.singletonList(review);

            Map<String, Object> hotelData = new HashMap<>();
            hotelData.put("id", 10004);
            hotelData.put("reviews", reviews);
            hotelData.put("url", "https://www.booking.com/hotel/nl/toren.html?aid=!AFFILIATE_ID!#tab-reviews");

            List<Map<String, Object>> dataList = Collections.singletonList(hotelData);

            Map<String, Object> response = new HashMap<>();
            response.put("request_id", "01fr9ez700exycb98w90w5r9sh");
            response.put("data", dataList);
            response.put("next_page", "...");

            // to JSONObject
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return "{\"error\": \"Failed to generate JSON\"}";
        }
    }
}






//package Prototype.ExternalAccomodationAdapter.api;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class NewBookingReviewAPI {
//
//    public Map<String, Object> externalRequest() {
//        //Mock
//        Map<String, Object> mockResponse = new HashMap<>();
//        mockResponse.put("hotelName", "Grand Hotel");
//        mockResponse.put("location", "Paris");
//        mockResponse.put("pricePerNight", 150);
//        mockResponse.put("availability", true);
//
//        return mockResponse;
//    }
//}




//  // Actual request
//const resp = await fetch(
//  `https://demandapi.booking.com/3.1/accommodations/reviews`,
//{
//    method: 'POST',
//            headers: {
//    'Content-Type': 'application/json',
//            'X-Affiliate-Id': '0',
//            Authorization: 'Bearer <YOUR_string_HERE>'
//},
//    body: JSON.stringify({
//            accommodations: [10004],
//    languages: ['en-gb']
//    })
//}
//);
//
//        const data = await resp.json();
//console.log(data);

// // Actual response
//{
//        "request_id": "01fr9ez700exycb98w90w5r9sh",
//        "data": [
//        {
//        "id": 10004,
//        "reviews": [
//        {
//        "id": 2130645894,
//        "date": "2023-04-12",
//        "language": "en-gb",
//        "negative": "front entrance is not clean (\nbreakfast was served not like a buffet, every time you have to ask a waiter to bring additional food.",
//        "positive": "beautiful hôtel in the central area",
//        "reviewer": {
//        "country": "ch",
//        "name": "Svetlana",
//        "travel_purpose": "leisure",
//        "type": "couple"
//        },
//        "score": 9,
//        "summary": "Fantastic hotel with kind personal who are providing hospitality and excellent service.",
//        "text_meets_guidelines": true
//        },
//        "..."
//        ],
//        "url": "https://www.booking.com/hotel/nl/toren.html?aid=!AFFILIATE_ID!#tab-reviews"
//        }
//        ],
//        "next_page": "..."
//        }

