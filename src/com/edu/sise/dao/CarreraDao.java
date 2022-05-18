//2
package com.edu.sise.dao;
import com.edu.sise.entidades.Departamento;
import com.edu.sise.entidades.Carrera;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
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
public class CarreraDao {
    
    Connection cn = null;
    
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public CarreraDao(){
        
        cn = new Conexion().getConn();
        
        
    }
 
    
    
    private Carrera getRS(ResultSet record) throws SQLException{
        
        return new Carrera(
                record.getInt("id_carrera"),
                        record.getString("nombre"));
                        
               
        
    }
    
    
    
    public List<Carrera> getAll(){
        List<Carrera> lista = new ArrayList<>();
        String sql = "select * from carreras";
        
        try {
            ps = cn.prepareStatement(sql);
            rs =ps.executeQuery(sql);
            while(rs.next()){
                lista.add(getRS(rs));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
   
    
    
    //buscar
     public List<Carrera> getSearch(String nombre){
        List<Carrera> lista = new ArrayList<>();
        String sql = "select * from carreras where nombre like ?";
        
        try {
            ps = cn.prepareStatement(sql);
            ps .setString(1, "%"+nombre+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                lista.add((getRS(rs)
                        
                ));
            }
     } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
    
     
     
     
     
     
     
     
     
    //select * from carreras where nombre like ?
    public boolean agegarCarrera(Carrera o){
        
        int cantidad=0;
        //cresr scrip
        String sql ="insert into carreras(nombre) values(?)";
       try{ 
//        st = cn.createStatement();
//        cantidad = st.executeUpdate(sql);
//        
ps= cn.prepareStatement(sql);
ps.setString(1, o.getNombre());
cantidad=ps.executeUpdate();



       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
     public boolean modificarCarrera(Carrera o){
        
        int cantidad=0;
        //cresr scrip
        String sql ="update carreras set nombre= ? where id_carrera = ?";
       try{ 
//        st = cn.createStatement();
//        cantidad = st.executeUpdate(sql);
        

ps = cn.prepareStatement(sql);
ps.setString(1, o.getNombre());
ps.setInt(2, o.getId_carrera());
cantidad= ps.executeUpdate();


       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
     
     public boolean eliminarCarrera(Integer id){
        
        int cantidad=0;
        //cresr scrip
        String sql ="delete from carreras where id_carrera= ?";
       try{ 
        ps = cn.prepareStatement(sql);
        ps.setInt(1, id);
        cantidad = ps.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
     
     
     
     
    
}
