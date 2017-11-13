/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import Modelo.Estrategia;
import Modelo.Evidencia;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carmc_000
 */
public class Evidencias extends JFrame implements ActionListener, MouseListener {
    Control gestor;
    private tablaEvidencia tabla;
    String nombre;
    private JTextArea area;
    private  JTable table;
    private ArrayList<Evidencia> evi;
        public Evidencias(Control ges,String nom,ArrayList<Evidencia> ev) {
             super("Mostrar evidencia servidor"+ nom);
            gestor=ges;
            nombre=nom;
            evi=ev;
        }
        
       public void iniciar(){
        JPanel central= new JPanel();
        tabla= new tablaEvidencia();
        central.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        central.setLayout(new BorderLayout());
        JScrollPane desplazamientoTabla = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       table = new JTable(); 
       table.addMouseListener(this);
        table.setModel(tabla);
        desplazamientoTabla.setViewportView(table);
        central.add(BorderLayout.CENTER,desplazamientoTabla);
        central.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4)));
        central.setBorder(BorderFactory.createTitledBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)), "EVIDENCIAS ",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 30), new Color(213,18,2)));
        
        area= new JTextArea(20,30);
        area.setEditable(false);
        area.setVisible(false);
        area.setFont (area.getFont().deriveFont ( 20f ));
        JScrollPane log = new JScrollPane(area);
        central.add(BorderLayout.EAST,log);
        
        JPanel botones= new JPanel();
        JButton atras= new JButton("Atras");
        atras.setActionCommand("atras");
        atras.addActionListener(this);
        botones.add(atras,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
        add(central,BorderLayout.CENTER);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        dibujar();
    }
         private void dibujar()
    {
        
       
        for (int i = 0; i < evi.size(); i++) {
            
           
            tabla.addRow(
                     new Object[]{
                       evi.get(i).getNombre(),
                        evi.get(i).getInicio(),
                        evi.get(i).getTermino()
                        
                    });
          
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if(e.getClickCount()==2)
                {
                   
                      int row= table.getSelectedRow();
                     
                        area.setText(evi.get(row).getEvidencia().toString());
                        area.setVisible(true);
                     
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
    class tablaEvidencia extends DefaultTableModel {

        public tablaEvidencia() {
            super(new Object[][]{},
                    new String[]{            
            "nombre","inicio","termino"});
            
            }
        
        @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("atras")){
            try {
                this.dispose();
                gestor.atras(2,nombre);
            } catch (InterruptedException ex) {
                Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

