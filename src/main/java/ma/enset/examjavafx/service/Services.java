package ma.enset.examjavafx.service;

import ma.enset.examjavafx.dao.entities.Category;
import ma.enset.examjavafx.dao.entities.Product;

import java.util.List;

public interface Services {
    List<Product>getAllProduct();
    List<Product>searchProductByQuery(String query);
    void addProduct(Product p);
    void updateProduct(Product p);
    void deleteProduct(Product p);
    List<Category> getAllCategories();
    void addCategory(Category c);
    void deleteCategory(Category c);

}
