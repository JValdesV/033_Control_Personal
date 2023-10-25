package com.empresa.mvc.controldepersonal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rut;
    private String nombres;
    private String apellidos;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private Boolean activo;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Direccion direccion;


    public Persona() {

    }

    public Persona(Long id, String rut, String nombres, String apellidos, Sexo sexo, Date fechaNacimiento, Direccion direccion) {
        this.activo = true;
        this.id = id;
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void actualizarDatos(Persona persona) {
        if(persona.nombres != null){
            this.nombres = persona.nombres;
        }
    }

    public void desactivarPersona(){
        this.activo = false;
    }

}
