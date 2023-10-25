package com.empresa.mvc.controldepersonal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private String numero;
    private String block;
    private String depto;
    private String comuna;
    private String ciudad;
    @Enumerated(EnumType.STRING)
    private Region region;
    private String codigoPostal;
    @OneToOne
    @JoinColumn(name="persona_id")
    private Persona persona;

    public Direccion() {

    }

    public Direccion(Long id, String calle, String numero, String block, String depto, String comuna, String ciudad, Region region, String codigoPostal, Persona persona) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.block = block;
        this.depto = depto;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.region = region;
        this.codigoPostal = codigoPostal;
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getDireccionString(){
        String direccion = String.format("%s %s %s %s %s",this.calle,this.numero,this.comuna,this.ciudad,this.region.getNombre());
        return direccion;
    }

}


