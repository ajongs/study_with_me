package serviceImpl;

import domain.Board;
import exception.NotFoundFileException;
import exception.UnAuthorizedException;
import mapper.BoardMapper;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.BoardService;
import service.UserService;
import util.JwtTokenProvider;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly=true)
public class BoardServiceImpl implements BoardService {
    public static String prefixPath = "/upload/";
    @Autowired
    JwtTokenProvider jwt;
    @Autowired
    UserService userService;
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public List<Board> getBoardList() throws Exception {
        return boardMapper.getBoardList();
    }

    @Override
    public Board getBoardDetail(int board_seq) throws Exception {
        return null;
    }

    @Override
    public void insertBoard(Board board) throws Exception {
        Map<String, Object> payload = userService.getTokenPayload();
        String userId = payload.get("id").toString();
        String BoardWriter = payload.get("nickname").toString();

        board.setIns_user_id(userId);
        board.setBoard_writer(BoardWriter);
        boardMapper.insertBoard(board);
    }

    @Override
    public void modifyBoard(Board board, int seq) throws Exception {

        if(isSameId(seq)) { //같다면
            board.setBoard_seq(seq);  //board 객체에 seq 값 넣어주기
            boardMapper.updateBoard(board); //board 에 id나 nickname 값 넣어줄 필요없음(content 랑 제목만 변경)
        }
    }

    @Override
    public void deleteBoard(int seq) throws Exception {
        if(isSameId(seq))
            boardMapper.deleteBoard(seq);
    }

    private boolean isSameId(int seq){
        //token id 값 가져오기
        String token_id = userService.getUserId();
        //해당 게시판을 올린 id 가져오기
        String writer_id = boardMapper.getUserId(seq);

        if(token_id.equals(writer_id)){
            return true;
        }
        else
            throw new UnAuthorizedException();
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        StringBuilder sb = new StringBuilder();
        Date date = new Date();
        if(file.isEmpty()){
            //TODO 파일 비었음 예외처리(Exception 핸들러 처리해야함)
            throw new NotFoundFileException();
        }
        else{
            sb.append(date.getTime());
            sb.append(file.getOriginalFilename());
        }
        String url = prefixPath+sb.toString();
        if(!file.isEmpty()){
            File dest = new File(url);
            file.transferTo(dest);
        }
        return url;
    }
}
