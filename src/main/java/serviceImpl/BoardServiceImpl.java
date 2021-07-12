package serviceImpl;

import domain.Board;
import domain.Comment;
import exception.NotFoundFileException;
import exception.UnAuthorizedException;
import mapper.BoardMapper;
import mapper.CommentMapper;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import service.BoardService;
import service.UserService;
import util.JwtTokenProvider;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    CommentMapper commentMapper;
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

    @Override
    public String increaseHit(int seq) {
        boardMapper.increaseHit(seq);
        return "조회수가 증가되었습니다.";
    }

    @Override
    public String insertComment(int seq, Comment comment) {
        Map<String, Object> payload = userService.getTokenPayload();
        String userId = payload.get("id").toString();
        String nickname = payload.get("nickname").toString();

        comment.setBoard_seq(seq);
        comment.setComment_id(userId);
        comment.setComment_writer(nickname);
        commentMapper.insertComment(comment);
        return "댓글이 등록 되었습니다.";
    }

    @Override
    public String insertReply(Comment comment) {
        Map<String, Object> payload = userService.getTokenPayload();
        String userId = payload.get("id").toString();
        String nickname = payload.get("nickname").toString();

        comment.setComment_id(userId);
        comment.setComment_writer(nickname);
        commentMapper.insertReply(comment);
        return "답글이 등록 되었습니다.";
    }

    @Override
    public List<Comment> getComment(int seq) {
        return commentMapper.getComment(seq);
    }

    @Override
    public String deleteComment(int board_seq, int comment_seq) {
        //TODO 1. board_seq, comment_seq -> map으로 변경
        //TODO 2. mapper 에서 is_deleted = true 로 변경
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("board_seq", board_seq);
        map.put("comment_seq", comment_seq);

        commentMapper.deleteComment(map);
        return "댓글이 삭제되었습니다.";
    }

    @Override
    public List<Comment> getCommentInPage(int seq, int comment_page) {
        //TODO 일단 페이징으로 10개를 보여준다 (부모들만)
        //TODO 1들어왔을때 0-10
        //TODO 2들어왔을때 10-10
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("board_seq", seq);
        comment_page -= 1;
        if(comment_page>0){
            comment_page *= 10;
        }
        map.put("comment_page", comment_page);
        return commentMapper.getCommentInPage(map);
    }
}
