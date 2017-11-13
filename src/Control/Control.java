package Control;

import java.io.IOException;
import java.sql.SQLException;
import Modelo.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import Vista.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author cesar
 */
public class Control {

    private Conexion model;
    
    
    private TableSpace tab_graf;
  
    private SQLiteJDBC sqlite;
    private Calendar fecha;
    private float aux;
    

    private VentInicio ventIni;

    public Control() throws SQLException {
        model = new Conexion();
        model.conectar();
        ventIni = new VentInicio(this);
        
        tab_graf = new TableSpace();
        sqlite = new SQLiteJDBC();
        fecha = new GregorianCalendar();
       
        
    }

    public void iniciar() throws InterruptedException, SQLException  {
         
         ventIni.iniciar(getdblink());

    }

    
    public void atras(Integer bandera, String nombre) throws InterruptedException, SQLException {    
        if (bandera.equals(1)) {
            ventIni = new VentInicio(this);
            ventIni.iniciar(getdblink());

        }
        else if (bandera.equals(2)) {
            Estrategias ven_est= new Estrategias(this,nombre);
            ven_est.iniciar(model.getEstrategias(nombre));
        }
                       
     
        
        
        
    }

    public void AgregarServer()
    {
        AgregarSer agser= new AgregarSer(this);
        agser.iniciar();
    }
    
    public boolean createDBLINK(String name,String user,String password,String ip,String port)
    {
        return model.createDBLINK(new DBLINK(name,user,password,ip,port));
    }
    
    public ArrayList<DBLINK> getdblink() throws SQLException
    {
        return model.getdblinks();
    }
    
    public boolean eliminardblink(String name)
    {
        return model.eliminarDBLink(name);
    }
    public void ventanaEstrategias(String nombre) throws InterruptedException, SQLException
    {
        Estrategias est= new Estrategias(this,nombre);
        est.iniciar(model.getEstrategias(nombre));
        
    }
    
    public void ventFormEstrategias(String nom) throws InterruptedException, SQLException
    {
        FormEstrategias fes= new FormEstrategias(this, nom);
        fes.init(model.getSegmentos(nom));
    }
    
    public boolean crearEstrategia(String bd,String sql,Date fec_ini,int freq,int dias,int ini_rang, int fin_rang)
    {
        int count=model.countEstrategias(bd);
        String nombre=bd+"EST"+(count+1);
    
       
        return model.AgregarEstrategia(new Estrategia(bd,nombre,sql,dias,freq,1,ini_rang,fin_rang,new Timestamp(fec_ini.getTime()),null,null,new Timestamp(fec_ini.getTime())));
    }
    
    public void ventanaEvidencia(String nom,String evidencia) throws SQLException
    {
        Evidencias eviden= new Evidencias(this,nom,model.getEvidencias(evidencia));
        eviden.iniciar();
    }
    public boolean modificarEstrategia(String bd,String name)
    {
        if(model.valorEstado(name)==1)
        {
           return  model.eliminarEstrategia(name, bd, 0);
        }
        else
            return model.eliminarEstrategia(name, bd, 1);
    }
}
