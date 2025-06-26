package com.example.niteshpractice.internoutput.validations;

import com.example.niteshpractice.internoutput.dto.PersonDTO;
import com.example.niteshpractice.internoutput.entities.PersonEntity;
import com.example.niteshpractice.internoutput.exceptions.EmailNotFoundException;
import com.example.niteshpractice.internoutput.exceptions.InvalidPasswordException;
import com.example.niteshpractice.internoutput.repositories.PersonRepository;
import com.example.niteshpractice.internoutput.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonLoginValidator {
    @Autowired
    private PersonRepository personRepository;

    public PersonEntity loginValidate(PersonDTO personDTO) {
        PersonEntity personEntity = personRepository.findByPrimaryEmail(personDTO.getPrimaryEmail());

        if (personEntity == null) {
            throw new EmailNotFoundException("Email does not exist");
        }

        String hashedInputPassword = PasswordUtil.hashPassword(personDTO.getPassword());

        if (!personEntity.getPassword().equals(hashedInputPassword)) {
            throw new InvalidPasswordException("Incorrect password");
        }

        return personEntity;
    }
}
