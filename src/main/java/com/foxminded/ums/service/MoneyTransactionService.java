package com.foxminded.ums.service;

import com.foxminded.ums.dto.MoneyTransactionDto;
import com.foxminded.ums.validation.UUID;
import com.foxminded.ums.validation.ValidCurrencyCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@Validated
public class MoneyTransactionService {
    private RestTemplate restTemplate = new RestTemplate();
    @Value( "${money.transaction.service.url}" )
    private String url;
    private String path = "/money-transactions";
    private String ownerIdQueryParamName = "ownerId";
    private String currencyQueryParamName = "currency";

    public List<MoneyTransactionDto> getMoneyTransactionsByOwner(@UUID String ownerId,
                                                                 @ValidCurrencyCode String currency) {

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(url)
                .path(path)
                .queryParam(ownerIdQueryParamName, ownerId)
                .queryParam(currencyQueryParamName, currency).build().encode();

        ResponseEntity<MoneyTransactionDto[]> response = restTemplate.getForEntity(uriComponents.toUriString(),
                MoneyTransactionDto[].class);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new ResponseStatusException(response.getStatusCode(),
                    uriComponents.toUriString() + System.lineSeparator() + response.getHeaders().toString());
        }

        return Arrays.asList(response.getBody());
    }
}
