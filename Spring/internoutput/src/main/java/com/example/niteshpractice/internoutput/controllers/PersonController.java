package com.example.niteshpractice.internoutput.controllers;

//Operations
//GET /Persons
//POST /Persons
//DELETE /Persons/{id}

import com.example.niteshpractice.internoutput.dto.PersonDTO;
import com.example.niteshpractice.internoutput.services.PersonService;
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
    public List<PersonDTO> getAllPersons(){
        return personService.getAllPersons();
    }
//    @GetMapping(path = "/{id}")
//    public PersonDTO getPersonById(@PathVariable("id") long personID){
//        return personService.getPersonById(personID);
//    }
    
    @PostMapping
    public PersonDTO createNewPerson(@RequestBody PersonDTO personDTO){
        return personService.createNewPerson(personDTO);
    }

    @PutMapping
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO) {
        return personService.updatePerson(personDTO);
    }

    @DeleteMapping(path = "/{uuid}")
    public boolean DeletePersonById(@PathVariable UUID uuid){
        return personService.deletePersonByUuid(uuid);
    }

    @PostMapping(path = "/login")
    public PersonDTO getPersonByCredentials(@RequestBody PersonDTO personDTO){
        return personService.getPersonByCredentials(personDTO);
    }
}
