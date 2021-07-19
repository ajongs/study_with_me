package serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Library;
import domain.ParsingBookInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.LibraryService;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private HttpClient httpClient;

    @Value("${library.key}")
    private String key;

    @Override
    public Library searchBook(String title) throws IOException, URISyntaxException, ParseException {
        String encodeTitle = URLEncoder.encode(title, "UTF-8");
        String apiUri = "https://www.nl.go.kr/NL/search/openApi/search.do?systemType=%ec%98%a4%ed%94%84%eb%9d%bc%ec%9d%b8%ec%9e%90%eb%a3%8c"+
                "&key="+key +
                "&apiType=json" +
                "&srchTarget=total" +
                "&kwd="+encodeTitle+
                "&category=%eb%8f%84%ec%84%9c"+
                "&pageSize=10" +
                "&pageNum=1";
        URI uri = new URI(apiUri);

        /* 이건 자바에서 제공되는 오래된 커넥션 특징으로는 쿠키조작 불가
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");*/

        //httpClient는 thread safe 하기 때문에 재사용 가능하도록 빈 등록 후 반복해서 생성되는 것을 막는다.
        HttpResponse response = httpClient.execute(new HttpGet(uri));
        HttpEntity entity = response.getEntity();

        String responseStr = EntityUtils.toString(entity, "UTF-8");

        System.out.println(responseStr);

        //TODO objectMapper 이용해서 data 받아오기
        //ObjectMapper om = new ObjectMapper();
        //Library libraryJson = om.readValue(responseStr, Library.class);

        //TODO JSONParser를 이용하여 필요한 data만 파싱하기
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseStr);

        Library libraryJson = new Library();
        List<ParsingBookInfo> bookInfos = new ArrayList<ParsingBookInfo>();

        JSONArray results = (JSONArray) jsonObject.get("result");
        for(int i=0; i<results.size(); i++){
            JSONObject tempOb = (JSONObject) results.get(i);
            ParsingBookInfo bookInfo = new ParsingBookInfo();

            bookInfo.setTitleInfo(tempOb.get("titleInfo").toString());
            bookInfo.setAuthorInfo(tempOb.get("authorInfo").toString());
            bookInfo.setPlaceInfo(tempOb.get("placeInfo").toString());
            bookInfo.setPubInfo(tempOb.get("pubInfo").toString());
            bookInfo.setDetailLink(tempOb.get("detailLink").toString());
            bookInfo.setManageName(tempOb.get("manageName").toString());
            bookInfo.setMediaName(tempOb.get("mediaName").toString());

            bookInfos.add(bookInfo);
        }
        libraryJson.setTotal(Integer.parseInt(jsonObject.get("total").toString()));
        libraryJson.setKwd(jsonObject.get("kwd").toString());
        libraryJson.setCategory(jsonObject.get("category").toString());
        libraryJson.setPageNum(Integer.parseInt(jsonObject.get("pageNum").toString()));
        libraryJson.setPageSize(Integer.parseInt(jsonObject.get("pageSize").toString()));
        libraryJson.setResult(bookInfos);

        //RestTemplet

        return libraryJson;
    }
}
