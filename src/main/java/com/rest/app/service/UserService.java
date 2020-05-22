package com.rest.app.service;

import com.rest.app.model.Users;

import java.util.List;

public interface UserService {
    List<Users>getAll();
    public Users getById(int id);
    public boolean saveUser(Users users);
    public boolean updateUser(Users users);
    public  boolean deleteUsers(int id);


}
