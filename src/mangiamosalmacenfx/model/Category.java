/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class Category {
    
    private final IntegerProperty categoryId;
    private final StringProperty name;
    
    public Category(){
        this(0, "");
    }
    
    public Category(int categoryId, String name){
        this.categoryId = new SimpleIntegerProperty(categoryId);
        this.name = new SimpleStringProperty(name);
    }
    
    public final int getCategoryId(){
        return categoryId.get();
    }
    
    public final void setCategoryId(int categoryId){
        this.categoryId.set(categoryId);
    }
    
    public final IntegerProperty categoryProperty(){
        return categoryId;
    }
    
    public final String getName(){
        return name.get();
    }
    
    public final void setName(String name){
        this.name.set(name);
    }
    
    public final StringProperty nameProperty(){
        return name;
    }
}

