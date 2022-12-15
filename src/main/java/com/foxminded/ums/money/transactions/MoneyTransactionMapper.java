package com.foxminded.ums.money.transactions;

import com.foxminded.ums.entities.Person;
import com.foxminded.ums.exeptions.StudentNotFoundException;
import com.foxminded.ums.exeptions.TeacherNotFoundException;
import com.foxminded.ums.service.StudentService;
import com.foxminded.ums.service.TeacherService;
import lombok.SneakyThrows;
import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class MoneyTransactionMapper {
    @Autowired
    StudentService studentService;

    @Autowired
    private CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    TeacherService teacherService;

    public MoneyTransactionDto entityToDto(MoneyTransaction entity) {
        MoneyTransactionDto dto = new MoneyTransactionDto();
        dto.setId(entity.getId());
        dto.setDateTime(entity.getDateTime());

        dto.setSenderId(entity.getSender().getId().toString());
        dto.setSenderName(entity.getSender().getName());
        dto.setSenderSurName(entity.getSender().getSurname());

        dto.setReceiverId(entity.getReceiver().getId().toString());
        dto.setReceiverName(entity.getReceiver().getName());
        dto.setReceiverSurName(entity.getReceiver().getSurname());

        dto.setAmount(entity.getMonetaryAmount().getNumber().numberValueExact(BigDecimal.class));
        dto.setCurrencyCode(entity.getMonetaryAmount().getCurrency().toString());
        return dto;
    }

    @SneakyThrows
    public MoneyTransaction dtoToEntity(MoneyTransactionDto dto){
        MoneyTransaction entity = new MoneyTransaction();
        entity.setId(dto.getId());
        entity.setDateTime(dto.getDateTime());

        Person sender= null;
        sender = findPerson(dto.getSenderId());
        if (sender == null) {
            throw new PersonNotFoundException(UUID.fromString(dto.getSenderId()),null);
        }

        Person Receiver = null;
        Receiver = findPerson(dto.getReceiverId());
        if (Receiver == null) {
            throw new PersonNotFoundException(UUID.fromString(dto.getReceiverId()),null);
        }

        entity.setSender(sender);
        entity.setReceiver(Receiver);

        MonetaryAmount moneyAmount = Money.of(dto.getAmount(), dto.getCurrencyCode());
        entity.setMonetaryAmount(moneyAmount);

        return entity;
    }

    public MoneyTransactionDto convertMoneyTransactionDtoInCurrency(MoneyTransactionDto dto,
                                                                    String currencyCode){
        if (currencyCode.equals(dto.getCurrencyCode())) {
            return dto;
        }

        MoneyTransactionDto result = new MoneyTransactionDto();
        result.setId(dto.getId());

        result.setDateTime(dto.getDateTime());

        result.setSenderId(dto.getSenderId());
        result.setReceiverId(dto.getReceiverId());

        result.setSenderName(dto.getSenderName());
        result.setReceiverName(dto.getReceiverName());

        result.setSenderSurName(dto.getSenderSurName());
        result.setReceiverSurName(dto.getReceiverSurName());

        result.setCurrencyCode(currencyCode);

        String baseCurrencyCode = currencyExchangeRateService.getBaseCurrency();
        if (baseCurrencyCode.equals(currencyCode)) {
            result.setAmount(dto.getAmount()
                    .multiply(BigDecimal.valueOf(
                            currencyExchangeRateService.getPurschaseExchangeRate(dto.getCurrencyCode()))));
        }
        else {
            result.setAmount(dto.getAmount()
                    .divide(BigDecimal.valueOf(
                            currencyExchangeRateService.getSellExchangeRate(currencyCode)),
                            2, RoundingMode.HALF_EVEN));
        }

        return result;
    }

    private  Person findPerson(String personUuid) {
        Person result = null;
        try {
            result = studentService.convertToEntity(
                    studentService.findStudent(UUID.fromString(personUuid)));
        } catch (StudentNotFoundException e) {
        }

        if (result == null) {
            try {
                result = teacherService.convertToEntity(
                        teacherService.findTeacher(UUID.fromString(personUuid)));
            } catch (TeacherNotFoundException e) {
            }
        }
        return result;
    }

}