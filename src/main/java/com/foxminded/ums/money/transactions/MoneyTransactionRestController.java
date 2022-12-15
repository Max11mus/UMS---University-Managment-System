package com.foxminded.ums.money.transactions;

import com.foxminded.ums.entities.User;
import com.foxminded.ums.exeptions.ErrorResponce;
import com.foxminded.ums.validation.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/moneytransactions")
@Tag(name = "moneytransactions", description = "MoneyTransaction API")
public class MoneyTransactionRestController {

    @Autowired
    private MoneyTransactionService moneyTransactionService;

    @Operation(summary = "Show List of MoneyTransactions",
            description = "Show List of MoneyTransactions",
            tags = {"moneytransactions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = MoneyTransactionDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<MoneyTransactionDto>> findMoneyTransactions()
    {
        List<MoneyTransactionDto> moneyTransactionsDtos = moneyTransactionService.findMoneyTransactions();
                    return ResponseEntity.ok().body(moneyTransactionsDtos);
    }

    @Operation(summary = "Show List of Sender MoneyTransactions Show Amounts In UAH",
            description = "Show List of Sender MoneyTransactions Show Amounts In UAH",
            tags = {"moneytransactions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = MoneyTransactionDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/SenderUAH", method = RequestMethod.GET, params = {"senderUUID"})
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<List<MoneyTransactionDto>> findMoneyTransactionsBySenderShowAmountsInUAH(
            @Valid @Parameter(description = "Sender UUID. Cannot null or empty", required = true)
            @RequestParam(value = "senderUUID") @UUID String senderUuid, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            java.util.UUID userUuid = ((User) authentication.getPrincipal()).getId();
            if (!userUuid.toString().equals(senderUuid)) {
                throw new AccessDeniedException("Only user with UUID: " + senderUuid + " may get transactions");
            }
        }
            List<MoneyTransactionDto> moneyTransactionsDtos = moneyTransactionService
                    .findMoneyTransactionsBySenderInCurrency(java.util.UUID.fromString(senderUuid), "UAH");
            return ResponseEntity.ok().body(moneyTransactionsDtos);
    }

    @Operation(summary = "Show List of Receiver MoneyTransactions Show Amounts In UAH",
            description = "Show List of Receiver MoneyTransactions Show Amounts In UAH",
            tags = {"moneytransactions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = MoneyTransactionDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/ReceiverUAH", method = RequestMethod.GET, params = {"receiverUUID"})
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<List<MoneyTransactionDto>> findMoneyTransactionsByReceiverShowAmountsInUAH(
            @Valid @Parameter(description = "Receiver UUID. Cannot null or empty", required = true)
            @RequestParam(value = "receiverUUID") @UUID String receiverUuid, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            java.util.UUID userUuid = ((User) authentication.getPrincipal()).getId();
            if (!userUuid.toString().equals(receiverUuid)) {
                throw new AccessDeniedException("Only user with UUID: " + receiverUuid + " may get transactions");
            }
        }
        List<MoneyTransactionDto> moneyTransactionsDtos = moneyTransactionService
                .findMoneyTransactionsBySenderInCurrency(java.util.UUID.fromString(receiverUuid), "UAH");
        return ResponseEntity.ok().body(moneyTransactionsDtos);
    }

    @Operation(summary = "Show List of Sender MoneyTransactions Show Amounts In USD",
            description = "Show List of Sender MoneyTransactions Show Amounts In USD",
            tags = {"moneytransactions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = MoneyTransactionDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/SenderUSD", method = RequestMethod.GET, params = {"senderUUID"})
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<List<MoneyTransactionDto>> findMoneyTransactionsBySenderShowAmountsInUSD(
            @Valid @Parameter(description = "Sender UUID. Cannot null or empty", required = true)
            @RequestParam(value = "senderUUID") @UUID String senderUuid, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            java.util.UUID userUuid = ((User) authentication.getPrincipal()).getId();
            if (!userUuid.toString().equals(senderUuid)) {
                throw new AccessDeniedException("Only user with UUID: " + senderUuid + " may get transactions");
            }
        }
        List<MoneyTransactionDto> moneyTransactionsDtos = moneyTransactionService
                .findMoneyTransactionsBySenderInCurrency(java.util.UUID.fromString(senderUuid), "USD");
        return ResponseEntity.ok().body(moneyTransactionsDtos);
    }

    @Operation(summary = "Show List of Receiver MoneyTransactions Show Amounts In USD",
            description = "Show List of Receiver MoneyTransactions Show Amounts In USD",
            tags = {"moneytransactions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = MoneyTransactionDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/ReceiverUSD", method = RequestMethod.GET, params = {"receiverUUID"})
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<List<MoneyTransactionDto>> findMoneyTransactionsByReceiverShowAmountsInUSD(
            @Valid @Parameter(description = "Receiver UUID. Cannot null or empty", required = true)
            @RequestParam(value = "receiverUUID") @UUID String receiverUuid, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            java.util.UUID userUuid = ((User) authentication.getPrincipal()).getId();
            if (!userUuid.toString().equals(receiverUuid)) {
                throw new AccessDeniedException("Only user with UUID: " + receiverUuid + " may get transactions");
            }
        }
        List<MoneyTransactionDto> moneyTransactionsDtos = moneyTransactionService
                .findMoneyTransactionsBySenderInCurrency(java.util.UUID.fromString(receiverUuid), "USD");
        return ResponseEntity.ok().body(moneyTransactionsDtos);
    }


    @Operation(summary = "Add new MoneyTransaction",
            description = "",
            tags = {"moneytransactions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
            @Content(schema = @Schema(implementation = MoneyTransactionDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @PostMapping(consumes = { "application/json"}, produces = { "application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MoneyTransactionDto> addMoneyTransaction(
            @Parameter(description = "MoneyTransaction to add. Cannot null or empty",
            required = true, schema = @Schema(implementation = MoneyTransactionDto.class))
            @Valid @RequestBody MoneyTransactionDto moneyTransactionDto) {
        MoneyTransactionDto addedMoneyTransaction = moneyTransactionService.addMoneyTransaction(moneyTransactionDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedMoneyTransaction);
    }

    @Operation(summary = "Find MoneyTransaction by ID",
            description = "Returns one MoneyTransaction with ID", tags = { "moneytransactions" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(schema = @Schema(implementation = MoneyTransactionDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MoneyTransactionDto> findMoneyTransaction(
            @Parameter(description = "MoneyTransaction UUID", required = true)
            @Valid @PathVariable("id") @UUID String id) {
            java.util.UUID moneyTransactionId = java.util.UUID.fromString(id);
        MoneyTransactionDto MoneyTransactionDto = moneyTransactionService.findMoneyTransaction(moneyTransactionId);
            return ResponseEntity.ok().body(MoneyTransactionDto);
    }

    @Operation(summary = "Update existed MoneyTransaction",
            description = "",
            tags = {"moneytransactions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(schema = @Schema(implementation = MoneyTransactionDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = { "application/json"},
            produces = { "application/json"} )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MoneyTransactionDto> updateMoneyTransaction(
            @Parameter(description = "MoneyTransaction to update. Cannot null or empty", required = true)
            @Valid @RequestBody MoneyTransactionDto MoneyTransactionDto,
            @Parameter(description = "MoneyTransaction UUID", required = true)
            @Valid @PathVariable("id") @UUID String id) {
        java.util.UUID moneyTransactionId = java.util.UUID.fromString(id);
        MoneyTransactionDto.setId(moneyTransactionId);
        MoneyTransactionDto updatedMoneyTransaction = moneyTransactionService.updateMoneyTransaction(MoneyTransactionDto);

            return ResponseEntity.ok().body(updatedMoneyTransaction);
    }

    @Operation(summary = "Delete existed MoneyTransaction",
            description = "",
            tags = {"moneytransactions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content =
            @Content(schema = @Schema(implementation = MoneyTransactionDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MoneyTransactionDto> deleteMoneyTransaction(
            @Parameter(description = "MoneyTransaction to delete. Cannot null or empty", required = true)
            @Valid @PathVariable("id") @UUID String id) {
            java.util.UUID moneyTransactionId = java.util.UUID.fromString(id);
            moneyTransactionService.deleteMoneyTransaction(moneyTransactionId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
