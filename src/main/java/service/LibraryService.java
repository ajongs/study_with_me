package service;

import domain.Library;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;

public interface LibraryService {
    public Library searchBook(String title) throws IOException, URISyntaxException, ParseException;
}
