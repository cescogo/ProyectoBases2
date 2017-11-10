/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
 * @author carmc_000
 */
public class Evidencias extends JFrame implements ActionListener{
    Control gestor;
    private tablaEvidencia tabla;
        public Evidencias(Control ges) {
             super("Mostrar evidencia");
            gestor=ges;
        }
        
       public void iniciar(){
        JPanel central= new JPanel();
        tabla= new tablaEvidencia();
        central.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        central.setLayout(new BorderLayout());
        JScrollPane desplazamientoTabla = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JTable table = new JTable();        
        table.setModel(tabla);
        desplazamientoTabla.setViewportView(table);
        central.add(BorderLayout.CENTER,desplazamientoTabla);
        central.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4)));
        central.setBorder(BorderFactory.createTitledBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)), "EVIDENCIAS ",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 30), new Color(213,18,2)));
        
        
        JPanel botones= new JPanel();
        JButton atras= new JButton("atras");
        atras.setActionCommand("atras");
        atras.addActionListener(this);
        botones.add(atras,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
        add(central,BorderLayout.CENTER);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //dibujar(bit);
    }
    class tablaEvidencia extends DefaultTableModel {

        public tablaEvidencia() {
            super(new Object[][]{},
                    new String[]{            
            "Servidor","Evidencia","Log", "Actividad(Check aplico o no)"});
            
            }
        
        @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return false;
        }
    }
    
//    private void dibujar(ArrayList<Evidencia> bit)
//    {
//        
//       
//        for (int i = 0; i < bit.size(); i++) {
//           
//            tabla.addRow(
//                     new Object[]{
//                        bit.get(i).getBd(),
//                        bit.get(i).getEstrategia(),
//                        bit.get(i).getLog(),
//                        bit.get(i).getC()
//                         
//                    });
//          
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("atras")){
            try {
                this.dispose();
                gestor.atras();
            } catch (InterruptedException ex) {
                Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

