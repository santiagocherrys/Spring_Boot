package com.riwi.taller1_Spring.repository;

import com.riwi.taller1_Spring.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* El repositorio se encarga de toda la persistencia de los datos
* interactua diretamente con la Base de datos.
* */

@Repository
/*Extendemos de la interfaz de JPA para obtener todos los méotodos del CRUD*/
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    Tarea findByTitulo(String titulo);

}
