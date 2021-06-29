package serviceImpl;

import domain.Board;
import exception.UnAuthorizedException;
import mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BoardService;
import service.UserService;
import util.JwtTokenProvider;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class BoardServiceImpl implements BoardService {
    @Autowired
    JwtTokenProvider jwt;

    @Autowired
    UserService userService;
    @Autowired
    BoardMapper boardMapper;
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
        boardMapper.insertBoard(board);
    }

    @Override
    public void modifyBoard(Board board, int seq) throws Exception {

        //token id 값 가져오기
        String token_id = userService.getUserId();

        String writer_id = boardMapper.getUserId(seq);

        if(token_id.equals(writer_id)){
            board.setBoard_seq(seq);
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
