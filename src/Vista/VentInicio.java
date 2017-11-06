/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import Modelo.DBLINK;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cesar
 */

public class VentInicio  extends JFrame implements ActionListener  {
    Control gestor;
    private JTable ta;
    private DefaultTableModel model;
    private JPanel pan_dblink;

    public VentInicio(Control ges) {
         super("Creador de estrategias");
        gestor=ges;
        pan_dblink= new JPanel();
    }
    
    public void iniciar(ArrayList<DBLINK> dblinks)
    {
        // tabla dblinks///
        
         ta= new JTable();

        JScrollPane desplazamientoTab = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       desplazamientoTab.setPreferredSize(new Dimension(400,400));
         desplazamientoTab.setViewportView(ta);
         model= new DefaultTableModel()
         {
             public Class<?> getColumnClass(int colum)
             {
                 if(colum==0)
                 {
                     return String.class;
                 }
                 else
                     return Boolean.class;
             }
             @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return columnas==1;
        }
         };
         
         ta.setModel(model);
         model.addColumn("DBLINK");
         model.addColumn("Modificar/Eliminar");
           for (int i = 0; i < dblinks.size(); i++) {
          String aux=dblinks.get(i).getName();
            model.addRow(new Object[]{aux,false});               
           
    }
           
         
        pan_dblink.add(desplazamientoTab);
       
        // panel botones///
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
        // content pane///
        add(pan_dblink,BorderLayout.CENTER);
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
