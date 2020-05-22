package com.rest.app.repository.Impl;

import com.rest.app.model.Users;
import com.rest.app.repository.UserRepo;
import com.rest.app.repository.rowMapper.UsersRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository
public class UserRepoImp implements UserRepo {
    JdbcTemplate jdbcTemplate;

   private UsersRowMapper usersRowMapper=new UsersRowMapper();

    @Autowired
    public UserRepoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Users> getUsers() {
        String query="SELECT * FROM users";
        List<Users> usersList=null;
        try {
 usersList =this.jdbcTemplate.query(query, new UsersRowMapper());


        }
        catch (RuntimeException e){
            System.out.println("Hata : "+ e);

        }
        return usersList ;
    }

    @Override
    public Users findById(int id) {


            String query="SELECT * FROM users WHERE id = ? ";
            Users users=null;
            try {

                //users = this.jdbcTemplate.queryForObject(query, new Object[]{id} ,new UsersRowMapper());
                users = (Users) jdbcTemplate.queryForObject(query, new Object[]{id} , usersRowMapper);


            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Hata"+e);
            }
            return users;



    }

    @Override
    public boolean save(Users users) {
        final  String query="INSERT INTO users(name,salary,department) VALUES(?,?,?)";
        try {
            Object[] objects = new Object[]{users.getName(),users.getSalary(),users.getDepartment()};

            this.jdbcTemplate.update(query,objects);
        }
        catch (RuntimeException e){
            System.out.println("Hata"+e);
            return false;

        }
        return true;



    }

    @Override
    public boolean update(final Users users) {
        String query="UPDATE users SET name = ? ,salary = ?,department = ? WHERE id= ? ";
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
                    new Object[]{users.getName(),users.getSalary(),users.getDepartment(),users.getId()});

        }
        catch (RuntimeException e){
            System.out.println("Hata : "+e);
            return false;

        }

        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String query="DELETE FROM users WHERE id = ?";
        try {
            this.jdbcTemplate.update(query,new Object[]{id});

        }
        catch (RuntimeException e){
            System.out.println("Hata : "+ e);
            return false;

        }

        return true;
    }


}
