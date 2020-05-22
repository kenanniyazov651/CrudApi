package com.rest.app.repository;

import com.rest.app.model.Admin;
import com.rest.app.model.User1;


import java.util.List;

public interface UserRepo1 {

    List<User1> getUsers();
    User1 findById(int id);
    boolean save(User1 users);
    boolean update(User1 users );
    boolean deleteById(int id);
    Admin auth(String username,String password);

}
