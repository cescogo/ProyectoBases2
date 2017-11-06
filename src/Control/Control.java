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

//         sqlite.conectar();
//         sqlite.query("drop table TB_SPACES");
//         sqlite.conectar();
//         sqlite.query("drop table Hist");
//         sqlite.conectar();
//         sqlite.query("drop table HWM");
//         sqlite.conectar();
//         sqlite.query("drop table SGA");
//         sqlite.conectar();
//         sqlite.query("CREATE TABLE TB_SPACES " + "(fecha TEXT not null,nombre TEXT NOT NULL, MB_TABLAS float not null, usado float NOT NULL,TasaTrans float not null,registros INT NOT NULL)");
//         sqlite.conectar();
//         sqlite.query("CREATE TABLE Hist " + "(fecha TEXT not null,nombre TEXT NOT NULL, uso INT not null, porcentaje INT NOT NULL)");
//         sqlite.conectar();
//         sqlite.query("CREATE TABLE HWM " + "(nombre TEXT NOT NULL, HWM INT not null)");
//         sqlite.conectar();
//         sqlite.query("CREATE TABLE SGA " + "(fecha TEXT not null,hora TEXT NOT NULL,sql TEXT NOT NULL, usuario TEXT NOT NULL, maquina TEXT NOT NULL)");
//         

        fecha = new GregorianCalendar();
       
        
    }

    public void iniciar() throws InterruptedException, SQLException  {
         ta = model.getSegmentos();
         ventIni.iniciar();

    }

    
    public void atras() throws InterruptedException, SQLException {

//        tabSpa = null;
//        ta = null;
//        tab_graf = null;
//
//        ta = model.getSegmentos();
        ventIni = new VentInicio(this);
        ventIni.iniciar();
    }

    public void AgregarServer()
    {
        AgregarSer agser= new AgregarSer(this);
        agser.iniciar();
    }
    
//    public ArrayList<Table> getTablas(String tsp) throws InterruptedException, SQLException
//    {
//        return model.getTable(tsp);
//    }
}
