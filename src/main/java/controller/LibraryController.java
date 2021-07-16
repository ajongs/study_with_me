package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LibraryService;

@Controller
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @ResponseBody
    @RequestMapping(value="/book/{title}", method= RequestMethod.GET)
    public ResponseEntity searchBook(@PathVariable String title){
        return new ResponseEntity(libraryService.searchBook(title), HttpStatus.OK);
    }

}
