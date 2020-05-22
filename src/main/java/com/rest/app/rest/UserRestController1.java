package com.rest.app.rest;

import com.rest.app.model.Admin;
import com.rest.app.model.Product;
import com.rest.app.model.User1;
import com.rest.app.model.Users;
import com.rest.app.service.UserService;
import com.rest.app.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users1")
public class UserRestController1 {
    @Autowired
    private UserService1 service;



    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User1>> getAllCustomers() {

        List<User1> usersList = this.service.getAll();

        if (usersList.isEmpty()) {
            return new ResponseEntity<List<User1>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<User1>>(usersList, HttpStatus.OK);
    }



    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User1> getUser(@PathVariable("id") int Id) {
        if (Id == 0) {
            return new ResponseEntity<User1>(HttpStatus.BAD_REQUEST);
        }

        User1 users = (User1)this.service.getById(Id);

        if (users == null) {
            return new ResponseEntity<User1>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User1>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User1> saveUser(@RequestBody @Valid User1 users) {
        HttpHeaders headers = new HttpHeaders();

        if (users == null) {
            return new ResponseEntity<User1>(HttpStatus.BAD_REQUEST);
        }

        this.service.saveUser(users);
        return new ResponseEntity<User1>(users, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User1> updateUser(@RequestBody  User1 users, @PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();

        if (users == null) {
            return new ResponseEntity<User1>(HttpStatus.BAD_REQUEST);
        }

        users.setId(id);
        System.out.println("useri  "+users.toString());
        System.out.println("Idisi   "+ id);
        this.service.updateUser(users);

        return new ResponseEntity<User1>(users, headers, HttpStatus.OK);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User1> deleteUser(@PathVariable("id") int id) {
        User1 users = this.service.getById(id);

        if (users == null) {
            return new ResponseEntity<User1>(HttpStatus.NOT_FOUND);
        }

        this.service.deleteUsers(id);

        return new ResponseEntity<User1>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Admin> Auth(@RequestBody Admin admin) {
        HttpHeaders headers = new HttpHeaders();
        if(admin.getUsername() == null || admin.getPassword()==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);


        }

        admin=this.service.getAuth(admin.getUsername(),admin.getPassword());


        return new ResponseEntity<Admin>(admin, HttpStatus.OK);
    }






}

