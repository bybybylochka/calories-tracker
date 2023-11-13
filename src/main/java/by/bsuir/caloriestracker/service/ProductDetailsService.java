package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.ProductDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class ProductDetailsService {
    @Value("${api-key}")
    private String apiKey;

    public ProductDetails getProductDetails(String query) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
//                .header("content-type", "application/x-www-form-urlencoded")
//                .header("Accept-Encoding", "application/gzip")
//                .header("X-RapidAPI-Key", "b0c69f6ef7msh339b13ae068acb9p15c7c2jsn452b3bd0d53a")
//                .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
//                .method("POST", HttpRequest.BodyPublishers.ofString("q=Hello%2C%20world!&target=ru&source=en"))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Api-Key", apiKey);
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
        String url = "https://api.api-ninjas.com/v1/nutrition?query=" + query;
        ResponseEntity<ProductDetails[]> result = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity, ProductDetails[].class
        );
        return Objects.requireNonNull(result.getBody())[0];
    }
}
