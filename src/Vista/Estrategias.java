/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author cesar
 */
public class Estrategias extends JFrame implements ActionListener {
    private Control gestor;
    private String nombre;
    public Estrategias(Control ges, String nom) {
        super(nom.toString());
        nombre=nom;
        gestor=ges;
    }
    
    public void iniciar()
    {
        // botones 
        JPanel botones = new JPanel();
        JButton agregar= new JButton("agregar");
        agregar.addActionListener(this);
            JButton modificar= new JButton("modificar");
        modificar.addActionListener(this);
            JButton eliminar= new JButton("eliminar");
        eliminar.addActionListener(this);
        botones.add(agregar,BorderLayout.CENTER);
        botones.add(modificar,BorderLayout.CENTER);
        botones.add(eliminar,BorderLayout.CENTER);
        
        /// get contentPane
      
          add(botones,BorderLayout.SOUTH);
      this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand().equals("agregar"))
    {
        try {
            this.dispose();
            gestor.ventFormEstrategias(nombre);
        } catch (InterruptedException ex) {
            Logger.getLogger(Estrategias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Estrategias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
}
