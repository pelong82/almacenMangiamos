/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mangiamosalmacenfx.dao.ItemDAO;
import mangiamosalmacenfx.interfaces.FormInterface;
import mangiamosalmacenfx.logic.ItemLogic;
import mangiamosalmacenfx.model.Category;
import mangiamosalmacenfx.model.Item;
import mangiamosalmacenfx.util.Utilities;

/**
 *
 * @author Administrator
 */
public class ItemController implements FormInterface<Item>{
    
    
    @FXML
    private TableColumn<Item, String> tcName;

    @FXML
    private TextField txPrice;

    @FXML
    private TextField txStock;

    @FXML
    private Button btNew;
    
    @FXML
    private Button btAdd;

    @FXML
    private TableColumn<Item, Number> tcPrice;

    @FXML
    private Button btSave;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private Button btDelete;

    @FXML
    private TextField txName;

    @FXML
    private TableView<Item> tvItems;
    
    private ItemLogic itemLogic;
    

    @Override
    public void clearForm() {
        txName.setText("");
        txPrice.setText("");
        txStock.setText("");
        cbCategory.getSelectionModel().select(0);
        
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
        btAdd.setVisible(false);
        itemLogic = new ItemLogic();
        formatTable();
        cbCategory.setItems(itemLogic.getListCategoryNames());
        
    }
    
    private void formatTable(){
       tcName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
       tcPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
       tcPrice.setCellFactory(col -> 
        new TableCell<Item, Number>(){
            @Override
            public void updateItem(Number price, boolean empty){
                super.updateItem(price, empty);
                if(empty){
                    setText(null);
                }
               else{
                    setText(Utilities.fomotMoney(price.doubleValue()));
                }
                setAlignment(Pos.CENTER);
            }
        });
        ObservableList<Item> lista = FXCollections.observableArrayList();
        lista.addAll(new ItemDAO().readAll());  
        tvItems.setItems(lista);
        tvItems.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showDetails(newValue);
                }
        );
    }

    @Override
    public void remove() {
       int row = tvItems.getSelectionModel().getSelectedIndex();
       if(row != -1){
           Item item = tvItems.getItems().remove(row);
           if(itemLogic.delete(item.getItemId()))
               showMessageOk("DatToEliminado");
           else
               showMessageError("No Sepudo Eliminar El Producto");
                
       }
    }

    @Override
    public void save() {
        
    }

    @Override
    public void showDetails(Item item) {
        txName.setText(item.getName());
        txStock.setText(""+item.getStock());
        txPrice.setText(Utilities.fomotMoney(item.getPrice()));
        cbCategory.getSelectionModel().select(item.getCategory().getName());
    }

    @Override
    public void showMessageError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("MENU PRODUCTOS");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    @Override
    public void showMessageOk(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MENU PRODUCTOS");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    @Override
    public void valiDataForm() {
        
    }
    
}
