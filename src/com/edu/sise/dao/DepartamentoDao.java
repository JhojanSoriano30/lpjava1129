/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.dao;
import com.edu.sise.entidades.Departamento;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author CLIENTE
 */
public class DepartamentoDao {
    
    Connection cn = null;
    
    Statement st = null;
    ResultSet rs = null;
    
    
    public DepartamentoDao(){
        
        cn = new Conexion().getConn();
        
        
    }
 
    public List<Departamento> getAll(){
        List<Departamento> lista = new ArrayList<>();
        String sql = "select * from departamentos";
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Departamento(
                rs.getInt("id_depa"),
                        rs.getString("nombre")
                ));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
   
    
    public boolean agegarDepartamento(Departamento o){
        
        int cantidad=0;
        //cresr scrip
        String sql ="insert into departamentos(nombre) values('"+o.getNombre()+"')";
       try{ 
        st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
     public boolean modificarDepartamento(Departamento o){
        
        int cantidad=0;
        //cresr scrip
        String sql ="update departamentos set nombre= '"+o.getNombre()+"' where id_depa = " + o.getId_depa();
       try{ 
        st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
     
     public boolean eliminarDepartamento(Integer id){
        
        int cantidad=0;
        //cresr scrip
        String sql ="delete from departamentos where id_depa= " + id;
       try{ 
        st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
     
     
     
     
    
}
