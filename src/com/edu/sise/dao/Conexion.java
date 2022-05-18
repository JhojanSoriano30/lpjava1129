/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CLIENTE
 */
public class Conexion {
    
    //declarar  url user contraseña
    //apuntar a nustra maquina= 1 loclahost   2 127.0.0.1
    private static String url="jdbc:mysql://localhost:3306/lpjava1129";
    private static String user="root";
    private static String password="jhojan123";
    
    
    //declarar el driver
    private static String driver="com.mysql.cj.jdbc.Driver";
    
    
    //declarar tipo de conexion 
     Connection conn = null;
     
     static {
        try {
            Class.forName(driver);
                    
                    } catch (ClassNotFoundException ex) {
    System.out.println("Error en Driver: " + ex.getMessage());
                    
                    }
     
     }
   
    public Connection getConn(){
        try {
            conn= DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Error en DrinerManager: " + ex.getMessage());
        }
        return conn;
    }
    
}
