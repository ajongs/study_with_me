package controller;

import domain.User;
import enums.ErrorEnum;
import message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value="/sign-up", method=RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Message message = new Message();
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            message.setErrorCode(ErrorEnum.VALIDATION_FAIL);
            message.setData(bindingResult.getFieldError().getDefaultMessage());

            return new ResponseEntity(message,/* httpHeaders, */ HttpStatus.BAD_REQUEST);
        }
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
