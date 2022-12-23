package com.foxminded.ums.dto;

import com.foxminded.ums.entities.Person;
import com.foxminded.ums.exeptions.PersonNotFoundException;
import com.foxminded.ums.exeptions.StudentNotFoundException;
import com.foxminded.ums.exeptions.TeacherNotFoundException;
import com.foxminded.ums.service.StudentService;
import com.foxminded.ums.service.TeacherService;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class MoneyTransactionDtoMapper {
    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @SneakyThrows
    public MoneyTransactionWithDetailsDto convertMoneyTransactionDto(MoneyTransactionDto dto) {

        MoneyTransactionWithDetailsDto result = new MoneyTransactionWithDetailsDto();
        result.setId(dto.getId());

        result.setDateTime(dto.getDateTime());

        Person owner = null;
        owner = findPerson(dto.getOwnerId());
        if (owner == null) {
            throw new PersonNotFoundException(UUID.fromString(dto.getOwnerId()), null);
        }

        Person receiver = null;
        receiver = findPerson(dto.getReceiverId());
        if (receiver == null) {
            throw new PersonNotFoundException(UUID.fromString(dto.getReceiverId()), null);
        }

        result.setOwnerId(dto.getOwnerId());
        result.setReceiverId(dto.getReceiverId());

        result.setOwnerName(owner.getName());
        result.setReceiverName(receiver.getName());

        result.setOwnerSurName(owner.getSurname());
        result.setReceiverSurName(receiver.getSurname());

        DecimalFormat df = new DecimalFormat("###,###,###,###.00");
        result.setAmount(df.format(dto.getAmount()));

        result.setCurrencyCode(dto.getCurrencyCode());

        return result;
    }

    private Person findPerson(String personUuid) {
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