//package com.financemicroservice.Util;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.JsonNodeFactory;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Component
//public class WebClientUtil {
//
//    protected final WebClient.Builder webClient;
//
//    public WebClientUtil(WebClient.Builder webClient,
//                         @Value("${MicroServiceEmployeeManagement.URL}") String baseUrl) {
//        this.webClient = webClient.baseUrl(baseUrl);
//    }
//
//    public JsonNode GetEmployeeWebClient(String URL) {
//
//        ResponseEntity<JsonNode> getJson = webClient.build()
//                .get()
//                .uri(URL)
//                .retrieve()
//                .toEntity(JsonNode.class)
//                .block();
//
//        return getJson.getBody();
//    }
//}
