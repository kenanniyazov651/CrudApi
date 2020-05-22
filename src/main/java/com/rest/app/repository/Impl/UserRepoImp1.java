package com.rest.app.repository.Impl;

import com.rest.app.model.Admin;
import com.rest.app.model.User1;
import com.rest.app.model.Users;
import com.rest.app.repository.UserRepo;
import com.rest.app.repository.UserRepo1;
import com.rest.app.repository.rowMapper.AdminsRowMapper;
import com.rest.app.repository.rowMapper.UsersRowMapper;
import com.rest.app.repository.rowMapper.UsersRowMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository
public class UserRepoImp1 implements UserRepo1 {
    JdbcTemplate jdbcTemplate;

    private UsersRowMapper1 usersRowMapper=new UsersRowMapper1();
    private AdminsRowMapper adminsRowMapper =new AdminsRowMapper();

//    public UserRepoImp1( ) {
//
//    }

    @Autowired
    public UserRepoImp1(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<User1> getUsers() {
        String query="SELECT * FROM users1";
        List<User1> usersList=null;
        try {
            usersList =this.jdbcTemplate.query(query, new UsersRowMapper1());


        }
        catch (RuntimeException e){
            System.out.println("Hata : "+ e);

        }
        return usersList ;
    }

    @Override
    public User1 findById(int id) {


        String query="SELECT * FROM users1 WHERE id = ? ";
        User1 users=null;
        try {

            //users = this.jdbcTemplate.queryForObject(query, new Object[]{id} ,new UsersRowMapper());
            users = (User1) jdbcTemplate.queryForObject(query, new Object[]{id} , new UsersRowMapper1());


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Hata"+e);
        }
        return users;



    }

    @Override
    public boolean save(User1 users) {
        final  String query="INSERT INTO users1(name,salary,department,image) VALUES(?,?,?,?)";
        try {
            Object[] objects = new Object[]{users.getName(),users.getSalary(),users.getDepartment(),users.getImage()};

            this.jdbcTemplate.update(query,objects);
        }
        catch (RuntimeException e){
            System.out.println("Hata"+e);
            return false;

        }
        return true;



    }

    @Override
    public boolean update(final User1 users) {
        String query="UPDATE users1 SET name = ? ,salary = ?,department = ? ,image = ? WHERE id= ? ";
        try {
//            this.jdbcTemplate.update(query, new PreparedStatementSetter() {
//                @Override
//                public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                    preparedStatement.setString(1,users.getName());
//                    preparedStatement.setInt(2, (int) users.getSalary());
//                    preparedStatement.setString(3, users.getDepartment());
//
//                }
//            });
            System.out.println("Xeta"+users.getId());
            this.jdbcTemplate.update(query,
                    new Object[]{users.getName(),users.getSalary(),users.getDepartment(),users.getImage(),users.getId()});

        }
        catch (RuntimeException e){
            System.out.println("Hata : "+e);
            return false;

        }

        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String query="DELETE FROM users1 WHERE id = ?";
        try {
            this.jdbcTemplate.update(query,new Object[]{id});

        }
        catch (RuntimeException e){
            System.out.println("Hata : "+ e);
            return false;

        }

        return true;
    }

    @Override
    public Admin auth(String username ,String password ) {
        String query="SELECT * FROM admins WHERE username = ? and password=?";
        Admin admin1=null;
        try {

            //users = this.jdbcTemplate.queryForObject(query, new Object[]{id} ,new UsersRowMapper());
            admin1 = (Admin) jdbcTemplate.queryForObject(query, new Object[]{username,password} , adminsRowMapper);
            System.out.println(" admin1 :ici   "+  admin1 );

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Hata"+e);
        }
        return admin1;





    }


}
