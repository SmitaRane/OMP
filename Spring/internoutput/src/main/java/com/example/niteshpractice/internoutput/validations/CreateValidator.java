package com.example.niteshpractice.internoutput.validations;

import com.example.niteshpractice.internoutput.dto.PersonDTO;
import com.example.niteshpractice.internoutput.exceptions.EmailAlreadyExistsException;
import com.example.niteshpractice.internoutput.exceptions.WeakPasswordException;
import com.example.niteshpractice.internoutput.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateValidator {

    @Autowired
    private PersonRepository personRepository;

    public void createValidate(PersonDTO personDTO){
        if (personRepository.findByPrimaryEmail(personDTO.getPrimaryEmail()) != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        if (!personDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            throw new WeakPasswordException(
                    "Password must be at least 8 characters long and include one uppercase letter, " +
                            "one lowercase letter, one number, and one special character."
            );
        }

        if(personDTO.getCreated_at() == null) {
            personDTO.setCreated_at(LocalDate.now());
        }
    }
}
