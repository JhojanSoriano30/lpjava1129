/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.dao;
import com.edu.sise.entidades.Departamento;
import com.edu.sise.entidades.Tutor;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Date;
import javax.accessibility.AccessibleState;


/**
 *
 * @author CLIENTE
 */
public class TutorDao {
    
    Connection cn = null;
    
    Statement st = null;
    ResultSet rs = null;
    
    CallableStatement cs = null;
    
    final String INSERTAR = "{call pa_insertar_tutores(?,?,?,?,?,?,?)}";
    final String MODIFICAR = "{call pa_modificar_tutor(?,?,?,?,?,?,?,?)}";
    final String ELIMINAR = "{call pa_eliminar_tutor(?)}";
    final String TODOS = "{call pa_listar_tutores()}";
    final String FILTRO = "{call pa_buscar_tutores(?)}";
    
    public TutorDao(){
        
        cn = new Conexion().getConn();
        System.out.println("Se instancio TutorDAO");

        
    }
 
    public List<Tutor> getAll(){
        List<Tutor> lista = new ArrayList<>();
       // String sql = "{call pa_listar_tutores()}";
        
        try {
            //st = cn.createStatement();
            cs=(CallableStatement) cn.prepareCall(TODOS);
            rs = cs.executeQuery();
            while(rs.next()){
                lista.add(new Tutor(
                rs.getInt("id_tutor"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("papellido"),
                        rs.getString("sapellido"),
                
                        rs.getDate("fnacimiento").toLocalDate(),
                        rs.getString("telefono"),
                        rs.getInt("id_prov")));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
   
    
    public boolean agegarTutor(Tutor o){
        
        int cantidad=0;
        //cresr scrip
       // String sql ="{call pa_insertar_tutores(?,?,?,?,?,?,?)}";
       try{ 
        cs = cn.prepareCall(INSERTAR);
        int x=1;
        cs.setString(x++, o.getDni());
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getPapellido());
        cs.setString(x++, o.getSapellido());
        cs.setDate(x++, Date.valueOf(o.getFnacimiento()));
        cs.setString(x++, o.getTelefono());
        cs.setInt(x++, o.getId_prov());
      
       cantidad = cs.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
    
    
    
     public List<Tutor> getSearch(String nombre){
        List<Tutor> lista = new ArrayList<>();
       // String sql = "{call pa_buscar_tutores(?)}";
        
        try {
            cs=cn.prepareCall(FILTRO);
          cs.setString(1, nombre);
          rs=cs.executeQuery();
        while(rs.next()){
                lista.add(new Tutor(
                rs.getInt("id_tutor"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("papellido"),
                        rs.getString("sapellido"), 
                        rs.getDate("fnacimiento").toLocalDate(),
                        rs.getString("telefono"),
                        rs.getInt("id_prov")
                
                ));
            }
        

     } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
    
    
    
    
    
    
    
     public boolean modificarTutor(Tutor o){
        
        int cantidad=0;
        //cresr scrip
        //String sql ="{call pa_modificar_tutor(?,?,?,?,?,?,?,?)}";
       try{ 
         cs = cn.prepareCall(MODIFICAR);
        int x=1;
        cs.setString(x++, o.getDni());
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getPapellido());
        cs.setString(x++, o.getSapellido());
        cs.setDate(x++, Date.valueOf(o.getFnacimiento()));
        cs.setString(x++, o.getTelefono());
        cs.setInt(x++, o.getId_prov());
        cs.setInt(x++, o.getId_tutor());
      
       cantidad = cs.executeUpdate();
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
     
     public boolean eliminarTutor(Integer id){
        
        int cantidad=0;
        //cresr scrip
        //String sql ="{call pa_eliminar_tutor(?)}";
       try{ 
        cs= cn.prepareCall(ELIMINAR);
        cs.setInt(1, id);
        cantidad = cs.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
     
     
     public int[] cargaMasiva(List<Tutor> lista)
     {
         
         int[] rpta = null;
          //String sql ="{call pa_insertar_tutores(?,?,?,?,?,?,?)}";
         
         try {
             cs=cn.prepareCall(INSERTAR);
         for(Tutor o: lista){
             
               int x=1;
        cs.setString(x++, o.getDni());
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getPapellido());
        cs.setString(x++, o.getSapellido());
        cs.setDate(x++, Date.valueOf(o.getFnacimiento()));
        cs.setString(x++, o.getTelefono());
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
