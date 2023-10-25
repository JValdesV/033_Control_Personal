package com.empresa.mvc.controldepersonal.models;
public enum Region {
    REGIÓN_DE_ARICA_Y_PARINACOTA("Región de Arica y Parinacota"),
    REGIÓN_DE_TARAPACÁ("Región de Tarapacá"),
    REGIÓN_DE_ANTOFAGASTA("Región de Antofagasta"),
    REGIÓN_DE_ATACAMA("Región de Atacama"),
    REGIÓN_DE_COQUIMBO("Región de Coquimbo"),
    REGIÓN_DE_VALPARAÍSO("Región de Valparaíso"),
    REGIÓN_METROPOLITANA_DE_SANTIAGO("Región Metropolitana de Santiago"),
    REGIÓN_DEL_LIBERTADOR_GENERAL_BERNARDO_OHIGGINS("Región del Libertador General Bernardo O'Higgins"),
    REGIÓN_DEL_MAULE("Región del Maule"),
    REGIÓN_DE_ÑUBLE("Región de Ñuble"),
    REGIÓN_DEL_BIOBÍO("Región del Biobío"),
    REGIÓN_DE_LA_ARAUCANÍA("Región de la Araucanía"),
    REGIÓN_DE_LOS_RÍOS("Región de Los Ríos"),
    REGIÓN_DE_LOS_LAGOS("Región de Los Lagos"),
    REGIÓN_DE_AYSÉN_DEL_GENERAL_CARLOS_IBÁÑEZ_DEL_CAMPO("Región de Aysén del General Carlos Ibáñez del Campo"),
    REGIÓN_DE_MAGALLANES_Y_DE_LA_ANTÁRTICA_CHILENA("Región de Magallanes y de la Antártica Chilena");

    private String nombre;

    Region(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
