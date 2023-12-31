package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.MoneyTransactionDto;
import com.foxminded.ums.dto.MoneyTransactionDtoMapper;
import com.foxminded.ums.dto.MoneyTransactionWithDetailsDto;
import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.entities.User;
import com.foxminded.ums.exeptions.ErrorResponce;
import com.foxminded.ums.service.MoneyTransactionService;
import com.foxminded.ums.service.TeacherService;
import com.foxminded.ums.validation.UUID;
import com.foxminded.ums.validation.ValidCurrencyCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping(value = "/teachers")
@Tag(name = "teachers", description = "Teacher API")
public class TeachersRestController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MoneyTransactionService moneyTransactionService;

    @Autowired
    private MoneyTransactionDtoMapper mapper;

    @Operation(summary = "Show List of Teachers page by page",
            description = "Show One Page of List of Teachers",
            tags = {"teachers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = TeacherDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<TeacherDto>> findTeachers(
            @Parameter(description = "page > 0 (default = 0), size > 1 (default = 5); masked by default values")
            @PageableDefault(page = 0, size = 5) Pageable pageable) {
            List<TeacherDto> teacherDtos = teacherService.findTeachersPageable(pageable);

            return ResponseEntity.ok().body(teacherDtos);
    }

    @Operation(summary = "Add new Teacher",
            description = "",
            tags = {"teachers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
            @Content(schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @PostMapping(consumes = { "application/json"}, produces = { "application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TeacherDto> addTeacher(
            @Parameter(description = "Student to add. Cannot null or empty",
                    required = true, schema = @Schema(implementation = TeacherDto.class))
            @Valid @RequestBody TeacherDto teacherDto) {
            TeacherDto addedTeacher = teacherService.addTeacher(teacherDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(addedTeacher);
    }

    @Operation(summary = "Find Teacher by ID",
            description = "Returns one Teacher with ID", tags = { "teachers" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TeacherDto> findTeacher(
            @Parameter(description = "Teacher UUID", required = true)
            @Valid @PathVariable("id") @UUID String id) {
            java.util.UUID teacherId = java.util.UUID.fromString(id);
            TeacherDto teacherDto = teacherService.findTeacher(teacherId);

            return ResponseEntity.ok().body(teacherDto);
    }

    @Operation(summary = "Update existed Teacher",
            description = "",
            tags = {"teachers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = { "application/json"},
            produces = { "application/json"} )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TeacherDto> updateTeacher(
            @Parameter(description = "Teacher to update. Cannot null or empty", required = true)
            @Valid @RequestBody TeacherDto teacherDto,
            @Parameter(description = "Teacher UUID", required = true)
            @Valid @PathVariable("id") @UUID String id) {
            java.util.UUID teacherId = java.util.UUID.fromString(id);
            teacherDto.setId(teacherId);

            TeacherDto updatedTeacher = teacherService.updateTeacher(teacherDto);

            return ResponseEntity.ok().body(updatedTeacher);
    }

    @Operation(summary = "Delete existed Student",
            description = "",
            tags = {"teachers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content =
            @Content(schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TeacherDto> deleteTeacher(
            @Parameter(description = "Teacher to delete. Cannot null or empty", required = true)
            @Valid @PathVariable("id") @UUID String id) {
            java.util.UUID teacherId = java.util.UUID.fromString(id);
            teacherService.deleteTeacher(teacherId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Operation(summary = "Find Teacher MoneyTransactions Show Amounts In Currency",
            description = "",
            tags = {"Teachers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = MoneyTransactionWithDetailsDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}/money-transactions", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<List<MoneyTransactionWithDetailsDto>> findTeacherMoneyTransactions(
            @Parameter(description = "Owner UUID. Cannot null or empty", required = true)
            @Valid @PathVariable("id") @UUID String ownerId,
            @Parameter(description = "Currency Code. Cannot null or empty", required = true)
            @Valid@RequestParam("currency") @ValidCurrencyCode String currency,
            Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            java.util.UUID userUuid = ((User) authentication.getPrincipal()).getId();
            if (!userUuid.toString().equals(ownerId)) {
                throw new AccessDeniedException("Only owner with UUID: " + ownerId + " may get transactions");
            }
        }

        List<MoneyTransactionDto> moneyTransactionDto =
                moneyTransactionService.getMoneyTransactionsByOwner(ownerId, currency);
        List<MoneyTransactionWithDetailsDto> moneyTransactionWithDetailsDtos =
                moneyTransactionDto.stream().map((e) -> mapper.convertMoneyTransactionDto(e))
                        .collect(Collectors.toList());

        return ResponseEntity.ok().body(moneyTransactionWithDetailsDtos);
    }

}
