package com.winandronux.LiterAlura.service;

import com.winandronux.LiterAlura.models.DataBook;
import com.winandronux.LiterAlura.models.DataGutendexResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexAPI {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ConvertJsonToData converter = new ConvertJsonToData();

    private DataGutendexResponse consult(String url) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://gutendex.com/books/" + url))
                .build();

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return converter.getData(response.body(), DataGutendexResponse.class);
    }

    public DataBook search(String query) {
        var data = consult("?search=" + query);
        if (data.count() > 0) {
            return data.results().get(0);
        } else {
            return null;
        }
    }
}
