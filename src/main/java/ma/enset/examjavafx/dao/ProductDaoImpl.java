package ma.enset.examjavafx.dao;

import ma.enset.examjavafx.dao.entities.Category;
import ma.enset.examjavafx.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product find(long id) {
        Product p=new Product();
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from PRODUCT where ID=?");
            pst.setLong(1,id);
            ResultSet rs=pst.executeQuery();

            if(rs.next()){
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setReference(rs.getString("REFERENCE"));
                p.setPrice(rs.getFloat("PRICE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products=new ArrayList<>();
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from PRODUCT");
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Product p=new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setPrice(rs.getFloat("PRICE"));
                p.setReference(rs.getString("REFERENCE"));
                PreparedStatement pst1 = connection.prepareStatement("select * from CATEGORY where ID=?");
                pst1.setLong(1,rs.getLong("ID_CAT"));
                ResultSet rs1=pst1.executeQuery();
                Category c=new Category();
                if(rs1.next()){
                    c.setName(rs1.getString("NAME"));
                }
                p.setCategory(c);
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public void save(Product o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("insert into PRODUCT (NAME,REFERENCE,PRICE,ID_CAT)" +
                    "Values(?,?,?,?)");
            pst.setString(1,o.getName());
            pst.setString(2,o.getReference());
            pst.setFloat(3,o.getPrice());
            pst.setLong(4,o.getCategory().getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Product o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("delete from PRODUCT where ID=?");
            pst.setLong(1,o.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("update  PRODUCT set NAME=?,REFERENCE=?,PRICE=?,ID_CAT=? where ID=?");
            pst.setString(1,o.getName());
            pst.setString(2,o.getReference());
            pst.setFloat(3,o.getPrice());
            pst.setLong(4,o.getCategory().getId());
            pst.setLong(5,o.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findbyQuery(String query) {
        List<Product> products=new ArrayList<>();
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from PRODUCT where NAME like ? or REFERENCE like ? or PRICE like ?");
            pst.setString(1,"%"+query+"%");
            pst.setString(2,"%"+query+"%");
            pst.setString(3,"%"+query+"%");
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Product p=new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setPrice(rs.getFloat("PRICE"));
                p.setReference(rs.getString("REFERENCE"));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
