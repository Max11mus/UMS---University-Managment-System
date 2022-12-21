package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.MoneyTransactionDto;
import com.foxminded.ums.validation.UUID;
import com.foxminded.ums.validation.ValidCurrencyCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Component
@Valid
public class RestMoneyTransactionClient {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:7000/money-transactions";

    public List<MoneyTransactionDto> getMoneyTransactionsByOwner(@UUID String ownerId,
                                                                 @ValidCurrencyCode String currency) {
        String urlWithParams = url
                + "?id=" + ownerId
                + "&currency=" + currency;
        ResponseEntity<MoneyTransactionDto[]> response = restTemplate.getForEntity(urlWithParams,
                MoneyTransactionDto[].class);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new ResponseStatusException(response.getStatusCode(),
                    urlWithParams + System.lineSeparator() + response.getHeaders().toString());
        }

        return Arrays.asList(response.getBody());
    }
}
