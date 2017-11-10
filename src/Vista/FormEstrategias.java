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
import java.awt.Color;
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
import java.awt.Font;
import java.awt.LayoutManager;
import static java.lang.Math.pow;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;


/**
 *
 * @author cesar
 */
public class FormEstrategias extends JFrame implements ActionListener  {
    
    private JPanel panel,pan_Tab,pan_fecha,principal,pan_job,pan_freq;
     private Button aceptar;
    private Control gestor;
  private DefaultComboBoxModel dia,me,hor,min,sem;
private  JComboBox dias,mes,hora,minute,semana;

  private  ArrayList<TableSpace> TaSpa;
  private DefaultTableModel model,model_sem;
  private JTable ta,tab_sem;
  private JTextField frecuencia;
  private  JRadioButton r_par_com,r_par_inc,r_par_inc1,r_par_acu,r_full;
  private JLabel leg_par;
  private String query,nombre;
  
 
    public FormEstrategias(Control c,String nom)
    {
        super(nom.toString());
        gestor=c;
        panel= new JPanel();   
        pan_fecha= new JPanel();
        pan_Tab= new JPanel(); 
        principal=new JPanel();
        pan_job= new JPanel();
         pan_freq= new JPanel();
       
         dia = new DefaultComboBoxModel();
         me = new DefaultComboBoxModel();
        hor = new DefaultComboBoxModel();
         min = new DefaultComboBoxModel();
         query="backup database;";
         nombre=nom;
    }
    
    public void init(ArrayList<TableSpace> TaSpa) throws InterruptedException
    {
        GridBagLayout tb= new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
    gc.insets=new Insets(0,0,0,0);
            //////
        this.TaSpa=TaSpa;
        this.gestor=gestor;
        ///////crear job//////
        pan_job.setLayout(tb);
        gc.gridx=0;
        gc.gridy=0;
        JLabel estado= new JLabel("opciones de las estrategias");
        pan_job.add(estado,gc);
        
         gc.gridy=1;
        estado= new JLabel("Estado del backup");
        pan_job.add(estado,gc);
        
        ButtonGroup group_est = new ButtonGroup();
        
        JRadioButton r_est= new JRadioButton("Frío");
        r_est.addActionListener(this);
        r_est.setActionCommand("frío");
        r_est.setSelected(true);
        group_est.add(r_est);
        gc.gridx=1;
        pan_job.add(r_est,gc);
        
        r_est= new JRadioButton("Caliente");
        r_est.addActionListener(this);
        r_est.setActionCommand("caliente");
        group_est.add(r_est);
        gc.gridx=2;
        pan_job.add(r_est,gc);
        
        gc.gridx=0;
        gc.gridy=2;
        estado= new JLabel("Tipo backup");
        pan_job.add(estado,gc);
        
        ButtonGroup group_tipo = new ButtonGroup();
        
         r_full= new JRadioButton("Full");
        r_full.addActionListener(this);
        r_full.setActionCommand("full");
        r_full.setSelected(true);
        group_tipo.add(r_full);
        gc.gridx=1;
        pan_job.add(r_full,gc);
        
      JRadioButton  r_tipo= new JRadioButton("Parcial");
        r_tipo.addActionListener(this);
        r_tipo.setActionCommand("parcial");
        group_tipo.add(r_tipo);
        gc.gridx=2;
        pan_job.add(r_tipo,gc);
        
         ////// subpanel ///
      
          gc.gridx=0;
        gc.gridy=3;
        leg_par= new JLabel("tipo de backup parcial");
        leg_par.setVisible(false);
        pan_job.add(leg_par,gc);
        
        ButtonGroup group_par = new ButtonGroup();
        
      r_par_com= new JRadioButton("Completo");
        r_par_com.addActionListener(this);
        r_par_com.setActionCommand("completo");
        r_par_com.setVisible(false);
        
        group_par.add(r_par_com);
        gc.gridx=1;
         pan_job.add(r_par_com,gc);
         
          r_par_inc= new JRadioButton("Incremental nv.0");
        r_par_inc.addActionListener(this);
        r_par_inc.setActionCommand("incremental0");
         r_par_inc.setVisible(false);
        group_par.add(r_par_inc);
        gc.gridx=2;
         pan_job.add(r_par_inc,gc);
         
            r_par_inc1= new JRadioButton("Incremental nv.1");
        r_par_inc1.addActionListener(this);
        r_par_inc1.setActionCommand("incremental1");
         r_par_inc1.setVisible(false);
        group_par.add(r_par_inc1);
        gc.gridx=3;
         pan_job.add(r_par_inc1,gc);
         
         gc.gridx=1;
         gc.gridy=4;
             r_par_acu= new JRadioButton("Incremental acumulativo nv.1");
        r_par_acu.addActionListener(this);
        r_par_acu.setActionCommand("acumulativo");
         r_par_acu.setVisible(false);
        group_par.add(r_par_acu);
        pan_job.add(r_par_acu,gc);
 
        pan_job.setPreferredSize(new Dimension(600,150));
        pan_job.setBorder(BorderFactory.createTitledBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)), "opciones de Backup",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 14), new Color(213,18,2)));
        
      ////////tablespace//////
        ta= new JTable();

        JScrollPane desplazamientoTab = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       desplazamientoTab.setPreferredSize(new Dimension(480,110));
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
               if(i==0)
               {
                    model.addRow(new Object[]{aux,true});
               }else
          
            model.addRow(new Object[]{aux,false});               
           
    }
           
         
        pan_Tab.add(desplazamientoTab);
        pan_Tab.setVisible(false);
        pan_Tab.setPreferredSize(new Dimension(500,150));
        pan_Tab.setBorder(BorderFactory.createTitledBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)), "tablespace para realizarles el Backup",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 14),new Color(213,18,2)));
        
        
       ////////// panel fecha////
       gc.insets=new Insets(0,0,0,10);
       pan_fecha.setLayout(tb);
       gc.gridx=0;
       gc.gridy=0;
      JLabel fecha= new JLabel("fecha de inicio de la estrategia");
      pan_fecha.add(fecha,gc);
      
      gc.gridx=1;
      gc.gridy=1;
      JLabel dia_Ini=new JLabel("Dia");
      pan_fecha.add(dia_Ini,gc);
      
      gc.gridx=2;
      JLabel mes_Ini=new JLabel("mes");
        pan_fecha.add(mes_Ini,gc);     
        
       for(int i=0;i<31;i++)
        {
             if(i<9)
            {
                dia.addElement("0"+(i+1));
            }
            else
           dia.addElement(i+1);
           
        }
        dias= new JComboBox(dia);
        gc.gridx=1;
        gc.gridy=2;
        pan_fecha.add(dias,gc);
        
        for(int i=0;i<12;i++)
        {
             if(i<9)
            {
                me.addElement("0"+(i+1));
            }
            else
           me.addElement(i+1);
        }
        mes= new JComboBox(me);
         gc.gridx=2;
        pan_fecha.add(mes,gc);
        
         gc.gridx=3;
       gc.gridy=0;
        fecha= new JLabel("Hora de inicio de la estrategia");
      pan_fecha.add(fecha,gc);
      
      gc.gridx=4;
      gc.gridy=1;
       JLabel hora_Ini=new JLabel("hora");
        pan_fecha.add(hora_Ini,gc);
        
        gc.gridx=5;
        JLabel min_Ini=new JLabel("minutos");
        pan_fecha.add(min_Ini,gc);
        
         gc.gridx=4;
        gc.gridy=2;
         for(int i=0;i<24;i++)
        {
            if(i<10)
            {
                hor.addElement("0"+i);
            }
            else
           hor.addElement(i);
        }
        hora= new JComboBox(hor);       
        pan_fecha.add(hora,gc);
        
        gc.gridx=5;
          for(int i=0;i<60;i++)
        {
              if(i<10)
            {
                min.addElement("0"+i);
            }
            else
           min.addElement(i);
        }
        minute= new JComboBox(min);
        
        pan_fecha.add(minute,gc);
       
        pan_fecha.setPreferredSize(new Dimension(590,300));
        pan_fecha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)), "fecha y hora que iniciaria el Backup",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 14),new Color(213,18,2)));
    
      ///////panel frecuencia/////
              gc.insets=new Insets(0,10,10,10);
              pan_freq.setLayout(tb);
      String[] d= new String[]{"Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};
      gc.gridy=0;   
      gc.gridx=0;
      JLabel freq=new JLabel("fecuencia de repeticion");
         pan_freq.add(freq,gc);
         
         gc.gridx=1;
        frecuencia= new JTextField(10);        
        pan_freq.add(frecuencia,gc);
        
         gc.gridy=1;   
      gc.gridx=0;
        JLabel dia_sem= new JLabel("Dias que se ejecuta");
        pan_freq.add(dia_sem,gc);
        
         gc.gridx=1;
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
         if(i==0)
         {
              model_sem.addRow(new Object[]{d[i],true});  
         }else
            model_sem.addRow(new Object[]{d[i],false});               
   }
           pan_freq.add(scroll_sem,gc);
           pan_freq.setPreferredSize(new Dimension(500,300));
          pan_freq.setBorder(BorderFactory.createTitledBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)), "Periocidad del Backup",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 14),new Color(213,18,2)));
       
        /////boton aceptar /////
      
       JButton aceptar= new JButton("Crear");
         aceptar.addActionListener(this);
         aceptar.setActionCommand("crear");
       JButton atras= new JButton("atras");
        atras.setActionCommand("atras");
        atras.addActionListener(this);
       panel.add(aceptar,BorderLayout.CENTER); 
       panel.add(atras,BorderLayout.CENTER); 
       /// agregar a ventana principal////
        gc.insets=new Insets(10,10,10,10);
    principal.setLayout(tb);    
     
    gc.gridx=0;
    gc.gridy=0;
    principal.add(pan_job,gc);
    
    gc.gridx=2;
    principal.add(pan_Tab,gc);
    
    
    gc.gridx=0;
    gc.gridy=1;
      principal.add(pan_fecha,gc);
      
      gc.gridx=2;
      principal.add(pan_freq,gc);
      
       gc.gridx=1;
    gc.gridy=4;
        principal.add(panel,gc);
       
        ////ventana principal/////
        add(principal,BorderLayout.CENTER);
      this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

		
                    if(e.getActionCommand().equals("parcial"))
                    {
                         r_par_com.setVisible(true);
                         r_par_inc.setVisible(true);
                         r_par_inc1.setVisible(true);
                         leg_par.setVisible(true);
                         r_par_acu.setVisible(true);
                         pan_Tab.setVisible(true);
                         r_par_com.setSelected(true);
                          query="";
                         query="backup full tablespace ";
                         
                    }
                else
                 if(e.getActionCommand().equals("full"))
                    {
                         r_par_com.setVisible(false);
                         r_par_inc.setVisible(false);
                         r_par_inc1.setVisible(false);
                         r_par_acu.setVisible(false);
                         leg_par.setVisible(false);
                         pan_Tab.setVisible(false);
                         query="";
                         query="backup database;";
                    }
                 else if(e.getActionCommand().equals("completo"))
                 {
                      query="";
                         query="backup full tablespace ";
                 }
                 else if(e.getActionCommand().equals("incremental0"))
                 {
                     query="";
                         query="backup incremental level 0 tablespace ";
                 }
                 else if(e.getActionCommand().equals("incremental1"))
                 {
                         query="";
                         query="backup incremental level 1 tablespace ";
                 }
                 else if(e.getActionCommand().equals("acumulativo"))
                 {
                      query="";
                         query="backup incremental level 1 cumulative tablespace ";
                 }
                 else if(e.getActionCommand().equals("crear"))
                 {
                     if(frecuencia.getText().equals(""))
                     {
                         JOptionPane.showMessageDialog(null, "Error no se envio el formulario, existe un campo vacio ", "Error", JOptionPane.ERROR_MESSAGE);
                     }
                     else
                     {
                     if(!r_full.isSelected())
                     {
                         tablespaces();
                     }
                     String fecha=dia.getSelectedItem().toString()+"/"+me.getSelectedItem()+" "+hor.getSelectedItem()+":"+min.getSelectedItem();
                     int freq= Integer.parseInt(frecuencia.getText());
                         
                         
                     if(gestor.crearEstrategia(nombre, query,fecha,freq,dias()))
                     {
                             JOptionPane.showMessageDialog(null, "Estrategia creada correctamente", "Aceptado", JOptionPane.INFORMATION_MESSAGE);
           
                     }
                     else{
                             JOptionPane.showMessageDialog(null, "Error al crear la estrategia", "ERROR", JOptionPane.ERROR_MESSAGE);
           
                     }
                 }
                 }
                    
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

    public void tablespaces()
    {
        boolean primero=true;
        for(int i=0;i<TaSpa.size();i++)
        {
            
            if (ta.getValueAt(i, 1).toString().equals("true"))
            {
                if(primero)
                {
                    query=query+TaSpa.get(i).getNombre();
                    primero=false;
                }
                else
                    query=query+","+TaSpa.get(i).getNombre();
            }
        }
        query=query+";";
    }
  
    private int dias()
    {
        int dias=0;
        for(int i=0;i<7;i++)
        {
            if (tab_sem.getValueAt(i, 1).toString().equals("true"))
            {
                dias=dias|(int)pow(2,i);
            }
            
        }
        return dias;
    }
    
}