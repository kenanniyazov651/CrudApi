package com.rest.app.repository.Impl;

import com.rest.app.model.Product;
import com.rest.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository

public class ProductRepositoryImpl implements ProductRepository {



    JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public boolean createProductTable() {
        String query="CREATE TABLE product(productId INTEGER NOT NULL, name VARCHAR(20),price INTEGER ,avaible INTEGER , addDate DATE ,PRIMARY KEY(productId))";
       try {
           this.jdbcTemplate.execute(query);
       }
       catch (RuntimeException e){
           System.out.println("Hata"+e);
           return false;

       }
       return true;


    }
    public boolean save(Product product) {

        final  String query="INSERT INTO product(productId,name,price,avaible,addDate) VALUES(?,?,?,?,?)";
        try {
            Object[] objects = new Object[]{product.getProductId(),product.getName(),product.getPrice(),product.getAvaible(),product.getAddDate()};

            this.jdbcTemplate.update(query,objects);
        }
        catch (RuntimeException e){
            System.out.println("Hata"+e);
            return false;

        }
        return true;

}

    public boolean saveBatch(final List<Product> productList) {
        final String query="INSERT INTO product(productId,name,price,avaible,addDate) VALUES(?,?,?,?,?)";
        try {

            this.jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    Product product= productList.get(i);
                    preparedStatement.setInt(1,product.getProductId());
                    preparedStatement.setString(2,product.getName());
                  preparedStatement.setDouble(3,product.getPrice());
                  preparedStatement.setInt(4,product.getAvaible());
                  preparedStatement.setTimestamp(5, Timestamp.from(product.getAddDate().toInstant()));


                }

                public int getBatchSize() {
                    return productList.size();
                }
            });
        }
        catch (RuntimeException e){
            System.out.println("Hata"+e);
            return false;

        }
        return true;
    }

    @Override
    public Product findById(final int id) {
         String sorgu="SELECT * FROM product WHERE productId = ? ";
         Product product=null;
         try {
        product = this.jdbcTemplate.queryForObject(sorgu, new Object[]{id} ,new RowMapper<Product>() {

                 @Override
                 public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                     int productId=resultSet.getInt(1);
                     String name=resultSet.getString("name");
                     double price=resultSet.getDouble("price");
                     int avaible = resultSet.getInt("avaible");
                     Date addDate=resultSet.getDate("addDate");

                     return new Product(productId,name,price,avaible,addDate);
                 }
             });


         }
         catch (RuntimeException e){
             System.out.println("Hata"+e);
         }
        return product;
    }

    @Override
    public List<Product> findProducts() {
        String sorgu="SELECT *FROM product";
        try {
            this.jdbcTemplate.query(sorgu, new RowMapper<Product>() {


                @Override
                public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                    return null;
                }
            });

        }
        catch (RuntimeException e){

        }
        return null;
    }
}
