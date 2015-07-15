/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.logic;


import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mangiamosalmacenfx.dao.BranchDAO;
import mangiamosalmacenfx.model.Branch;

/**
 *
 * @author Héctor Manuel Rodríguez Méndez
 */
public class BranchLogic {
    
    private BranchDAO branchDAO;
    private ObservableList<Branch> listBranchs;
    
    public BranchLogic(){
        branchDAO = new BranchDAO();
        listBranchs = FXCollections.observableArrayList();
        listBranchs.addAll(branchDAO.readAll());
    }
    
    public void conect(){
        branchDAO.conect();
    }
    
    public void closeConnection(){
        branchDAO.closeConnection();
    }
    
    public void delete(int id){
        branchDAO.delete(id);
    }
    
    public ObservableList<Branch> getBranchs(){
        return listBranchs;
    }
    
    /**
     * Guarda los datos en la base de datos
     * @param branch
     * @return 
     */
    public boolean save(Branch branch){
        boolean res = branchDAO.create(branch);
        listBranchs.add(branch);
        return res;
    }
    
    public boolean update(Branch branch){
        boolean res = branchDAO.update(branch);
        return res;
    }
    
}
