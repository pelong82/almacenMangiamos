/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.interfaces;

/**
 *
 * @author Administrator
 */
public interface ConstantCategoryDAO {
    
    public static final String SQL_INSERT = "INSERT INTO categorys(name) VALUES(?)";
    public static final String SQL_SELECT_ALL = "SELECT * FROM categorys";
    public static final String SQL_SELECT = "SELECT * FROM categorys WHERE category_id = ?";
    public static final String SQL_DELETE = "DELETE FROM categorys WHERE category_id = ? LIMIT 1";
    public static final String SQL_UPDATE = "UPDATE categorys SET name = ? WHERE category_id = ? LIMIT 1";
    
}
