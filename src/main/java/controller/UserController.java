package controller;

import annotation.Auth;
import annotation.ValidationGroups;
import domain.User;
import enums.ErrorEnum;
import exception.unauthenticateException;
import message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value="/sign-up", method=RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody @Validated(ValidationGroups.signIn.class) User user, BindingResult bindingResult) throws Exception{
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
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ResponseEntity login(@RequestBody @Validated(ValidationGroups.logIn.class) User user) throws Exception{
        return new ResponseEntity(userService.login(user), HttpStatus.OK);
    }

    @Auth
    @ResponseBody
    @RequestMapping(value="/check", method=RequestMethod.POST)
    public String test(){
        return "access possible.";
    }

    @Auth
    @ResponseBody
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity refresh(){
        return new ResponseEntity(userService.refresh(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value="/users" , method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
