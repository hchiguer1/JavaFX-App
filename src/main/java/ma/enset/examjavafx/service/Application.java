package ma.enset.examjavafx.service;

import ma.enset.examjavafx.dao.CategoryDaoImp;
import ma.enset.examjavafx.dao.ProductDaoImpl;
import ma.enset.examjavafx.dao.entities.Category;
import ma.enset.examjavafx.dao.entities.Product;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Services services=new ServicesImpl(new ProductDaoImpl(),new CategoryDaoImp());
        Product p1=new Product();
        Category c1=new Category();
        c1.setId(1);
        c1.setName("Gaming");
        p1.setName("HP");
        p1.setReference("REF11111");
        p1.setPrice(13000);
        p1.setCategory(c1);
        services.addProduct(p1);
        List<Product> productList=services.getAllProduct();
        for(Product p:productList){
            System.out.println("ID : "+p.getId()+" Name : "+p.getName());
        }
    }
}
