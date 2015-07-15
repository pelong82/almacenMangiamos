/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.dao;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mangiamosalmacenfx.interfaces.CRUD;
import mangiamosalmacenfx.interfaces.ConstantBranchDAO;
import mangiamosalmacenfx.model.Branch;

/**
 *
 * @author Héctor Manuel Rodríguez Méndez
 */
public class BranchDAO implements CRUD<Branch>, ConstantBranchDAO{
    
    private static Conexion conexion;
    private Connection connection;
    private PreparedStatement prdstCreate;
    private PreparedStatement prdstDelete;
    private PreparedStatement prdstUpdate;
    private PreparedStatement prdstRead;
    private PreparedStatement prdstReadAll;
    
    public BranchDAO(){
        conect();
        makeSQL();
    }

    public void closeConnection(){
        conexion.close();
    }
    
    public final void conect(){
        conexion = Conexion.getInstance();
        connection = conexion.getConnection();
    }
    
    @Override
    public boolean create(Branch branch) {
        try {
            prdstCreate.setString(1, branch.getName());
            prdstCreate.setLong(2, branch.getPhone());
            prdstCreate.setString(3, branch.getStreet());
            prdstCreate.setInt(4, branch.getStreetNumber());
            prdstCreate.setString(5, branch.getColony());
            prdstCreate.setString(6, branch.getCity()); 
            int res = prdstCreate.executeUpdate();
            ResultSet resId = prdstCreate.getGeneratedKeys();
            if(resId.next())
                branch.setBranchId(resId.getInt(1));
            if(res > 0)
                return true;
        } catch (MySQLIntegrityConstraintViolationException ex) {
            Logger.getLogger(BranchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean delete(int id) {
        try {
            prdstDelete.setInt(1, id);
            int res = prdstDelete.executeUpdate();
            if(res > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(BranchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Crea El CRUD De La Base De Datos
     */
    public final void makeSQL(){
        try {
            prdstCreate = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            prdstDelete = connection.prepareStatement(SQL_DELETE);
            prdstReadAll = connection.prepareStatement(SQL_SELECT_ALL);
            prdstRead = connection.prepareStatement(SQL_SELECT);
            prdstUpdate = connection.prepareStatement(SQL_UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(BranchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Branch read(int id) {
        Branch branch = null;
        try {
            prdstRead.setInt(1, id);
            ResultSet res = prdstRead.executeQuery();
            while(res.next()){
                branch = new Branch();
                branch.setBranchId(res.getInt("branch_id"));
                branch.setCity(res.getString("city"));
                branch.setColony(res.getString("colony"));
                branch.setName(res.getString("name"));
                branch.setPhone(res.getLong("phone"));
                branch.setStreet(res.getString("street"));
                branch.setStreetNumber(res.getInt("street_number"));
                return branch;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BranchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return branch;
    }

    @Override
    public ArrayList<Branch> readAll() {
        ArrayList<Branch> listBranchs = new ArrayList<>();
        try {
            ResultSet res = prdstReadAll.executeQuery();
            while(res.next()){
                Branch branch = new Branch();
                branch.setBranchId(res.getInt("branch_id"));
                branch.setCity(res.getString("city"));
                branch.setColony(res.getString("colony"));
                branch.setName(res.getString("name"));
                branch.setPhone(res.getLong("phone"));
                branch.setStreet(res.getString("street"));
                branch.setStreetNumber(res.getInt("street_number"));
                listBranchs.add(branch);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BranchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBranchs;
    }

    @Override
    public boolean update(Branch branch) {
        try {
            prdstUpdate.setString(1, branch.getName());
            prdstUpdate.setLong(2, branch.getPhone());
            prdstUpdate.setString(3, branch.getStreet());
            prdstUpdate.setInt(4, branch.getStreetNumber());
            prdstUpdate.setString(5, branch.getColony());
            prdstUpdate.setString(6, branch.getCity());
            prdstUpdate.setInt(7, branch.getBranchId());
            int res = prdstUpdate.executeUpdate();
            if(res > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(BranchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
