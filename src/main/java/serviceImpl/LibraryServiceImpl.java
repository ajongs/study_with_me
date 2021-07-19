package serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Library;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.LibraryService;

import java.io.IOException;
import java.net.*;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private HttpClient httpClient;

    @Value("${library.key}")
    private String key;

    @Override
    public Library searchBook(String title) throws IOException, URISyntaxException {
        String encodeTitle = URLEncoder.encode(title, "UTF-8");
        String apiUri = "https://www.nl.go.kr/NL/search/openApi/search.do?"+
                "key="+key +
                "&apiType=json" +
                "&srchTarget=total" +
                "&kwd="+encodeTitle+
                "&pageSize=10" +
                "&pageNum=1";
        URI uri = new URI(apiUri);

        /* 이건 자바에서 제공되는 오래된 커넥션
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");*/

        //httpClient는 thread safe 하기 때문에 재사용 가능하도록 빈 등록 후 반복해서 생성되는 것을 막는다.
        HttpResponse response = httpClient.execute(new HttpGet(uri));
        HttpEntity entity = response.getEntity();

        String responseStr = EntityUtils.toString(entity, "UTF-8");

        System.out.println(responseStr);

        ObjectMapper om = new ObjectMapper();
        Library libraryJson = om.readValue(responseStr, Library.class);

        //RestTemplet

        return libraryJson;
    }
}
