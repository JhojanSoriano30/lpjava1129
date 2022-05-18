/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.logic;

import com.edu.sise.dao.DepartamentoDao;
import com.edu.sise.entidades.Departamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;


public class DepartamentoLogic {
    List<Departamento> lista;

    public DefaultTableModel getModelo(){
        //cargarLista();
        
        lista = new DepartamentoDao().getAll();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
   modelo.addColumn("ID");
   modelo.addColumn("NOMBRE");
   for(Departamento obj : lista){
       Object data[]=
       {
       obj.getId_depa(),
           obj.getNombre()
       
       };
       
       modelo.addRow(data);
   }
    
    return modelo;
}
//
//public void cargarLista(){
//lista = new ArrayList<>();
//
//lista.add(new Departamento(100, "La libertad"));
//lista.add(new Departamento(200, "Lima"));
//lista.add(new Departamento(300, "Tumbes"));
//lista.add(new Departamento(400, "Lambayeque"));
//}


public void imprimir(JTable tabla){
    tabla.setModel(getModelo());
}

 public boolean agegarDepartamento(Departamento o){
     return new DepartamentoDao().agegarDepartamento(o);
 }

 
 public boolean modificarDepartamento(Departamento o){
     return new DepartamentoDao().modificarDepartamento(o);
 }

 
 public boolean eliminarDepartamento(Integer id){
     return new DepartamentoDao().eliminarDepartamento(id);
 }

 //metodo para llenar informacion del combo
 
 public void llenarDepartamento(JComboBox cbo) {
        lista = new DepartamentoDao().getAll();
        
     DefaultComboBoxModel modelo= new DefaultComboBoxModel();
     for(Departamento obj : lista) {
         modelo.addElement (obj);
     }
     cbo.setModel(modelo);
 }
 
 public void seleccionarItemCbo(JComboBox cbo, Integer id){
        DefaultComboBoxModel modelo = (DefaultComboBoxModel)cbo.getModel();
        
        for(int i=0; i<modelo.getSize();i++){
            if(((Departamento)modelo.getElementAt(i)).getId_depa()==id){
                modelo.setSelectedItem(modelo.getElementAt(i));
            }
        }
    }

 
 
}