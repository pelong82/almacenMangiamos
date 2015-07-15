    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import mangiamosalmacenfx.interfaces.FormInterface;
import mangiamosalmacenfx.logic.BranchLogic;
import mangiamosalmacenfx.model.Branch;
import mangiamosalmacenfx.util.Utilities;

/**
 *
 * @author Administrator
 */
public class BranchController implements FormInterface<Branch>{
    
    @FXML
    private TableColumn<Branch, String> tcName;

    @FXML
    private TextField txCity;

    @FXML
    private TableColumn<Branch, Number> tcPhone;

    @FXML
    private TextField txColony;

    @FXML
    private TextField txStreet;

    @FXML
    private TextField txPhone;

    @FXML
    private TextField txNumber;

    @FXML
    private TableColumn<Branch, String> tcColony;

    @FXML
    private TextField txName;

    @FXML
    private TableView<Branch> tvBranch;
    
    @FXML
    private Button btSave;

    @FXML
    private Button btDelete;
    
    @FXML
    private Button btNew;

    @FXML
    private Button btEdit;
    
    private BranchLogic branchLogic;
    
    @Override
    public void clearForm() {
        txCity.setText("");
        txColony.setText("");
        txName.setText("");
        txNumber.setText("");
        txPhone.setText("");
        txStreet.setText("");
        tvBranch.getSelectionModel().clearSelection();
    }

    @Override
    public void disableBtsDeleteSave() {
        btDelete.setDisable(true);
        btSave.setDisable(true);
    }

    @Override
    public void disableBtNew() {
        btNew.setDisable(true);
    }

    @Override
    public void enableBtsDeleteSave() {
        btDelete.setDisable(false);
        btSave.setDisable(false);
    }

    @Override
    public void enableBtNew() {
        btNew.setDisable(false);
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        disableBtsDeleteSave();
        tcName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tcColony.setCellValueFactory(cellData -> cellData.getValue().colonyProperty());
        tcPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        
        //Eventos de Botones
        btNew.setOnAction(event -> {
            clearForm();
            enableBtsDeleteSave();
            disableBtNew();
            
        });
        btSave.setOnAction(event -> {
            disableBtsDeleteSave();
            enableBtNew();
            saveOrUpdate();
            clearForm();
        });
        btDelete.setOnAction(event -> {
            remove();
            clearForm();
            disableBtsDeleteSave();
        });
        
        branchLogic = new BranchLogic();
        tvBranch.setItems(branchLogic.getBranchs());
        tvBranch.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showDetails(newValue);
                    enableBtsDeleteSave();
                    enableBtNew();
                });
      
        txPhone.setOnKeyTyped((KeyEvent event) -> validateTextFildNumbers(event));
        txNumber.setOnKeyTyped((KeyEvent event) -> validateTextFildNumbers(event));
        
    }
    

    @Override
    public void remove() {
        int row = tvBranch.getSelectionModel().getSelectedIndex();
        if(row != -1){
            Branch branchRemove = tvBranch.getItems().remove(row);
            branchLogic.delete(branchRemove.getBranchId());
            showMessageOk("Sucursal Eliminada");
        }
        else
            showMessageError("No Sepudo Eliminar La Sucursal");
    }
    
    /**
     *  Seleciona un update o save 
     */
    public void saveOrUpdate(){
        int row = tvBranch.getSelectionModel().getSelectedIndex();
        if(row == -1)
            save();
        else
            update(row);
    }

    @Override
    public void save() {
        Branch branch = new Branch();
        branch.setCity(txCity.getText());
        branch.setColony(txColony.getText());
        branch.setName(txName.getText());
        branch.setPhone(Utilities.toPhone(txPhone.getText()));
        branch.setStreet(txStreet.getText());
        branch.setStreetNumber(Utilities.convertInt(txNumber.getText()));
        if(branchLogic.save(branch)){
            showMessageOk("Datos Guardados");
        }
        else
            showMessageError("Error Al Guardar Los Datos");
        
    }

    @Override
    public void showDetails(Branch branch) {
        if(branch != null){
            txCity.setText(branch.getCity());
            txColony.setText(branch.getColony());
            txName.setText(branch.getName());
            txNumber.setText(""+branch.getStreetNumber());
            txPhone.setText(""+branch.getPhone());
            txStreet.setText(branch.getStreet());
        }
    }
    
    @Override
    public void showMessageError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("MENU SUCURSALES");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    @Override
    public void showMessageOk(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MENU SUCURSALES");
        alert.setHeaderText(text);
        alert.showAndWait();
    }
    

    @Override
    public void valiDataForm() {
        
    }
    
    /**
     * Valida Solo Numeros En textField
     * @param event 
     */
    public void validateTextFildNumbers(KeyEvent event){
        Character c = event.getCharacter().charAt(0);
        if(!Character.isDigit(c))
            event.consume();
    }
    
    public void update(int row){
        Branch branch = tvBranch.getItems().get(row);
        branch.setCity(txCity.getText());
        branch.setColony(txColony.getText());
        branch.setName(txName.getText());
        branch.setPhone(Utilities.toPhone(txPhone.getText()));
        branch.setStreet(txStreet.getText());
        branch.setStreetNumber(Utilities.convertInt(txNumber.getText()));
        if(branchLogic.update(branch)){
            showMessageOk("Datos Guardados");
        }
        else
            showMessageError("Error Al Guardar Los Datos");
    }
    
}
