/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @param <AnyObject>
 */
public interface CRUD <AnyObject>{
    
    public boolean create(AnyObject anyObject);
    public boolean delete(int id);
    public AnyObject read(int id);
    public ArrayList<AnyObject> readAll();
    public boolean update(AnyObject anyObject);    
    
}
