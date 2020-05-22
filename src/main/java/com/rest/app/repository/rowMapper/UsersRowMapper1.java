package com.rest.app.repository.rowMapper;

import com.rest.app.model.User1;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper1 implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        int id=resultSet.getInt("id");
        String name=resultSet.getString("name");
        double salary=resultSet.getDouble("salary");
        String department=resultSet.getString("department");
        String image=resultSet.getString("image");


        User1 u=new User1(id,name,salary,department,image);
        System.out.println("u "+u);
        return u;
    }
}
