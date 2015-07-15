/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import mangiamosalmacenfx.MainApp;

/**
 *
 * @author Administrator
 */
public class MainAppController{
    
    @FXML
    private Button btBranch;
    
    @FXML
    private Button btCategory;
    
    @FXML
    private Button btStatistics;

    private MainApp mainApp;
    
    @FXML
    void showBranch(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BranchFXML.fxml"));
            mainApp.getRootLayout().setCenter(null);
            mainApp.getRootLayout().setCenter((AnchorPane)loader.load());
            BranchController controller = loader.getController();
            //controller.ddd();
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    void showCategorys(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CategoryFXML.fxml"));
            mainApp.getRootLayout().setCenter(null);
            mainApp.getRootLayout().setCenter((SplitPane)loader.load());
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void showItem(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ItemFXML.fxml"));
            mainApp.getRootLayout().setCenter(null);
            mainApp.getRootLayout().setCenter((AnchorPane)loader.load());
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setMainApp(MainApp app){
        mainApp = app;
    }

    
}
