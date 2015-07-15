/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class Item {
    
    private final IntegerProperty itemId;
    private final StringProperty name;
    private final ObjectProperty<Category> category;
    private final DoubleProperty price;
    private final IntegerProperty stock;
    
    public Item(){
        this(0, null, null, 0, 0);
    }
    
    public Item(int itemId, String name, Category category, double price, int stock){
        this.itemId = new SimpleIntegerProperty(itemId);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleObjectProperty<>(category);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
    }
    
    public final Category getCategory(){
        return category.get();
    }
    
    public final void setCategory(Category category){
        this.category.set(category);
    }
    
    public final ObjectProperty<Category> categoryProperty(){
        return category;
    }
    
    public final int getItemId(){
        return itemId.get();
    }
    
    public final void setItemId(int itemId){
        this.itemId.set(itemId);
    }
    
    public final IntegerProperty itemIdProperty(){
        return itemId;
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
    
    public final double getPrice(){
        return price.get();
    }
    
    public final void setPrice(double price){
        this.price.set(price);
    }
    
    public final DoubleProperty priceProperty(){
        return price;
    }
    
    public final int getStock(){
        return stock.get();
    }
    
    public final void setStock(int stock){
        this.stock.set(stock);
    }
    
    public final IntegerProperty stcokProperty(){
        return stock;
    }
    
}
