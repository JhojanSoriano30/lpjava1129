/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.dao;
import com.edu.sise.entidades.Departamento;
import com.edu.sise.entidades.Provincia;
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
public class ProvinciaDao {
    
    Connection cn = null;
    
    Statement st = null;
    ResultSet rs = null;
    
    
    public ProvinciaDao(){
        
        cn = new Conexion().getConn();
        
        
    }
 
    public List<Provincia> getAll(){
        List<Provincia> lista = new ArrayList<>();
        String sql = "select prov.*, depa.nombre 'des_id_depa'\n" +
"from provincias prov \n"
     + "inner join departamentos depa on(prov.id_depa = depa.id_depa)";
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Provincia(
                rs.getInt("id_prov"),
                        rs.getString("nombre"),
                        rs.getInt("id_depa"),
                        rs.getString("des_id_depa")//nombre del departamento
                ));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
   
    
    public boolean agegarProvincia(Provincia o){
        
        int cantidad=0;
        //cresr scrip
        String sql ="insert into provincias(nombre,id_depa) values('"+o.getNombre()+"','"+o.getId_depa()+"')";
       try{ 
        st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
     public boolean modificarProvincia(Provincia o){
        
        int cantidad=0;
        //cresr scrip
        String sql ="update provincias set nombre= '"+o.getNombre()+"', id_depa='"+o.getId_depa()+"' where id_prov = " + o.getId_prov();
       try{ 
        st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
     
     public boolean eliminarProvincia(Integer id){
        
        int cantidad=0;
        //cresr scrip
        String sql ="delete from provincias where id_prov = "+id;
       try{ 
        st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
     
     
     
     
    
}
