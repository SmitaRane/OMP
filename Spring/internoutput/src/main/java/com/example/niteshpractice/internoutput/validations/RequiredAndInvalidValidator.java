package com.example.niteshpractice.internoutput.validations;

import com.example.niteshpractice.internoutput.dto.PersonDTO;
import com.example.niteshpractice.internoutput.exceptions.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class RequiredAndInvalidValidator {

    private static final List<String> ALLOWED_STATUSES = Arrays.asList(
            "reg", "assigned", "prof_gen", "active", "purge"
    );

    public void requiredAndInvalidValidate(PersonDTO personDTO) {
        // === Required Field Checks ===
        if (personDTO.getFirstname() == null || personDTO.getFirstname().trim().isEmpty()) {
            throw new RequiredException("First Name is required");
        }

        if (personDTO.getLastname() == null || personDTO.getLastname().trim().isEmpty()) {
            throw new RequiredException("Last Name is required");
        }

        if (personDTO.getPrimaryEmail() == null || personDTO.getPrimaryEmail().trim().isEmpty()) {
            throw new RequiredException("Primary Email is required");
        }

        if (!personDTO.getPrimaryEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new InvalidEmailException("Primary Email format is invalid");
        }

        if (personDTO.getSecondaryEmail() == null || personDTO.getSecondaryEmail().trim().isEmpty()) {
            throw new RequiredException("Secondary Email is required");
        }

        if (!personDTO.getSecondaryEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new InvalidEmailException("Secondary Email format is invalid");
        }

        if (personDTO.getContact() == null || personDTO.getContact().trim().isEmpty()) {
            throw new RequiredException("Contact number is required");
        }

        if (!personDTO.getContact().matches("^\\d{10}$")) {
            throw new InvalidContactException("Contact number must be exactly 10 digits");
        }

        if (!personDTO.getContact().matches("\\d+")) {
            throw new InvalidContactException("Contact number must contain only digits");
        }

        if (personDTO.getEducation() == null || personDTO.getEducation().trim().isEmpty()) {
            throw new RequiredException("Education is required");
        }

        if (personDTO.getPassword() == null || personDTO.getPassword().trim().isEmpty()) {
            throw new RequiredException("Password is required");
        }

        if (personDTO.getStatus() == null || personDTO.getStatus().trim().isEmpty()) {
            personDTO.setStatus("REG"); // default value
        }

        if (!ALLOWED_STATUSES.contains(personDTO.getStatus().toLowerCase().trim())) {
            throw new InvalidStatusException("Status must be one of: registered, assigned, profile generation, active, to be deleted");
        }
    }
}
