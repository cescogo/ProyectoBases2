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

    
    public void atras() throws InterruptedException, SQLException {


        ventIni = new VentInicio(this);
        ventIni.iniciar(getdblink());
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
    public void ventanaEstrategias(String nombre)
    {
        Estrategias est= new Estrategias(this,nombre);
        est.iniciar();
        
    }
    
    public void ventFormEstrategias(String nom) throws InterruptedException, SQLException
    {
        FormEstrategias fes= new FormEstrategias(this, nom);
        fes.init(model.getSegmentos());
    }
    
    public boolean crearEstrategia(String bd,String sql,String fec_ini,String freq,int dias)
    {
        int count=model.countEstrategias(bd);
                System.out.println(count);
        return true;
    }
}
