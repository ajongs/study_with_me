package controller;

import domain.Library;
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

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @ResponseBody
    @RequestMapping(value="/book/{title}", method= RequestMethod.GET)
    public ResponseEntity<Library> searchBook(@PathVariable String title) throws IOException, URISyntaxException {
        return new ResponseEntity(libraryService.searchBook(title), HttpStatus.OK);
    }

}
