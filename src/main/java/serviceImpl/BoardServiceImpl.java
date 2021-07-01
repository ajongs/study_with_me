package serviceImpl;

import domain.Board;
import exception.UnAuthorizedException;
import mapper.BoardMapper;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BoardService;
import service.UserService;
import util.JwtTokenProvider;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly=true)
public class BoardServiceImpl implements BoardService {
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

        //token id 값 가져오기
        String token_id = userService.getUserId();
        //해당 게시판을 올린 id 가져오기
        String writer_id = boardMapper.getUserId(seq);
        //각 id값 비교
        if(token_id.equals(writer_id)){ //같다면
            board.setBoard_seq(seq);  //board 객체에
            boardMapper.updateBoard(board);
            return;
        }
        else{
            throw new UnAuthorizedException();
        }
    }

    @Override
    public void deleteBoard(int seq) throws Exception {
        String token_id = userService.getUserId();
        String writer_id = boardMapper.getUserId(seq);

        if(token_id.equals(writer_id)){
            boardMapper.deleteBoard(seq);
            return;
        }
        else
            throw new UnAuthorizedException();
    }

}
