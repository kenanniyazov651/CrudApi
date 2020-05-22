package com.rest.app.repository.rowMapper;

import com.rest.app.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        int id=resultSet.getInt("id");
        String name=resultSet.getString("name");
        double salary=resultSet.getDouble("salary");
        String department=resultSet.getString("department");
        Users u=new Users(id,name,salary,department);
        System.out.println("u "+u);
        return u;
    }
}
