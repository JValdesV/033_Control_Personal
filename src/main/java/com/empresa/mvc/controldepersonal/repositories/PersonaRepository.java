package com.empresa.mvc.controldepersonal.repositories;

import com.empresa.mvc.controldepersonal.models.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    Page<Persona> findAll(Pageable pageable);
    Page<Persona> findByActivoTrue(Pageable pagination);

}
