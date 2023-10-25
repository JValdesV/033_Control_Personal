package com.empresa.mvc.controldepersonal.repositories;

import com.empresa.mvc.controldepersonal.models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion,Long> {
}
