package com.example.niteshpractice.internoutput.controllers;

//Operations
//GET /Persons
//POST /Persons
//DELETE /Persons/{id}

import com.example.niteshpractice.internoutput.dto.PersonDTO;
import com.example.niteshpractice.internoutput.responses.ApiResponse;
import com.example.niteshpractice.internoutput.services.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonDTO>>> getAllPersons(HttpServletRequest request) {
        List<PersonDTO> persons = personService.getAllPersons();
        ApiResponse<List<PersonDTO>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Persons retrieved successfully",
                request.getRequestURI(),
                persons
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PersonDTO>> createNewPerson(@RequestBody PersonDTO personDTO, HttpServletRequest request) {
        PersonDTO createdPerson = personService.createNewPerson(personDTO);
        ApiResponse<PersonDTO> response = new ApiResponse<>(
                true,
                HttpStatus.CREATED.value(),
                "Person created successfully",
                request.getRequestURI(),
                createdPerson
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<PersonDTO>> updatePerson(@RequestBody PersonDTO personDTO, HttpServletRequest request) {
        PersonDTO updatedPerson = personService.updatePerson(personDTO);
        ApiResponse<PersonDTO> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Person updated successfully",
                request.getRequestURI(),
                updatedPerson
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<ApiResponse<Boolean>> deletePersonById(@PathVariable UUID uuid, HttpServletRequest request) {
        boolean deleted = personService.deletePersonByUuid(uuid);
        ApiResponse<Boolean> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Person deleted successfully",
                request.getRequestURI(),
                deleted
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse<PersonDTO>> getPersonByCredentials(@RequestBody PersonDTO personDTO, HttpServletRequest request) {
        PersonDTO person = personService.getPersonByCredentials(personDTO);
        ApiResponse<PersonDTO> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Login successful",
                request.getRequestURI(),
                person
        );
        return ResponseEntity.ok(response);
    }
}
