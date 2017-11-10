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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cesar
 */

public class VentInicio  extends JFrame implements ActionListener, MouseListener {
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
ta.addMouseListener(this);
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
      this.setExtendedState(MAXIMIZED_BOTH);
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
        else
        if(e.getActionCommand().equals("eliminar"))
        {
            for(int i=0;i<ta.getRowCount();i++)
                    {
                       if(ta.getValueAt(i,1).toString()=="true")
                        {
                           if(gestor.eliminardblink(ta.getValueAt(i, 0).toString()))
                           {
                                    
                               try {
                                   JOptionPane.showMessageDialog(null, "Data base link eliminado correctamente", "Aceptado", JOptionPane.INFORMATION_MESSAGE);
                                   this.dispose();
                                   gestor.atras(1,"inicio");
                               } catch (InterruptedException ex) {
                                   Logger.getLogger(VentInicio.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (SQLException ex) {
                                   Logger.getLogger(VentInicio.class.getName()).log(Level.SEVERE, null, ex);
                               }
                           }
                           else
                           {
                                   JOptionPane.showMessageDialog(null, "Error al eliminar el database link ", "Error", JOptionPane.ERROR_MESSAGE);
            
                           }
                        }
                    }
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if(e.getClickCount()==1)
                {
                   int row= ta.getSelectedRow();
                    int colum=ta.getSelectedColumn();
                    
                  if(colum ==1)
                {
                    for(int i=0;i<ta.getRowCount();i++)
                    {
                       if(i!=row && ta.getValueAt(i,1).toString()=="true")
                        {
                            ta.setValueAt(false, i, 1);
                        }
                    }
                }
                }
                if(e.getClickCount()==2)
                {
                     int colum=ta.getSelectedColumn();
                      int row= ta.getSelectedRow();
                     if(colum!=1)
                     {
                         this.dispose();
                         gestor.ventanaEstrategias(ta.getValueAt(row,0).toString());
                     }
                }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
