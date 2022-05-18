/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.dao;
import com.edu.sise.entidades.Departamento;
import com.edu.sise.entidades.Profesor;
import com.edu.sise.entidades.Profesor;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Date;
import javax.accessibility.AccessibleState;


public class ProfesorDao {
    
    Connection cn = null;
    
    Statement st = null;
    ResultSet rs = null;
    
    CallableStatement cs = null;
    
    final String INSERTAR = "{call pa_insertar_profesores(?,?,?,?,?,?,?,?)}";
    final String MODIFICAR = "{call pa_modificar_profesor(?,?,?,?,?,?,?,?,?)}";
    final String ELIMINAR = "{call pa_eliminar_profesor(?)}";
    final String TODOS = "{call pa_listar_profesores()}";
    final String FILTRO = "{call pa_buscar_profesores(?)}";
    
    public ProfesorDao(){
        
        cn = new Conexion().getConn();
 

        
    }
 
    public List<Profesor> getAll(){
        List<Profesor> lista = new ArrayList<>();
       // String sql = "{call pa_listar_profesores()}";
        
        try {
            //st = cn.createStatement();
            cs=(CallableStatement) cn.prepareCall(TODOS);
            rs = cs.executeQuery();
            while(rs.next()){
                lista.add(new Profesor(
                rs.getInt("id_profe"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("papellido"),
                        rs.getString("sapellido"),
                
                        rs.getDate("fnacimiento").toLocalDate(),
                        rs.getString("telefono"),
                        rs.getInt("id_carrera"),
                        rs.getInt("id_prov")
                ));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
   
    
    public boolean agegarProfesor(Profesor o){
        
        int cantidad=0;
        //cresr scrip
       // String sql ="{call pa_insertar_profesores(?,?,?,?,?,?,?)}";
       try{ 
        cs = cn.prepareCall(INSERTAR);
        int x=1;
        cs.setString(x++, o.getDni());
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getPapellido());
        cs.setString(x++, o.getSapellido());
        cs.setDate(x++, Date.valueOf(o.getFnacimiento()));
        cs.setString(x++, o.getTelefono());
        cs.setInt(x++, o.getId_carrera());
        cs.setInt(x++, o.getId_prov());
      
       cantidad = cs.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
    
    
    
     public List<Profesor> getSearch(String nombre){
        List<Profesor> lista = new ArrayList<>();
       // String sql = "{call pa_buscar_profesores(?)}";
        
        try {
            cs=cn.prepareCall(FILTRO);
          cs.setString(1, nombre);
          rs=cs.executeQuery();
        while(rs.next()){
                lista.add(new Profesor(
          rs.getInt("id_profe"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("papellido"),
                        rs.getString("sapellido"),
                
                        rs.getDate("fnacimiento").toLocalDate(),
                        rs.getString("telefono"),
                        rs.getInt("id_carrera"),
                        rs.getInt("id_prov")
                
                ));
            }
        

     } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
    
    
    
    
    
    
    
     public boolean modificarProfesor(Profesor o){
        
        int cantidad=0;
        //cresr scrip
        //String sql ="{call pa_modificar_profesor(?,?,?,?,?,?,?,?)}";
       try{ 
         cs = cn.prepareCall(MODIFICAR);
        int x=1;
        cs.setString(x++, o.getDni());
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getPapellido());
        cs.setString(x++, o.getSapellido());
        cs.setDate(x++, Date.valueOf(o.getFnacimiento()));
        cs.setString(x++, o.getTelefono());
        cs.setInt(x++, o.getId_carrera());
        cs.setInt(x++, o.getId_prov());
        cs.setInt(x++, o.getId_profe());
      
       cantidad = cs.executeUpdate();
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
     
     public boolean eliminarProfesor(Integer id){
        
        int cantidad=0;
        //cresr scrip
        //String sql ="{call pa_eliminar_profesor(?)}";
       try{ 
        cs= cn.prepareCall(ELIMINAR);
        cs.setInt(1, id);
        cantidad = cs.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
     
     
     public int[] cargaMasiva(List<Profesor> lista)
     {
         
         int[] rpta = null;
          //String sql ="{call pa_insertar_profesores(?,?,?,?,?,?,?)}";
         
         try {
             cs=cn.prepareCall(INSERTAR);
         for(Profesor o: lista){
             
               int x=1;
  cs.setString(x++, o.getDni());
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getPapellido());
        cs.setString(x++, o.getSapellido());
        cs.setDate(x++, Date.valueOf(o.getFnacimiento()));
        cs.setString(x++, o.getTelefono());
        cs.setInt(x++, o.getId_carrera());
        cs.setInt(x++, o.getId_prov());
        cs.addBatch();
         }    
             
         rpta= cs.executeBatch();
             
          } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
          
         return rpta;
         
     }
     
     
     
     
    
}
