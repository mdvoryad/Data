package htw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import htw.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class RequestPublisher {
int counter = 0;

    @PostMapping("/orderProduct")
    public Object orderProduct(@RequestBody Product cake) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://external.services.com:3/deliverProduct";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(cake);

        // Send POST request with JSON request body
        HttpEntity<String> request = new HttpEntity<String>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        counter++;
        System.out.println(counter);
        // Return the response entity as the result
        return response;
    }

    @GetMapping("/totalOrders")
    public Object orderProduct() throws JsonProcessingException {


        return counter;
    }

}

