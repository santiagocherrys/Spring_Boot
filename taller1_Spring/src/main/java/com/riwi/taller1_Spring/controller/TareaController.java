package com.riwi.taller1_Spring.controller;

import com.riwi.taller1_Spring.entity.Tarea;
import com.riwi.taller1_Spring.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class TareaController {

    /*
    * Inyección de dependencias
    * */
    @Autowired
    private TareaService objTareaService;

    @GetMapping
    public String showTareas(Model objModel){
        //Obtenemos la lista de las tareas a realizar
        List<Tarea> listTareas = this.objTareaService.findAll();
        objModel.addAttribute("listTareas",listTareas);

        return "showTareas";
    }

    @GetMapping("llenarInfo")
    public String showViewFill(Model model){
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("action","/create-tarea");

        return "viewFill";
    }

    @PostMapping("create-tarea")
    public String createTarea(@ModelAttribute Tarea objTarea){
        //Se obtiene la hora y fecha automática
        objTarea.setFechaCreacion(LocalDate.now());
        objTarea.setHoraCreacion(LocalTime.now());
        this.objTareaService.create(objTarea);
        return "redirect:/";
    }

    @GetMapping("busquedaNombre")
    public String showFillNombre(Model model){
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("action","/MostrarTareaNombre");

        return "viewFillNombre";
    }

    @PostMapping("MostrarTareaNombre")
    public String mostrarTareaNombre(Model objModel, @ModelAttribute Tarea objTarea){
        //Se obtiene la hora y fecha automática

        try{
            Tarea tarea = this.objTareaService.findByTitulo(objTarea.getTitulo());
            System.out.println("Este es el objeto filtrado " + tarea.toString());
            objModel.addAttribute("tarea",tarea);
        }catch (Exception e){
            System.out.println("No se encontraron coincidencias");
        }


        return "showTareas2";
    }

    //Borrar
    @GetMapping("/delete/{id}")
    public String deleteTarea(@PathVariable Long id){
        this.objTareaService.delete(id);

        //Redirecciona a la lista de Tareas
        return "redirect:/";
    }

    //Actualizar
    @GetMapping("/update/{id}")
    public String updateTarea(@PathVariable Long id, Model model){
    Tarea objTarea = this.objTareaService.findById(id);
    model.addAttribute("tarea", objTarea);
    model.addAttribute("action","/edit/" + id);

    return "viewFill";
    }

    @PostMapping("edit/{id}")
    public String updateTarea(@PathVariable Long id, @ModelAttribute Tarea objTarea){


        this.objTareaService.update(id, objTarea);

        return "redirect:/";
    }

}
