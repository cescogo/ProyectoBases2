/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar
 */
public class Conexion {
///parametros de configuracion de usuario

    private Connection conexion;
    static String url = "jdbc:oracle:thin:@localhost:1521/XE"; //Descargar ojdbc6.jar e incluirlo en la libreria
    static String user = "central";
    static String password = "central";
    private boolean exito;
    private Control gestor;

    private ArrayList<String> resultados = new ArrayList<String>();

    /*Metodos*/
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        Conexion.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Conexion.password = password;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void conectar() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            exito = true;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado");
            exito = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            System.out.println(e.getMessage());
            exito = false;
        }
    }

    // se obtienen los segmentos de la base de datos
    public ArrayList<TableSpace> getSegmentos(String dblink) throws InterruptedException, SQLException {
        ArrayList<TableSpace> vec = new ArrayList<>();
        Statement stm = null;
        try {
            stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("select tablespace_name from dba_tablespaces@"+dblink+"");

            getColumnNames(rs);
            while (rs.next()) {

                //Aqui deberia jalar el nombre de la columna
                vec.add(new TableSpace(rs.getString("TABLESPACE_NAME"), 0, 0));

            }
            stm.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (stm != null) {
                stm.close();
            }
        }

        return vec;
    }

    public boolean createDBLINK(DBLINK dblink)
    {
         Statement stm = null;
        
        try {   
            stm = conexion.createStatement();
            String st="CREATE DATABASE LINK "+dblink.getName()+" CONNECT TO sede IDENTIFIED BY sede USING'(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST ="+ dblink.getIp()+")(PORT = "+dblink.getPort()+"))(CONNECT_DATA = (SERVICE_NAME = XE)))'";
            stm.execute(st);
         stm.close();
         return true;
   }catch ( SQLException e ) {
       System.out.println(e.getMessage());
         return false;
         
      }catch (Exception e)
      {
          System.out.println(e.getMessage());
         return false;
      }
    }
    
    public ArrayList<DBLINK> getdblinks() throws SQLException
    {
        ArrayList<DBLINK> vec = new ArrayList<>();
        Statement stm = null;
        try {
            stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("SELECT DB_LINK, USERNAME ,HOST FROM DBA_DB_LINKS where USERNAME!=' '");

            getColumnNames(rs);
            while (rs.next()) {

                //Aqui deberia jalar el nombre de la columna
                vec.add(new DBLINK(rs.getString("DB_LINK")));

            }
            stm.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (stm != null) {
                stm.close();
            }
        }

        return vec;
        
    }
    
    public boolean eliminarDBLink(String name)
    {
        Statement stm = null;
        
        try {   
            stm = conexion.createStatement();
            String st="Drop DATABASE LINK "+name;
            stm.execute(st);

         stm.close();
         return true;
   }catch ( SQLException e ) {
       System.out.println(e.getMessage());
         return false;
         
      }catch (Exception e)
      {
          System.out.println(e.getMessage());
         return false;
      }
    }

    public int countEstrategias(String bd)
    {
        
        int count=0;
         Statement stm = null;
        try {
            stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("select count(*) from sede.estrategias@"+bd);
           getColumnNames(rs);
           rs.next();
           count=rs.getInt("COUNT(*)");

         stm.close();
     
   }catch ( SQLException e ) {
       System.out.println(e.getMessage());
         
         
      }catch (Exception e)
      {
          System.out.println(e.getMessage());
         
      }
        return count;
    }

    
     public boolean AgregarEstrategia(Estrategia est)
    {
        PreparedStatement stm = null;
        
        try {   
           if(agregarSede(est.getBd(),est.getNombre(),est.getSql(),est.getEstado(),est.getFreq(),est.getDias(),est.getIni_rang(),est.getFin_ran(),est.getFec_ini()))
           {
        
            String st="insert into estrategias values (?,?,?,?,?,?,?,?,?,?,?,?)";
            stm = conexion.prepareStatement(st);
            stm.setString(1, est.getBd());
            stm.setString(2,est.getNombre());
            stm.setString(3, est.getSql());
            stm.setTimestamp(4, est.getFec_ini());
            stm.setInt(5, est.getEstado());
            stm.setInt(6, est.getFreq());
            stm.setInt(7,est.getDias());
            stm.setInt(8,est.getIni_rang());
            stm.setInt(9,est.getFin_ran());
            stm.setTimestamp(10,est.getIni_ult_eje());
            stm.setTimestamp(11,est.getFin_ul_eje());
            stm.setTimestamp(12,est.getFec_ini());
             stm.executeUpdate();
            stm.close();
         return true;
            }
            else
                return false;
   }catch ( SQLException e ) {
       System.out.println(e.getMessage());
         return false;
         
      }catch (Exception e)
      {
          System.out.println(e.getMessage());
         return false;
      }
    }
     
     private boolean agregarSede(String bd,String nombre,String sql,int estado,int freq,int dias,int ini_rang,int fin_rang,Timestamp prx_eje)
     {
          PreparedStatement stm = null;
        String insert="insert into sede.estrategias@"+bd+" values(?,?,?,?,?,?,?,?)";
        try {   
            stm = conexion.prepareStatement(insert);
            stm.setString(1,nombre);
            stm.setString(2,sql);
            stm.setInt(3,estado);
            stm.setInt(4,freq);
            stm.setInt(5,dias);
            stm.setInt(6,ini_rang);
            stm.setInt(7,fin_rang);
            stm.setTimestamp(8,prx_eje);
            stm.executeUpdate();
            stm.close();
         return true;
   }catch ( SQLException e ) {
       System.out.println(e.getMessage());
         return false;
         
      }catch (Exception e)
      {
          System.out.println(e.getMessage());
         return false;
      }
     }
     
      public ArrayList<Estrategia> getEstrategias(String bd) throws InterruptedException, SQLException {
        ArrayList<Estrategia> vec = new ArrayList<>();
        Statement stm = null;
        try {
            stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("select * from estrategias where bd='"+bd+"'");

            getColumnNames(rs);
            while (rs.next()) {

                //Aqui deberia jalar el nombre de la columna
             vec.add(new Estrategia(bd,rs.getString("nombre"),rs.getString("sentencia"),rs.getInt("dias"),rs.getInt("frecuencia"),rs.getInt("estado"),rs.getInt("ini_rang"),rs.getInt("fin_rang"),rs.getTimestamp("fecha_inicio"),rs.getTimestamp("inicio_ult_ejecu"),rs.getTimestamp("fin_ult_ejecu"),rs.getTimestamp("proxima_ejecucion")));
            }
            stm.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (stm != null) {
                stm.close();
            }
        }

        return vec;
    }
    
    /*Devuelve columna*/
    public static void getColumnNames(ResultSet rs) throws SQLException {
        if (rs == null) {
            return;
        }
        // get result set meta data
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int numberOfColumns = rsMetaData.getColumnCount();

        // get the column names; column indexes start from 1
        for (int i = 1; i < numberOfColumns + 1; i++) {
            String columnName = rsMetaData.getColumnName(i);
            // Get the name of the column's table name
            String tableName = rsMetaData.getTableName(i);

        }
    }

    

}
