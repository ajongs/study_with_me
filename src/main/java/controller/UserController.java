package controller;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value="/sign-up", method=RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody User user){
        userService.signUp(user);
        return new ResponseEntity(HttpStatus.OK);
    }
    @ResponseBody
    @RequestMapping(value="/users" , method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @ResponseBody
    @RequestMapping(value="/users/{class_no}" , method = RequestMethod.GET)
    public User getUser(@PathVariable int class_no){
        return userService.getUser(class_no);
    }
    @ResponseBody
    @RequestMapping(value="/users" , method = RequestMethod.POST)
    public void insert(@RequestBody User user){
        userService.insert(user);
    }
    @ResponseBody
    @RequestMapping(value="/users" , method = RequestMethod.PUT)
    public void update(@RequestBody User user){
        userService.update(user);
    }
    @ResponseBody
    @RequestMapping(value="/users" , method = RequestMethod.DELETE)
    public void delete(@RequestBody User user){
        userService.delete(user);
    }
}
