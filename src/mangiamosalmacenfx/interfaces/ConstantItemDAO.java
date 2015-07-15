/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.interfaces;

/**
 *
 * @author Héctor Manuel Rodríguez Méndez
 */
public interface ConstantItemDAO {
    
    public static final String SQL_INSERT = "INSERT INTO items(name,category_id,unit_price,stock) VALUES(?,?,?,?)";
    public static final String SQL_SELECT_ALL = "SELECT items.item_id, items.name, items.category_id, items.unit_price, "
            + "items.stock, cat.name AS category_name "
            + "FROM items INNER JOIN categorys AS cat ON items.category_id = cat.category_id";
    public static final String SQL_SELECT = "SELECT items.item_id, items.name, items.category_id, items.unit_price, items.stock, cat.name AS category_name FROM items INNER JOIN categorys AS cat ON \n" +
"items.category_id = cat.category_id WHERE items.item_id = ?";
    public static final String SQL_DELETE = "DELETE FROM items WHERE item_id = ? LIMIT 1";
    public static final String SQL_UPDATE = "UPDATE items SET name = ?, category_id = ?, unit_price = ?, stock = ? WHERE item_id = ?";
    
}
