package ma.enset.examjavafx.dao;

import ma.enset.examjavafx.dao.entities.Category;
import ma.enset.examjavafx.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImp implements CategoryDao{
    @Override
    public Category find(long id) {
        Category c=new Category();
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from CATEGORY where ID=?");
            pst.setLong(1,id);
            ResultSet rs=pst.executeQuery();

            if(rs.next()){
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories=new ArrayList<>();

        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from CATEGORY");
            ResultSet rs=pst.executeQuery();

            while (rs.next()){
                Category c=new Category();
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));
                categories.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public void save(Category o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("insert into CATEGORY NAME=?" );
            pst.setString(1,o.getName());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Category o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("delete from CATEGORY where ID=?");
            pst.setLong(1,o.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Category o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("update CATEGORY set NAME=? where ID=?");
            pst.setString(1,o.getName());
            pst.setLong(2,o.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
