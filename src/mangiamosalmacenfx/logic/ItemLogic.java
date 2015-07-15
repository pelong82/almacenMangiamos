/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mangiamosalmacenfx.dao.CategoryDAO;
import mangiamosalmacenfx.dao.ItemDAO;
import mangiamosalmacenfx.model.Category;
import mangiamosalmacenfx.model.Item;

/**
 *
 * @author Administrator
 */
public class ItemLogic {
    
    private final ItemDAO itemDAO;
    private final ObservableList<Item> listItems;
    private final ObservableList<Category> listCategory;
    
    public ItemLogic(){
        itemDAO = new ItemDAO();
        listItems = FXCollections.observableArrayList();
        listItems.addAll(itemDAO.readAll());
        listCategory = FXCollections.observableArrayList();
        listCategory.addAll(new CategoryDAO().readAll());
    }
    
    /**
     * Devualve las categorias en observableList
     * @return ObservableList
     */
    public ObservableList<Category> getListCategorys(){
        return listCategory;
    }
    
    public ObservableList<String> getListCategoryNames(){
        ObservableList<String> lista = FXCollections.observableArrayList();
        listCategory.stream().forEach((cat) -> {
            lista.add(cat.getName());
        });
        return lista;
    } 
    
    /**
     * Devuelve los articulos en un objeto observableList
     * @return ObservableList
     */
    public ObservableList<Item> getListItems(){
        return listItems;
    }
    
    public boolean delete(int id){
        boolean res = itemDAO.delete(id);
        return res;
    }
}
