    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author cesar
 */
public class Estrategia {
    private String bd,nombre,sql,fec_ini,ini_ult_eje,fin_ul_eje,prox_eje;
        private int dias,freq,estado;

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public Estrategia(String bd, String nombre, String sql, String fec_ini,int estado, int freq, String ini_ult_eje, String fin_ul_eje, String prox_eje, int dias) {
        this.bd = bd;
        this.nombre = nombre;
        this.sql = sql;
        this.fec_ini = fec_ini;
        
        this.estado = estado;
        this.freq = freq;
        this.ini_ult_eje = ini_ult_eje;
        this.fin_ul_eje = fin_ul_eje;
        this.prox_eje = prox_eje;
        this.dias = dias;
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

    public String getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(String fec_ini) {
        this.fec_ini = fec_ini;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String getIni_ult_eje() {
        return ini_ult_eje;
    }

    public void setIni_ult_eje(String ini_ult_eje) {
        this.ini_ult_eje = ini_ult_eje;
    }

    public String getFin_ul_eje() {
        return fin_ul_eje;
    }

    public void setFin_ul_eje(String fin_ul_eje) {
        this.fin_ul_eje = fin_ul_eje;
    }

    public String getProx_eje() {
        return prox_eje;
    }

    public void setProx_eje(String prox_eje) {
        this.prox_eje = prox_eje;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    
}
