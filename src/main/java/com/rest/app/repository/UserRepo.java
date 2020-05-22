package com.rest.app.repository;

import com.rest.app.model.Users;

import java.util.List;

public interface UserRepo {
   List<Users> getUsers();
   Users findById(int id);
 boolean save(Users users);
 boolean update(Users users );
 boolean deleteById(int id);
}
