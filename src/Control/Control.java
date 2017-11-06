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
    
  
    private ArrayList<String> tabSpa;
    private ArrayList<TableSpace> ta;
    private TableSpace tab_graf;
  
    private SQLiteJDBC sqlite;
    private Calendar fecha;
    private float aux;

    private VentInicio ventIni;

    public Control() throws SQLException {
        model = new Conexion();
        model.conectar();
        ventIni = new VentInicio(this);
        tabSpa = new ArrayList<>();
        ta = new ArrayList<>();
        tab_graf = new TableSpace();
        sqlite = new SQLiteJDBC();
        fecha = new GregorianCalendar();
       
        
    }

    public void iniciar() throws InterruptedException, SQLException  {
         ta = model.getSegmentos();
         ventIni.iniciar();

    }

    
    public void atras() throws InterruptedException, SQLException {


        ventIni = new VentInicio(this);
        ventIni.iniciar();
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
}
