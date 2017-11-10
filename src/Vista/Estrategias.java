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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cesar
 */
public class Estrategias extends JFrame implements ActionListener {
    private Control gestor;
    private String nombre;
    private tablaEstrategia tabla;
    public Estrategias(Control ges, String nom) {
        super(nom.toString());
        nombre=nom;
        gestor=ges;
        
        
     
    }
    
    public void iniciar()
    {
        
       //Panel de la tabla estrategia
        JPanel arriba= new JPanel();
        tabla = new tablaEstrategia();
        arriba.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        arriba.setLayout(new BorderLayout());
        JScrollPane desplazamientoTabla = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JTable table = new JTable();        
        table.setModel(tabla);
        desplazamientoTabla.setViewportView(table);
       arriba.add(BorderLayout.CENTER,desplazamientoTabla);
       arriba.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4)));
       arriba.setBorder(BorderFactory.createTitledBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)), "ESTRATEGIAS",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 30), new Color(213,18,2)));
        
       

        // botones 
        JPanel botones = new JPanel();
        JButton agregar= new JButton("agregar");
        agregar.addActionListener(this);
        JButton modificar= new JButton("modificar");
        modificar.addActionListener(this);
        JButton eliminar= new JButton("eliminar");
        eliminar.addActionListener(this);
        JButton evidencia= new JButton("Ver Tabla Evidencia");
        evidencia.addActionListener(this);
        evidencia.setActionCommand("evidencia");
        JButton atras= new JButton("Atras");
        atras.addActionListener(this);
        atras.setActionCommand("atras");
        botones.add(agregar,BorderLayout.CENTER);
        botones.add(modificar,BorderLayout.CENTER);
        botones.add(eliminar,BorderLayout.CENTER);
        botones.add(evidencia,BorderLayout.CENTER);
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
    
    
    if(e.getActionCommand().equals("evidencia")){
        this.dispose();
        try {
            gestor.ventanaEvidencia();
        } catch (SQLException ex) {
            Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    if(e.getActionCommand().equals("atras")){
        this.dispose();
        try {
            gestor.atras();
        } catch (InterruptedException ex) {
            Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    class tablaEstrategia extends DefaultTableModel {

        public tablaEstrategia() {
            super(new Object[][]{},
                    new String[]{            
            "Servidor","Estrategia","Sentencia utilizada","Primera ejecución","Estado","Fecha inicio de ultima ejec.","Fecha final de ultima ejec.","Próxima ejecución"});
            
            }
        
        @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return false;
        }
    }
    
    private void dibujar(ArrayList<Estrategia> bit)
    {
        
       
        for (int i = 0; i < bit.size(); i++) {
           
            tabla.addRow(
                     new Object[]{
                        bit.get(i).getBd(),
                        bit.get(i).getNombre(),
                        bit.get(i).getSql(),
                        bit.get(i).getFec_ini(),
                        bit.get(i).getIni_ult_eje(),
                        bit.get(i).getFin_ul_eje(),
                        bit.get(i).getProx_eje()
                        
                    });
          
        }
    }
}