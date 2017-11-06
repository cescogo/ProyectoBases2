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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author cesar
 */

public class VentInicio  extends JFrame implements ActionListener  {
    Control gestor;

    public VentInicio(Control ges) {
         super("Creador de estrategias");
        gestor=ges;
    }
    
    public void iniciar()
    {
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
          add(botones,BorderLayout.SOUTH);
       setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("agregar"))
        {
            this.dispose();
            gestor.AgregarServer();
        }
        
    }
    
}
