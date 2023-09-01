package ma.enset.examjavafx.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.examjavafx.dao.CategoryDaoImp;
import ma.enset.examjavafx.dao.ProductDaoImpl;
import ma.enset.examjavafx.dao.entities.Category;
import ma.enset.examjavafx.dao.entities.Product;
import ma.enset.examjavafx.service.Services;
import ma.enset.examjavafx.service.ServicesImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable{
    @FXML private TextField textName;
    @FXML private TextField textRef;
    @FXML private TextField textPrice;
    @FXML private TextField textSearch;
    @FXML private ComboBox<Category> productCategory;
    @FXML private TableView<Product> tableProduct;
    @FXML private TableColumn<Long,Product> columnID=new TableColumn<Long,Product>("id");
    @FXML private TableColumn<String,Product> columnName=new TableColumn<String,Product>("name");
    @FXML private TableColumn<String,Product> columnReference=new TableColumn<String,Product>("reference");
    @FXML private TableColumn<Float,Product> columnPrice=new TableColumn<Float,Product>("price");
    @FXML private TableColumn<Category,Product> columnCategory=new TableColumn<Category,Product>("category");
    private Product selectedP;
    private Services services;
    ObservableList<Product> data= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableProduct.setItems(data);
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        services=new ServicesImpl(new ProductDaoImpl(),new CategoryDaoImp());
        productCategory.getItems().addAll(services.getAllCategories());
        loadData();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                data.clear();
                data.addAll(services.searchProductByQuery(t1));
            }
        });

    }
    private void loadData(){
        data.clear();
        data.addAll(services.getAllProduct());
    }
    public void addProduct(){
        Product p=new Product();
        p.setName(textName.getText());
        p.setReference(textRef.getText());
        p.setPrice(Float.parseFloat(textPrice.getText()));
        p.setCategory(productCategory.getSelectionModel().getSelectedItem());
        services.addProduct(p);
        loadData();
        textName.clear();
        textRef.clear();
        textPrice.clear();
        productCategory.getItems().clear();
    }
    public void deleteProduct(){
        Product p=tableProduct.getSelectionModel().getSelectedItem();
        services.deleteProduct(p);
        loadData();
    }
    public void editProduct(){
        selectedP=tableProduct.getSelectionModel().getSelectedItem();
        textName.setText(selectedP.getName());
        textRef.setText(selectedP.getReference());
        textPrice.setText(String.valueOf(selectedP.getPrice()));

    }
    public void updateProduct(){
        selectedP.setName(textName.getText());
        selectedP.setReference(textRef.getText());
        selectedP.setPrice(Float.parseFloat(textPrice.getText()));
        selectedP.setCategory(productCategory.getSelectionModel().getSelectedItem());
        services.updateProduct(selectedP);
        loadData();
    }
}
