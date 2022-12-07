package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.exeptions.ErrorResponce;
import com.foxminded.ums.security.SecurityHelper;
import com.foxminded.ums.service.StudentService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/students")
@Tag(name = "students", description = "Student API")
public class StudentsRestController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SecurityHelper securityHelper;

    @Operation(summary = "Show List of Students page by page",
            description = "Show One Page of List of Students",
            tags = {"students"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = StudentDto.class)))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StudentDto>> findStudents(
            @Parameter(description = "page > 0 (default = 0), size > 1 (default = 5); masked by default values")
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            Authentication authentication) {
        List<StudentDto> studentDtos = studentService.findStudentsPageable(pageable);

        if (securityHelper.isAdmin(authentication) || securityHelper.isTeacher(authentication)) {
            return ResponseEntity.ok().body(studentDtos);
        }

        if (securityHelper.isStudent(authentication)) {
            throw new AccessDeniedException("Endpoint  forbiden for Student");
        }

        throw new AccessDeniedException("Endpoint allowed only for Admins and Teachers");
    }

    @Operation(summary = "Add new Student",
            description = "",
            tags = {"students"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
            @Content(schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @PostMapping(consumes = { "application/json"}, produces = { "application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDto> addStudent(
            @Parameter(description = "Student to add. Cannot null or empty",
            required = true, schema = @Schema(implementation = StudentDto.class))
            @Valid @RequestBody StudentDto studentDto,
            Authentication authentication) {

        if (securityHelper.isAdmin(authentication)){
        StudentDto addedStudent = studentService.addStudent(studentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
        }

        throw new AccessDeniedException("Endpoint allowed only for Admins");
    }

    @Operation(summary = "Find Student by ID",
            description = "Returns one Student with ID", tags = { "students" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentDto> findStudent(
            @Parameter(description = "Student UUID", required = true)
            @Valid @PathVariable("id") @UUID String id,
            Authentication authentication) {
        if (securityHelper.isAdmin(authentication)) {
            java.util.UUID studentId = java.util.UUID.fromString(id);

            StudentDto studentDto = studentService.findStudent(studentId);

            return ResponseEntity.ok().body(studentDto);
        }

        throw new AccessDeniedException("Endpoint allowed only for Admins");
    }

    @Operation(summary = "Update existed Student",
            description = "",
            tags = {"students"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
            @Content(schema = @Schema(implementation = StudentDto.class))),
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
    public ResponseEntity<StudentDto> updateStudent(
            @Parameter(description = "Student to update. Cannot null or empty", required = true)
            @Valid @RequestBody StudentDto studentDto,
            @Parameter(description = "Student UUID", required = true)
            @Valid @PathVariable("id") @UUID String id,
            Authentication authentication) {

        if (securityHelper.isAdmin(authentication)) {
            java.util.UUID studentId = java.util.UUID.fromString(id);
            studentDto.setId(studentId);
            StudentDto updatedStudent = studentService.updateStudent(studentDto);

            return ResponseEntity.ok().body(updatedStudent);
        }

        throw new AccessDeniedException("Endpoint allowed only for Admins and Teachers");
    }

    @Operation(summary = "Delete existed Student",
            description = "",
            tags = {"students"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content =
            @Content(schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content =
            @Content(schema = @Schema(implementation = ErrorResponce.class)))
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<StudentDto> deleteStudent(
            @Parameter(description = "Student to delete. Cannot null or empty", required = true)
            @Valid @PathVariable("id") @UUID String id,
            Authentication authentication) {

        if (securityHelper.isAdmin(authentication)) {
            java.util.UUID studentId = java.util.UUID.fromString(id);
            studentService.deleteStudent(studentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new AccessDeniedException("Endpoint allowed only for Admins");
    }

}
