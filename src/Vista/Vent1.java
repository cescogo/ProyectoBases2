/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import Modelo.*;
import java.awt.BasicStroke;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javafx.scene.paint.Color;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

/**
 *
 * @author cesar
 */
public class Vent1 extends JFrame implements ActionListener  {
    
    private JPanel panel,pan_Tab,pan_Inicio,principal,pan_job,pan_freq;
     private Button aceptar;
    private Control gestor;
  private DefaultComboBoxModel dia,me,hor,min,sem;
private  JComboBox dias,mes,hora,minute,semana;

  private  ArrayList<TableSpace> TaSpa;
  private DefaultTableModel model,model_sem;
  private JTable ta,tab_sem;
  private JTextField frecuencia,nombre;
  
 
    public Vent1(Control c)
    {
        super("tablespace");
        gestor=c;
        panel= new JPanel();   
        pan_Inicio= new JPanel();
        pan_Tab= new JPanel(); 
        principal=new JPanel();
        pan_job= new JPanel();
         pan_freq= new JPanel();
         dia = new DefaultComboBoxModel();
         me = new DefaultComboBoxModel();
        hor = new DefaultComboBoxModel();
         min = new DefaultComboBoxModel();
    }
    
    public void init(ArrayList<TableSpace> TaSpa) throws InterruptedException
    {
        
            //////
        this.TaSpa=TaSpa;
        this.gestor=gestor;
        ///////crear job//////
        JLabel nom= new JLabel("nombre de la estrategia");
        pan_job.add(nom,BorderLayout.CENTER);
        nombre= new JTextField(15);
        pan_job.add(nombre,BorderLayout.CENTER);
        pan_job.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
        
       ////////// panel fecha////
      
        for(int i=0;i<31;i++)
        {
           dia.addElement(i+1);
        }
        dias= new JComboBox(dia);
        JLabel dia_Ini=new JLabel("Dia");
        pan_Inicio.add(dia_Ini,BorderLayout.CENTER);
        pan_Inicio.add(dias,BorderLayout.CENTER);
        for(int i=0;i<12;i++)
        {
           me.addElement(i+1);
        }
        mes= new JComboBox(me);
        JLabel mes_Ini=new JLabel("mes");
        pan_Inicio.add(mes_Ini,BorderLayout.CENTER);
        pan_Inicio.add(mes,BorderLayout.CENTER);
         for(int i=0;i<24;i++)
        {
           hor.addElement(i);
        }
        hora= new JComboBox(hor);
        JLabel hora_Ini=new JLabel("hora");
        pan_Inicio.add(hora_Ini,BorderLayout.CENTER);
        pan_Inicio.add(hora,BorderLayout.CENTER);
          for(int i=0;i<60;i++)
        {
           min.addElement(i);
        }
        minute= new JComboBox(min);
        JLabel min_Ini=new JLabel("minutos");
        pan_Inicio.add(min_Ini,BorderLayout.CENTER);
        pan_Inicio.add(minute,BorderLayout.CENTER);
       
        
        pan_Inicio.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
    
      ///////panel frecuencia/////
            
      String[] d= new String[]{"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};
         JLabel freq=new JLabel("fecuencia de repeticion");
        frecuencia= new JTextField(5);
        pan_freq.add(freq,BorderLayout.CENTER);
        pan_freq.add(frecuencia,BorderLayout.CENTER);
        JLabel dia_sem= new JLabel("Dias que se ejecuta");
        pan_freq.add(dia_sem,BorderLayout.CENTER);
        tab_sem= new JTable();
        JScrollPane scroll_sem = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       scroll_sem.setPreferredSize(new Dimension(150,150));
         scroll_sem.setViewportView(tab_sem);
          model_sem= new DefaultTableModel()
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
        tab_sem.setModel(model_sem);
      
         model_sem.addColumn("Dia");
         model_sem.addColumn("Select");
           for (int i = 0; i < 7; i++) {
         
            model_sem.addRow(new Object[]{d[i],false});               
   }
           pan_freq.add(scroll_sem,BorderLayout.CENTER);           
          pan_freq.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
         
         ////////tablespace//////
        ta= new JTable();

        JScrollPane desplazamientoTab = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       desplazamientoTab.setPreferredSize(new Dimension(150,150));
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
         model.addColumn("Tablespace");
         model.addColumn("Select");
           for (int i = 0; i < TaSpa.size(); i++) {
          String aux=TaSpa.get(i).getNombre();
            model.addRow(new Object[]{aux,false});               
           
    }
           
         
        pan_Tab.add(desplazamientoTab);
        pan_Tab.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
        /////boton aceptar /////
      
       JButton aceptar= new JButton("Crear");
         aceptar.addActionListener(this);

       panel.add(aceptar,BorderLayout.CENTER); 
       /// agregar a ventana principal////
        GridBagLayout tb= new GridBagLayout();
    principal.setLayout(tb);    
     GridBagConstraints gc = new GridBagConstraints();
    gc.insets=new Insets(0,0,0,0);
    gc.gridx=0;
    gc.gridy=0;
       principal.add(pan_job,gc);
       gc.gridx=1;
    gc.gridy=2;
      principal.add(pan_Inicio,gc);
      gc.gridx=2;
      principal.add(pan_freq,gc);
           gc.gridx=0;
    gc.gridy=3;
       principal.add(pan_Tab,gc);
       gc.gridx=1;
    gc.gridy=4;
        principal.add(panel,gc);
       
        ////ventana principal/////
        add(principal,BorderLayout.CENTER);
       setSize(1200,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Crear"))		
                {
                }
    }


  
    
    
}