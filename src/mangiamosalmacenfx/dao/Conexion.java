/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mangiamosalmacenfx.interfaces.ConstantConexionDAO;

/**
 *
 * @author Hector Manuel Rodriguez Mendez
 */
public class Conexion implements ConstantConexionDAO{
    
    private static Conexion instance; //singleton
    private Connection connection;
    
    
    private Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(SERVER+DB,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        instance = null;
    }
    
    public synchronized static Conexion getInstance(){
        if(instance == null)
            instance = new Conexion();
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
}
