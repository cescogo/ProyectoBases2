/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author adan-
 */
public class Table {

   
    String name;
   
    String owner;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Table(int bytes, String name, int count, String owner) {
      
        this.name = name;
        
        this.owner = owner;
    }
    
    public Table(String name,String owner) {
        this.name = name;
        this.owner = owner;
    }
}
