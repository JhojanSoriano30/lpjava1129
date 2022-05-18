/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.logic;

import com.edu.sise.dao.DAOManager;
import com.edu.sise.dao.ProfesorDao;
import com.edu.sise.entidades.Profesor;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CLIENTE
 */
public class ProfesorLogic {
    List<Profesor> lista;
ProfesorDao dao = DAOManager.getInstancia().getProfesorDao();
    public DefaultTableModel getModelo(){

        lista = DAOManager.getInstancia().getProfesorDao().getAll();
        DefaultTableModel modelo = new DefaultTableModel();
        
   modelo.addColumn("ID");
   modelo.addColumn("DNI");
     modelo.addColumn("NOMBRE");
   modelo.addColumn("PAPELLIDO");
   modelo.addColumn("SAPELLIDO");
   modelo.addColumn("FNACIMIENTO");
   modelo.addColumn("TELEFONO");
   modelo.addColumn("ID_CARRERA");
   modelo.addColumn("ID_PROV");
  
   for(Profesor obj : lista){
       Object data[]=
       {
       obj.getId_profe(),
           obj.getDni(),
             obj.getNombre(),
           obj.getPapellido(),
           obj.getSapellido(),
           obj.getFnacimiento(),
           obj.getTelefono(),
           obj.getId_carrera(),
           obj.getId_prov()
       
       
       };
   
       modelo.addRow(data);
   }
    
    return modelo;
}

    
    
      public DefaultTableModel getModelo(String nombre){
        //cargarLista();
        
        lista =dao.getSearch(nombre);
        
        DefaultTableModel modelo = new DefaultTableModel();
 modelo.addColumn("ID");
   modelo.addColumn("DNI");
     modelo.addColumn("NOMBRE");
   modelo.addColumn("PAPELLIDO");
   modelo.addColumn("SAPELLIDO");
   modelo.addColumn("FNACIMIENTO");
   modelo.addColumn("TELEFONO");
   modelo.addColumn("ID_CARRERA");
   modelo.addColumn("ID_PROV");
  
   for(Profesor obj : lista){
       Object data[]=
       {
        obj.getId_profe(),
           obj.getDni(),
             obj.getNombre(),
           obj.getPapellido(),
           obj.getSapellido(),
           obj.getFnacimiento(),
           obj.getTelefono(),
           obj.getId_carrera(),
           obj.getId_prov()
       
       
       };
   
       modelo.addRow(data);
   }
    
    return modelo;
}

    
      
      
      


public void imprimir(JTable tabla, String nombre){
    tabla.setModel(getModelo(nombre));
}

public void imprimir(JTable tabla){
    tabla.setModel(getModelo());
}

 public boolean agegarProfesor(Profesor o){
     return dao.agegarProfesor(o);
 }

 
 public boolean modificarProfesor(Profesor o){
     return dao.modificarProfesor(o);
 }

 
 public boolean eliminarProfesor(Integer id){
     return dao.eliminarProfesor(id);
 }

 
 //crear metodo para la  carga masiva
public int cargaMasiva(String rutaArchivo){
    int registros_afectados=0;
        try {
            FileInputStream archivo = new FileInputStream(rutaArchivo);
            DataInputStream entrada = new DataInputStream(archivo);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String linea;
            List<Profesor> ListaCargaMa = new ArrayList<>();
            
            while((linea=buffer.readLine())!=null){
                System.out.println(linea);
                
                String[] campos = linea.split("\\|");
                ListaCargaMa.add(new Profesor(
                
                -1,
                        campos[0],//dni
                        campos[1],//nombre
                        campos[2],//P apellido
                        campos[3],//S apellido 
                        LocalDate.parse(campos[4]),
                          campos[5],//telefono
                        Integer.parseInt((campos[6])),   //id_provv
                        Integer.parseInt((campos[7]))   //id_provv
                ));
            }
            
            
             entrada.close();
            //agregar la logica para interactuar con BD
            
            int[] resultados =new ProfesorDao().cargaMasiva(ListaCargaMa);
            for(int i=0; i<resultados.length;i++)
                registros_afectados += resultados[i];
            
        } catch (Exception ex) {
            System.out.println("Error Carga Masiva: " + ex.getMessage());
        }
        
        return registros_afectados;
    }

 
}