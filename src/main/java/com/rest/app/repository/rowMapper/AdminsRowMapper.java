package com.rest.app.repository.rowMapper;

import com.rest.app.model.Admin;
import com.rest.app.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminsRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        int id=resultSet.getInt("id");
        String username=resultSet.getString("username");

        String password=resultSet.getString("password");
        Admin u=new Admin(id,username,password);
        System.out.println("u "+u);
        return u;
    }
}
