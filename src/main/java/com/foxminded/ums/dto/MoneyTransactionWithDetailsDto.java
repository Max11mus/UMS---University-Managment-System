
package com.foxminded.ums.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foxminded.ums.validation.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MoneyTransactionWithDetailsDto {
    private java.util.UUID id;
    private LocalDateTime dateTime;

    @UUID
    @NotNull
    @NotBlank
    private String ownerId;
    private String ownerName;
    private String ownerSurName;

    @UUID
    @NotNull
    @NotBlank
    private String receiverId;
    private String receiverName;
    private String receiverSurName;

    private String currencyCode;

    private String amount;
}
