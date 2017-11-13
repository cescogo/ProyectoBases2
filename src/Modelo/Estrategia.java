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
public class Estrategia {
    private String bd,nombre,sql;
        private int dias,freq,estado,ini_rang,fin_ran;
        private  Timestamp fec_ini,ini_ult_eje,fin_ul_eje,prox_eje;

    public Estrategia(String bd, String nombre, String sql, int dias, int freq, int estado, int ini_rang, int fin_ran, Timestamp fec_ini, Timestamp ini_ult_eje, Timestamp fin_ul_eje, Timestamp prox_eje) {
        this.bd = bd;
        this.nombre = nombre;
        this.sql = sql;
        this.dias = dias;
        this.freq = freq;
        this.estado = estado;
        this.ini_rang = ini_rang;
        this.fin_ran = fin_ran;
        this.fec_ini = fec_ini;
        this.ini_ult_eje = ini_ult_eje;
        this.fin_ul_eje = fin_ul_eje;
        this.prox_eje = prox_eje;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIni_rang() {
        return ini_rang;
    }

    public void setIni_rang(int ini_rang) {
        this.ini_rang = ini_rang;
    }

    public int getFin_ran() {
        return fin_ran;
    }

    public void setFin_ran(int fin_ran) {
        this.fin_ran = fin_ran;
    }

    public Timestamp getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(Timestamp fec_ini) {
        this.fec_ini = fec_ini;
    }

    public Timestamp getIni_ult_eje() {
        return ini_ult_eje;
    }

    public void setIni_ult_eje(Timestamp ini_ult_eje) {
        this.ini_ult_eje = ini_ult_eje;
    }

    public Timestamp getFin_ul_eje() {
        return fin_ul_eje;
    }

    public void setFin_ul_eje(Timestamp fin_ul_eje) {
        this.fin_ul_eje = fin_ul_eje;
    }

    public Timestamp getProx_eje() {
        return prox_eje;
    }

    public void setProx_eje(Timestamp prox_eje) {
        this.prox_eje = prox_eje;
    }

    
}
