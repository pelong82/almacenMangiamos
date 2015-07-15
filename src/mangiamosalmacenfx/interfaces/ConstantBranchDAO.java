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
public interface ConstantBranchDAO {
    
    public static final String SQL_INSERT = "INSERT INTO branchs(name,phone,street,street_number,colony,city) VALUES(?,?,?,?,?,?)";
    public static final String SQL_SELECT_ALL = "SELECT * FROM branchs";
    public static final String SQL_SELECT = "SELECT * FROM branchs WHERE branch_id = ?";
    public static final String SQL_DELETE = "DELETE FROM branchs WHERE branch_id = ? LIMIT 1";
    public static final String SQL_UPDATE = "UPDATE branchs SET name = ?, phone = ?, street = ?, street_number = ?, "
            + "colony = ?, city = ? WHERE branch_id = ? LIMIT 1";
    
}
