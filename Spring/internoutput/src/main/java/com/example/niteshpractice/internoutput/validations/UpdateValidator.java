package com.example.niteshpractice.internoutput.validations;

import com.example.niteshpractice.internoutput.dto.PersonDTO;
import com.example.niteshpractice.internoutput.exceptions.EmailAlreadyExistsException;
import com.example.niteshpractice.internoutput.exceptions.WeakPasswordException;

import com.example.niteshpractice.internoutput.repositories.PersonRepository;
import com.example.niteshpractice.internoutput.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateValidator {

    @Autowired
    private PersonRepository personRepository;

    public void updateValidate(PersonDTO personDTO){

        if (personRepository.findByPrimaryEmail(personDTO.getPrimaryEmail()) != null &&
                !personDTO.getUuid().equals(
                        personRepository.findByPrimaryEmail(personDTO.getPrimaryEmail()).getUuid())
        ) {
            throw new EmailAlreadyExistsException("Primary email is already in use by another user.");
        }

        if (!personDTO.getPassword().equals(personRepository.findByUuid(personDTO.getUuid()).getPassword())) {

            if (!personDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                throw new WeakPasswordException("Password must be at least 8 characters long and include one " +
                        "uppercase letter, one lowercase letter, one number, and one special character.");
            }

            personDTO.setPassword(PasswordUtil.hashPassword(personDTO.getPassword()));
        }
    }
}
