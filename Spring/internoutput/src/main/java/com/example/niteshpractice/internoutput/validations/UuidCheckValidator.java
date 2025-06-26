package com.example.niteshpractice.internoutput.validations;

import com.example.niteshpractice.internoutput.entities.PersonEntity;
import com.example.niteshpractice.internoutput.exceptions.UuidNotFoundException;
import com.example.niteshpractice.internoutput.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidCheckValidator {

    @Autowired
    private PersonRepository personRepository;

    public PersonEntity uuidCheckValidate(UUID uuid) {
        PersonEntity person = personRepository.findByUuid(uuid);

        if (person == null) {
            throw new UuidNotFoundException("User with UUID not found");
        }

        return person;
    }
}
