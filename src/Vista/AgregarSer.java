/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author cesar
 */
public class AgregarSer extends JFrame implements ActionListener {
    Control gestor;
    JTextField ip,nombre,puerto,user,password;
    public AgregarSer(Control ges) {
         super("Crear Server");
        gestor=ges;
    }

    public void iniciar()
    {
        JPanel principal=new JPanel();
        GridBagLayout tb= new GridBagLayout();
    principal.setLayout(tb);    
     GridBagConstraints gc = new GridBagConstraints();
    gc.insets=new Insets(0,0,0,0);
    gc.gridx=0;
    gc.gridy=0;
    JLabel add= new JLabel("inserte el nombre del server: ");
    principal.add(add,gc);
    gc.gridx=1;
    nombre= new JTextField(10);
    principal.add(nombre,gc);
    gc.gridx=0;
    gc.gridy=1;
  add= new JLabel("inserte la ip del server: ");
  principal.add(add,gc);
    ip= new JTextField(10);
    gc.gridx=1;
    principal.add(ip,gc);
    gc.gridx=0;
    gc.gridy=2;
    add= new JLabel("inserte el puerto del server: ");
     principal.add(add,gc);
     gc.gridx=1;
    puerto= new JTextField(10);
     principal.add(puerto,gc);
      gc.gridx=0;
    gc.gridy=3;
    add= new JLabel("inserte el usuario del server: ");
     principal.add(add,gc);
          gc.gridx=1;
    user= new JTextField(10);
     principal.add(user,gc);
       gc.gridx=0;
    gc.gridy=4;
    add= new JLabel("inserte el password del server: ");
     principal.add(add,gc);
          gc.gridx=1;
    password= new JTextField(10);
     principal.add(password,gc);
    
    JPanel botones= new JPanel();
    JButton agregar= new JButton("agregar");
    agregar.addActionListener(this);
    agregar.setActionCommand("agregar");
    botones.add(agregar,BorderLayout.CENTER);
      agregar= new JButton("atras");
    agregar.addActionListener(this);
    agregar.setActionCommand("atras");
    botones.add(agregar,BorderLayout.CENTER);
          add(principal,BorderLayout.CENTER);
          add(botones,BorderLayout.SOUTH);
      this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("atras"))
        {
            try {
                this.dispose();
                gestor.atras();
            } catch (InterruptedException ex) {
                Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgregarSer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else 
        {
           
            if(!blanco())
            {
                 boolean aux= gestor.createDBLINK(nombre.getText(), user.getText(),password.getText(),ip.getText(),puerto.getText());
            if(!aux)
            {
                JOptionPane.showMessageDialog(null, "Error al crear el database link ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               
                limpiar();
                JOptionPane.showMessageDialog(null, "Data base link creado correctamente", "Aceptado", JOptionPane.INFORMATION_MESSAGE);
            }
                
        }
            else  JOptionPane.showMessageDialog(null, "Error no se envio el formulario, existe un campo vacio ", "Error", JOptionPane.ERROR_MESSAGE);
            
                
        }
    }
    
    private void limpiar()
    {
        nombre.setText("");
        user.setText("");
        password.setText("");
        ip.setText("");
        puerto.setText("");
    }
    
    private boolean blanco()
    {
        if(nombre.getText().equals(""))
        {
            return true;
        }
        else if(user.getText().equals(""))
        {
            return true;
        }
        else if(password.getText().equals(""))
        {
            return true;
        }
        else if(ip.getText().equals(""))
        {
            return true;
        }
        else if(puerto.getText().equals(""))
        {
            return true;
        }
        else return false;
    }
    
}
