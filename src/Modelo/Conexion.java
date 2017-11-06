/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    static String user = "system";
    static String password = "root";
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
    public ArrayList<TableSpace> getSegmentos() throws InterruptedException, SQLException {
        ArrayList<TableSpace> vec = new ArrayList<>();
        Statement stm = null;
        try {
            stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("select tablespace_name from dba_tables where tablespace_name is not null AND tablespace_name != 'SYSTEM' group by tablespace_name");

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
        try {   stm = conexion.createStatement();
         stm.addBatch("CREATE DATABASE LINK"+dblink.getName()+"CONNECT TO"+ dblink.getUser()+" IDENTIFIED BY"+ dblink.getPassword()+ "USING'(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST ="+ dblink.getIp()+")(PORT = "+dblink.getPort()+"))(CONNECT_DATA = (SERVICE_NAME = XE)))'" );
         stm.executeBatch();
         stm.clearBatch();
         stm.close();
         return true;
   }catch ( SQLException e ) {
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
