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
import mangiamosalmacenfx.interfaces.ConstantCategoryDAO;
import mangiamosalmacenfx.model.Category;

/**
 *
 * @author Hector Manuel Rodriguez Mendez
 */
public class CategoryDAO implements CRUD<Category>, ConstantCategoryDAO{
    
    private static Conexion conexion;
    private Connection connection;
    private PreparedStatement prdstCreate;
    private PreparedStatement prdstDelete;
    private PreparedStatement prdstUpdate;
    private PreparedStatement prdstRead;
    private PreparedStatement prdstReadAll;
    
    public CategoryDAO(){
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

    /**
     * Guarda un objeto Category en la Base De Datos
     * @param category objeto Category a Guardar
     * @return Devuelve true si no hubo problemas o
     * false en caso contrario
     */
    @Override
    public boolean create(Category category) {
        try {
            prdstCreate.setString(1, category.getName());
            int res = prdstCreate.executeUpdate();
            ResultSet resId = prdstCreate.getGeneratedKeys();
            if(resId.next())
                category.setCategoryId(resId.getInt(1));
            if(res > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Elimina una Categoria de la DB
     * @param id id de la categoria a eliminar
     * @return Devuelve true si tuvo exito o false en caso contrario
     */
    @Override
    public boolean delete(int id) {
        try {
            prdstDelete.setInt(1, id);
            int res = prdstDelete.executeUpdate();
            if(res > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Recupera una categoria de la DB
     * @param id id de la Categoria a recuperar
     * @return Devuelve un objeto Category si tuvo exito
     * o null en caso contrario
     */
    @Override
    public Category read(int id) {
        Category category = null;
        try {
            prdstRead.setInt(1, id);
            ResultSet res = prdstRead.executeQuery();
            while(res.next()){
                category = new Category();
                category.setCategoryId(res.getInt("category_id"));
                category.setName(res.getString("name"));
            }
            return category;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    /**
     * Devuelve todas las categorias de la DB
     * @return regresa un objeto ArrayList con todos las categorias
     */
    @Override
    public ArrayList<Category> readAll() {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            ResultSet res = prdstReadAll.executeQuery();
            while(res.next()){
                Category category = new Category();
                category.setCategoryId(res.getInt("category_id"));
                category.setName(res.getString("name"));
                listCategory.add(category);
            }
            return listCategory;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategory;
    }

    /**
     * Actualiza los datos de un categoria en la DB
     * @param category Objeto Category a actualizar
     * @return Devuelve true si tuvo exito o false
     * en caso contrario
     */
    @Override
    public boolean update(Category category) {
        try {
            prdstUpdate.setString(1, category.getName());
            prdstUpdate.setInt(2, category.getCategoryId());
            int res = prdstUpdate.executeUpdate();
            if(res > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
