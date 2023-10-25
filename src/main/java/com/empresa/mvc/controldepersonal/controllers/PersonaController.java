package com.empresa.mvc.controldepersonal.controllers;


import com.empresa.mvc.controldepersonal.models.Direccion;
import com.empresa.mvc.controldepersonal.models.Persona;
import com.empresa.mvc.controldepersonal.repositories.DireccionRepository;
import com.empresa.mvc.controldepersonal.repositories.PersonaRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @GetMapping({"","/"})
    public String listarPersonas(Model model,@PageableDefault(size = 20) Pageable pagination){
        //Page<Persona> personasPage = personaRepository.findAll(pagination);
        Page<Persona> personasPage = personaRepository.findByActivoTrue(pagination);
        model.addAttribute("personas",personasPage);
        return "personas/listapersonas";
    }

    @GetMapping("/{id}")
    public String listarPersona(Model model, @PathVariable("id") Long id){
        Optional<Persona> personaOpt = personaRepository.findById(id);

        if(personaOpt.isPresent()){
            Persona persona = personaOpt.get();
            model.addAttribute("persona", persona);
            return "personas/mostrarEntidad";
        }else{
            return "personas/mostrarEntidad";
        }
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioPersona(Model model){
        //Inyeccion de objetos de manera independientes
        model.addAttribute("persona", new Persona());
        model.addAttribute("direccion", new Direccion());
        return "personas/formularioPersona";
    }

    @PostMapping("/guardar")
    public String guardarPersona(@ModelAttribute("persona") Persona persona,
                                 @ModelAttribute("direccion") Direccion direccion){

        persona.setDireccion(direccion);
        direccion.setPersona(persona);
        personaRepository.save(persona);

        return "redirect:/personas/nuevo";
    }

    @GetMapping("/{id}/editar")
    public String editarPersona(@PathVariable("id") Long id,Model model){
        Optional<Persona> persona = personaRepository.findById(id);
        if(persona.isEmpty()){
            return "redirect:/personas";
        }else{
            model.addAttribute("persona",persona);
        }
        return "personas/editarPersona";
    }

    @PutMapping("/{id}")
    @Transactional
    public String actualizarPersona(@ModelAttribute("persona") Persona persona, BindingResult result){
        System.out.println("Pase por el metodo actualizarPersona");
        if(result.hasErrors()){
            System.out.println("Pase por el error");
            return "personas/editarPersona";
        }else{
            System.out.println("Pase por el guardar");
            Persona personaUpdate = personaRepository.getReferenceById(persona.getId());
            personaUpdate.actualizarDatos(persona);
            return "redirect:/personas";
        }
    }

    @DeleteMapping("/{id}/eliminar")
    @Transactional
    public String eliminarPersona(@PathVariable("id") Long id){
        Persona persona = personaRepository.getReferenceById(id);
        persona.desactivarPersona();
        return "redirect:/personas";
    }











    //Ruta para interactuar con la libreria hora
    //Y trabajar con las sesiones
    @GetMapping("/hora")
    @ResponseBody
    public String devolverHora(HttpSession session){
        //Trabajar con las sesiones
        Integer count = (Integer) session.getAttribute("count");
        if(count==null){
            count = 0;
        }
        count++;
        session.setAttribute("count",count);

        //Trabajando con fechas y aplicando un formato personalizado
        Date fecha = new Date();
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

        //Revocar la sesion transcurrido 1 minuto en el sistema
        long tiempoActual = System.currentTimeMillis();
        long tiempoSessionCreated = session.getCreationTime();
        long tiempoSessionLastInteraction = session.getLastAccessedTime();

        //Determinar la diferencia entre estos 2 tiempos
        //long tiempoDeInteraccion = (tiempoActual - tiempoSessionCreated) / (1000*60);
        long tiempoDeInteraccion = (tiempoActual - tiempoSessionLastInteraction) / (1000*60);

        if(tiempoDeInteraccion>=1){
            session.invalidate();
            return "La variable count perteneciente a la session tiene el valor: "+count+"| La fecha creada en el sistema es: "+datetimeFormat.format(tiempoSessionCreated)+"| Tiempo Actual:"+datetimeFormat.format(tiempoActual);
        }else{
            return "La variable count perteneciente a la session tiene el valor: "+count+"| La fecha creada en el sistema es: "+datetimeFormat.format(tiempoSessionCreated)+"| Tiempo Actual:"+datetimeFormat.format(tiempoActual);
        }


    }

    @GetMapping("/contenido")
    public String mostrarContenido(Model model, @RequestParam(value = "codigo", required = false) String codigo){
        model.addAttribute("codigo",codigo);
        return "contenido/contenido";
    }

    @GetMapping("/error")
    public String mensajeFlush(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error","Te enviamos un error de prueba!!!");
        return "redirect:/personas/contenido";
    }




}
