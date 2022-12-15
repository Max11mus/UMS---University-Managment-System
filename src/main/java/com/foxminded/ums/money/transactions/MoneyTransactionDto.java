
package com.foxminded.ums.money.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foxminded.ums.validation.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MoneyTransactionDto {
    private java.util.UUID id;
    private LocalDateTime dateTime;

    @UUID
    @NotNull
    @NotBlank
    private String senderId;
    private String senderName;
    private String senderSurName;

    @UUID
    @NotNull
    @NotBlank
    private String receiverId;
    private String receiverName;
    private String receiverSurName;

    @ValidCurrencyCode
    private String currencyCode;

    private BigDecimal amount;
}
