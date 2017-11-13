/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import Modelo.Estrategia;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cesar
 */
public class Estrategias extends JFrame implements ActionListener, MouseListener {
    private Control gestor;
    private String nombre;
    private DefaultTableModel tabla;
    private JTable table;
    public Estrategias(Control ges, String nom) {
        super(nom.toString());
        nombre=nom;
        gestor=ges;
        
        
     
    }
    
    public void iniciar(ArrayList<Estrategia> estrategias)
    {
        
       //Panel de la tabla estrategia
        JPanel arriba= new JPanel();
        tabla =new DefaultTableModel()
         {
             public Class<?> getColumnClass(int colum)
             {
                 if(colum<3||colum==4)
                 {
                     return String.class;
                 }
                 else
                     if(colum==3||colum==6|| colum==7||colum==8)
                {
                    return Timestamp.class;
                }else
                         if(colum==5)
                {
                   return  Integer.class;
                    
                }
                     return Boolean.class;
             }
             @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return columnas==9;
        }
         };
        arriba.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        arriba.setLayout(new BorderLayout());
        JScrollPane desplazamientoTabla = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       table = new JTable(); 
       table.addMouseListener(this);
        table.setModel(tabla);
        tabla.addColumn("Servidor");
        tabla.addColumn("Estrategia");
        tabla.addColumn("Sentencia utilizada");
        tabla.addColumn("Primera ejecución");
        tabla.addColumn("Rango de horas");
        tabla.addColumn("Estado");
        tabla.addColumn("Fecha inicio de ultima ejec.");
        tabla.addColumn("Fecha final de ultima ejec.");
        tabla.addColumn("Próxima ejecución");
        tabla.addColumn("eliminar");
        desplazamientoTabla.setViewportView(table);
       arriba.add(BorderLayout.CENTER,desplazamientoTabla);
       arriba.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4)));
       arriba.setBorder(BorderFactory.createTitledBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)), "ESTRATEGIAS",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 30), new Color(213,18,2)));
        
       dibujar(estrategias);

        // botones 
        JPanel botones = new JPanel();
        JButton agregar= new JButton("agregar");
        agregar.addActionListener(this);
       JButton eliminar= new JButton("modificar estado");
        eliminar.addActionListener(this);
        eliminar.setActionCommand("modificar");
       JButton atras= new JButton("Atras");
        atras.addActionListener(this);
        atras.setActionCommand("atras");
        botones.add(agregar,BorderLayout.CENTER);
        botones.add(eliminar,BorderLayout.CENTER);
        botones.add(atras,BorderLayout.CENTER);
        
        /// get contentPane
        add(arriba,BorderLayout.CENTER);
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
    
    
    if(e.getActionCommand().equals("atras")){
        this.dispose();
        try {
            gestor.atras(1," ");
        } catch (InterruptedException ex) {
            Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        if(e.getActionCommand().equals("modificar"))
        {
            
            try {
                this.dispose();
            gestor.modificarEstrategia(nombre,gesEstrategia());
                gestor.ventanaEstrategias(nombre);
            } catch (InterruptedException ex) {
                Logger.getLogger(Estrategias.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Estrategias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
   private String gesEstrategia()
   {
       String aux="";
        for(int i=0;i<table.getRowCount();i++)
                    {
                       if(table.getValueAt(i,9).toString()=="true")
                        {
                          aux= table.getValueAt(i,1).toString();
                        }
                    }
        return aux;
   }
    private void dibujar(ArrayList<Estrategia> bit)
    {
        
       
        for (int i = 0; i < bit.size(); i++) {
            String h1=String.valueOf((bit.get(i).getIni_rang()/60));
            String m1=String.valueOf((bit.get(i).getIni_rang()%60));
            String h2=String.valueOf((bit.get(i).getFin_ran()/60));
            String m2=String.valueOf(bit.get(i).getFin_ran()%60);
           
            tabla.addRow(
                     new Object[]{
                        bit.get(i).getBd(),
                        bit.get(i).getNombre(),
                        bit.get(i).getSql(),
                        bit.get(i).getFec_ini(),
                        h1+":"+m1+" - "+h2+":"+m2,
                        bit.get(i).getEstado(),
                        bit.get(i).getIni_ult_eje(),
                        bit.get(i).getFin_ul_eje(),
                        bit.get(i).getProx_eje(),false
                        
                    });
          
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
     if(e.getClickCount()==1)
                {
                   int row= table.getSelectedRow();
                    int colum=table.getSelectedColumn();
                    
                  if(colum ==9)
                {
                    for(int i=0;i<table.getRowCount();i++)
                    {
                       if(i!=row && table.getValueAt(i,9).toString()=="true")
                        {
                            table.setValueAt(false, i, 9);
                        }
                    }
                }
                }
      if(e.getClickCount()==2)
                {
                     int colum=table.getSelectedColumn();
                      int row= table.getSelectedRow();
                     if(colum!=9)
                     {
                         this.dispose();
                         try {
                            gestor.ventanaEvidencia(nombre,table.getValueAt(row,1).toString());
                         } catch (SQLException ex) {
                             Logger.getLogger(VentInicio.class.getName()).log(Level.SEVERE, null, ex);
                         }
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