package com.foxminded.ums.money.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MoneyTransactionService {
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    @Autowired
    private MoneyTransactionMapper mapper;

    public MoneyTransactionDto findMoneyTransaction(UUID id) {
        MoneyTransactionDto moneyTransactionDto = mapper.entityToDto(moneyTransactionRepository.findById(id).get());
        return moneyTransactionDto;
    }

    public List<MoneyTransactionDto> findMoneyTransactions() {
        List<MoneyTransactionDto> moneyTransactionDtos = new ArrayList<>();
        moneyTransactionRepository.findAll().forEach(s -> moneyTransactionDtos.add(mapper.entityToDto(s)));
        return moneyTransactionDtos;
    }

    public List<MoneyTransactionDto> findMoneyTransactionsBySenderInCurrency(UUID senderId, String currencyCode) {
        List<MoneyTransactionDto> moneyTransactionDtos = new ArrayList<>();
        moneyTransactionRepository.findBySender(senderId).forEach(s -> moneyTransactionDtos.add(
                mapper.convertMoneyTransactionDtoInCurrency(
                        mapper.entityToDto(s),currencyCode)));
        return moneyTransactionDtos;
    }

    public List<MoneyTransactionDto> findMoneyTransactionsByReceiverInCurrency(UUID receiverId, String currencyCode) {
        List<MoneyTransactionDto> moneyTransactionDtos = new ArrayList<>();
        moneyTransactionRepository.findByReceiver(receiverId).forEach(s -> moneyTransactionDtos.add(
                mapper.convertMoneyTransactionDtoInCurrency(
                        mapper.entityToDto(s), currencyCode)));
        return moneyTransactionDtos;
    }

    @Transactional
    public MoneyTransactionDto addMoneyTransaction(MoneyTransactionDto moneyTransactionDto) {
        MoneyTransaction entity = mapper.dtoToEntity(moneyTransactionDto);
        entity.setId(null);
        return mapper.entityToDto(moneyTransactionRepository.save(entity));
    }

    @Transactional
    public MoneyTransactionDto updateMoneyTransaction(MoneyTransactionDto moneyTransactionDto) {
        findMoneyTransaction(moneyTransactionDto.getId());

        MoneyTransaction entity = mapper.dtoToEntity(moneyTransactionDto);
        moneyTransactionRepository.save(entity);

        MoneyTransactionDto dto = mapper.entityToDto(
                moneyTransactionRepository.findById(moneyTransactionDto.getId()).get());

        return dto;
    }

    @Transactional
    public void deleteMoneyTransaction(UUID moneyTransactionUuid) {
        moneyTransactionRepository.deleteById(moneyTransactionUuid);
    }
}