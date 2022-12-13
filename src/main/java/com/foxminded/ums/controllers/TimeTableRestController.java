package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.TimeTableUnitDto;
import com.foxminded.ums.entities.User;
import com.foxminded.ums.exeptions.ErrorResponce;
import com.foxminded.ums.service.StudentService;
import com.foxminded.ums.service.TimeTableService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@RestController
@Validated
@Tag(name = "timetable", description = "Timetable API")
@RequestMapping(value = "/timetable")
public class TimeTableRestController {
    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Show List of TimetableUnits For Student with ID",
            description = "",
            tags = {"timetable"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = TimeTableUnitDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, params = {"startDay", "endDay"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<List<TimeTableUnitDto>> getTimeTableForStudent(
            @Parameter(description = "Student UUID. Cannot null or empty", required = true)
            @Valid @PathVariable("id") @UUID String id,
            @Parameter(description = "Starting Day. Cannot null or empty. Format yyyy-mm-dd", required = true)
            @RequestParam(value = "startDay") String startDay,
            @Parameter(description = "Ending Day. Cannot null or empty. Format yyyy-mm-dd", required = true)
            @RequestParam(value = "endDay") String endDay,
            Authentication authentication) {

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
            java.util.UUID userUuid = ((User) authentication.getPrincipal()).getId();
            if (!userUuid.toString().equals(id)) {
            throw new AccessDeniedException("Endpoint allowed for Student with UUID: " + id);
            }
        }

            TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDayDate = LocalDate.parse(startDay, formatter);
            LocalDate endDayDate = LocalDate.parse(endDay, formatter);

            List<TimeTableUnitDto> timeTableUnitDto = timeTableService
                    .findByPeriodForStudent(java.util.UUID.fromString(id),
                            startDayDate, endDayDate);



            return ResponseEntity.ok().body(timeTableUnitDto);

    }

    @Operation(summary = "Show List of TimetableUnits For Teacher with ID",
            description = "",
            tags = {"timetable"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = TimeTableUnitDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, params = {"startDay", "endDay"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<List<TimeTableUnitDto>> getTimeTableForTeacher(
            @Parameter(description = "Teacher UUID. Cannot null or empty", required = true)
            @Valid @PathVariable("id") @UUID String id,
            @Parameter(description = "Starting Day. Cannot null or empty. Format yyyy-mm-dd", required = true)
            @RequestParam(value = "startDay") String startDay,
            @Parameter(description = "Ending Day. Cannot null or empty. Format yyyy-mm-dd", required = true)
            @RequestParam(value = "endDay") String endDay) {
            TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDayDate = LocalDate.parse(startDay, formatter);
            LocalDate endDayDate = LocalDate.parse(endDay, formatter);

            List<TimeTableUnitDto> timeTableUnitDto = null;

            timeTableUnitDto = timeTableService.findByPeriodForTeacher(java.util.UUID.fromString(id),
                    startDayDate, endDayDate);

            return ResponseEntity.ok().body(timeTableUnitDto);
    }


}
