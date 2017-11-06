/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobases2;

import Control.Control;
import java.sql.SQLException;

/**
 *
 * @author cesar
 */
public class ProyectoBases2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, InterruptedException {
        // TODO code application logic here
        Control gestor= new Control();
        gestor.iniciar();
    }
    
}
