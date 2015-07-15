/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class Branch {
    private final IntegerProperty branchId;
    private final StringProperty name;
    private final LongProperty phone;
    private final StringProperty street;
    private final IntegerProperty streetNumber;
    private final StringProperty colony;
    private final StringProperty city;
    
    public Branch(){
        this(-1, null, -1, null, -1, null, null);
    }
    
    public Branch(int branchId, String name, long phone, String street, int streetNumber, String colony, String city){
        this.branchId = new SimpleIntegerProperty(branchId);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleLongProperty(phone);
        this.street = new SimpleStringProperty(street);
        this.streetNumber = new SimpleIntegerProperty(streetNumber);
        this.colony = new SimpleStringProperty(colony);
        this.city = new SimpleStringProperty(city);
    }
    
    public final int getBranchId(){
        return branchId.get();
    }
    
    public final void setBranchId(int branchId){
        this.branchId.set(branchId);
    }
    
    public IntegerProperty branchIdProperty(){
        return branchId;
    }
    
    public final String getName(){
        return name.get();
    }
    
    public final void setName(String name){
        this.name.set(name);
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    
    public final long getPhone(){
        return phone.get();
    }
    
    public final void setPhone(long phone){
        this.phone.set(phone);
    }
    
    public LongProperty phoneProperty(){
        return phone;
    }
    
    public final String getStreet(){
        return street.get();
    }
    
    public final void setStreet(String street){
        this.street.set(street);
    }
    
    public StringProperty streetProperty(){
        return street;
    }
    
    public final int getStreetNumber(){
        return streetNumber.get();
    }
    
    public final void setStreetNumber(int streetNumber){
        this.streetNumber.set(streetNumber);    
    }
    
    public IntegerProperty streetNumberProperty(){
        return streetNumber;
    }
    
    public final String getColony(){
        return colony.get();
    }
    
    public final void setColony(String colony){
        this.colony.set(colony);
    }
    
    public StringProperty colonyProperty(){
        return colony;
    }
    
    public final String getCity(){
        return city.get();
    }
    
    public final void setCity(String city){
        this.city.set(city);
    }
    
    public StringProperty cityProperty(){
        return city;
    }
}
