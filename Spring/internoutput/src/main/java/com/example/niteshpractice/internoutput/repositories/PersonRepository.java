package com.example.niteshpractice.internoutput.repositories;

import com.example.niteshpractice.internoutput.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    PersonEntity findByPrimaryEmail(String primaryEmail);
    PersonEntity findByUuid(UUID uuid);
}
