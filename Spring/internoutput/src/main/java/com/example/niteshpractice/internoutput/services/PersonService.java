package com.example.niteshpractice.internoutput.services;

import com.example.niteshpractice.internoutput.dto.PersonDTO;
import com.example.niteshpractice.internoutput.entities.PersonEntity;
import com.example.niteshpractice.internoutput.repositories.PersonRepository;
import com.example.niteshpractice.internoutput.utils.PasswordUtil;
import com.example.niteshpractice.internoutput.validations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private RequiredAndInvalidValidator requiredAndInvalidValidator;

    @Autowired
    private PersonLoginValidator personLoginValidator;

    @Autowired
    private UuidCheckValidator uuidCheckValidator;

    @Autowired
    private CreateValidator createValidator;

    @Autowired
    private UpdateValidator updateValidator;

    final PersonRepository personRepository;

    final ModelMapper modelMapper;

    public PersonService(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

//    public PersonDTO getPersonById(Long id){
//        PersonEntity personEntity = personRepository.getById(id);
//        return modelMapper.map(personEntity, PersonDTO.class);
//    }

    public PersonDTO createNewPerson(PersonDTO personDTO) {
        requiredAndInvalidValidator.requiredAndInvalidValidate(personDTO);
        createValidator.createValidate(personDTO);

        String hashedPassword = PasswordUtil.hashPassword(personDTO.getPassword());
        personDTO.setPassword(hashedPassword);

        PersonEntity personEntity = modelMapper.map(personDTO, PersonEntity.class);
        return modelMapper.map(personRepository.save(personEntity), PersonDTO.class);
    }

    public List<PersonDTO> getAllPersons() {
        return personRepository
                .findAll()
                .stream()
                .map(personEntity -> modelMapper.map(personEntity, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public boolean deletePersonByUuid(UUID uuid) {
        PersonEntity personEntity = uuidCheckValidator.uuidCheckValidate(uuid);
        personEntity.setStatus("TBD");
        personRepository.save(personEntity);
        return true;
    }

    public PersonDTO getPersonByCredentials(PersonDTO personDTO) {
        PersonEntity personEntity = personLoginValidator.loginValidate(personDTO);
        return modelMapper.map(personEntity, PersonDTO.class);

    }

    public PersonDTO updatePerson(PersonDTO personDTO) {
        PersonEntity personEntity = uuidCheckValidator.uuidCheckValidate(personDTO.getUuid());
        updateValidator.updateValidate(personDTO);
        requiredAndInvalidValidator.requiredAndInvalidValidate(personDTO);

        modelMapper.map(personDTO, personEntity);

        return modelMapper.map(personRepository.save(personEntity), PersonDTO.class);
    }

}
