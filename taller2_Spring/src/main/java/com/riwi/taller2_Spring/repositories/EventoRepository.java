package com.riwi.taller2_Spring.repositories;

import com.riwi.taller2_Spring.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String> {

}
