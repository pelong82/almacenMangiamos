/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mangiamosalmacenfx.interfaces.CRUD;
import mangiamosalmacenfx.interfaces.ConstantItemDAO;
import mangiamosalmacenfx.model.Category;
import mangiamosalmacenfx.model.Item;

/**
 *
 * @author Héctor Manuel Rodríguez Méndez
 */
public class ItemDAO implements CRUD<Item>, ConstantItemDAO {
    
    private static Conexion conexion;
    private Connection connection;
    private PreparedStatement prdstCreate;
    private PreparedStatement prdstDelete;
    private PreparedStatement prdstUpdate;
    private PreparedStatement prdstRead;
    private PreparedStatement prdstReadAll;
    
    public ItemDAO(){
        conect();
        makeSQL();
    }
    
    public void closeConnection(){
        conexion.close();
    }
    
    public void conect(){
        conexion = Conexion.getInstance();
        connection = conexion.getConnection();
    }

    @Override
    public boolean create(Item item) {
        try {
            prdstCreate.setString(1, item.getName());
            prdstCreate.setInt(2, item.getCategory().getCategoryId());
            prdstCreate.setDouble(3, item.getPrice());
            prdstCreate.setInt(4, item.getStock());
            int res = prdstCreate.executeUpdate();
            ResultSet resId = prdstCreate.getGeneratedKeys();
            if(resId.next())
                item.setItemId(resId.getInt(1));
            if(res > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     /**
     * Crea El CRUD De La Base De Datos
     */
    public void makeSQL(){
        try {
            prdstCreate = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            prdstDelete = connection.prepareStatement(SQL_DELETE);
            prdstReadAll = connection.prepareStatement(SQL_SELECT_ALL);
            prdstRead = connection.prepareStatement(SQL_SELECT);
            prdstUpdate = connection.prepareStatement(SQL_UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Item read(int id) {
        Item item = null;
        try {
            prdstRead.setInt(1, id);
            ResultSet res = prdstRead.executeQuery();
            while(res.next()){
                item = new Item();
                Category category = new Category();
                category.setCategoryId(res.getInt("category_id"));
                category.setName(res.getString("category_name"));
                item.setCategory(category);
                item.setItemId(res.getInt("item_id"));
                item.setName(res.getString("name"));
                item.setPrice(res.getDouble("unit_price"));
                item.setStock(res.getInt("stock"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    @Override
    public ArrayList<Item> readAll() {
        ArrayList<Item> listItems = new ArrayList<>();
        try {
            ResultSet res = prdstReadAll.executeQuery();
            while(res.next()){
                Item item = new Item();
                Category category = new Category();
                category.setCategoryId(res.getInt("category_id"));
                category.setName(res.getString("category_name"));
                item.setCategory(category);
                item.setItemId(res.getInt("item_id"));
                item.setName(res.getString("name"));
                item.setPrice(res.getDouble("unit_price"));
                item.setStock(res.getInt("stock"));
                listItems.add(item);
            }
            return listItems;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(Item item) {
        try {
            prdstUpdate.setString(1, item.getName());
            prdstUpdate.setInt(2, item.getCategory().getCategoryId());
            prdstUpdate.setDouble(3, item.getPrice());
            prdstUpdate.setInt(4, item.getStock());
            prdstUpdate.setInt(5, item.getItemId());
            int res = prdstUpdate.executeUpdate();
            if(res > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
