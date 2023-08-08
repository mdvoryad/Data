package htw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import htw.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/services")
public class RequestPublisher {

    @GetMapping("/getProduct/{id}")
    public Object getProduct(@PathVariable String id) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://data.services.com:4/findProductsById/"+id;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        return response;
    }

    @GetMapping("/listProducts")
    public Object listAllProducts() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://data.services.com:4/findAllProducts";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        return response;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public Object deleteProduct(@PathVariable String id) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://data.services.com:4/delete/"+id;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

        return response;
    }

    @PostMapping("/orderProduct")
    public Object orderProduct(@RequestBody Product cake) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://data.order.com:2/orderProduct";

        // Set request headers and convert Product object to JSON string
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(cake);

        // Send POST request with JSON request body
        HttpEntity<String> request = new HttpEntity<String>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        // Return the response entity as the result
        return response;
    }
    @GetMapping("/check")
    public Object check() {
        return "OK";
    }

}

