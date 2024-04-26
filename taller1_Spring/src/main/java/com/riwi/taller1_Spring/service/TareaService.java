package com.riwi.taller1_Spring.service;

import com.riwi.taller1_Spring.entity.Tarea;
import com.riwi.taller1_Spring.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Se crean las funciones del CRUD
@Service
public class TareaService {

    //Autowired Registra la inyección de dependencias en SPRING BOOT
    @Autowired
    private TareaRepository objTareaRepository;


    //Buscar todo
    public List<Tarea> findAll(){
        return this.objTareaRepository.findAll();
    }

    //Crear una nueva tarea en base de datos
    public Tarea create(Tarea objTarea){
        return this.objTareaRepository.save(objTarea);
    }

    //Elimina una tarea
    public void delete(Long id){
        this.objTareaRepository.deleteById(id);
    }

    //buscar por Id
    public Tarea findById(Long id){
        //Busca tarea por ID y en  caso de no ser encontrado devuelve null
        return this.objTareaRepository.findById(id).orElse(null);
    }

    //Update
    public Tarea update(Long id, Tarea coder){

        //Se busca el coder por ID
        Tarea objTareaDB = this.findById(id);

        if(objTareaDB == null) return null;

        /*Actualizar el coder viejo*/
        objTareaDB = coder;
        /*
        *El método save verifica, si el objeto tiene la llave primaria*/

        return this.objTareaRepository.save(objTareaDB);

    }

    public Tarea findByTitulo(String titulo){
        return this.objTareaRepository.findByTitulo(titulo);
    }



}
