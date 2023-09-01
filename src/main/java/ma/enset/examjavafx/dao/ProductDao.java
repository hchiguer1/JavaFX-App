package ma.enset.examjavafx.dao;

import ma.enset.examjavafx.dao.entities.Product;

import java.util.List;

public interface ProductDao extends Dao<Product> {
    List<Product> findbyQuery(String query);
}
