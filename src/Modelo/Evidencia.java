/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author cesar
 */
public class Evidencia {
    private String nombre,evidencia;
    private Timestamp inicio,termino;

    public Evidencia(String nombre, String evidencia, Timestamp inicio, Timestamp termino) {
        this.nombre = nombre;
        this.evidencia = evidencia;
        this.inicio = inicio;
        this.termino = termino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public Timestamp getInicio() {
        return inicio;
    }

    public void setInicio(Timestamp inicio) {
        this.inicio = inicio;
    }

    public Timestamp getTermino() {
        return termino;
    }

    public void setTermino(Timestamp termino) {
        this.termino = termino;
    }
    
    
}
