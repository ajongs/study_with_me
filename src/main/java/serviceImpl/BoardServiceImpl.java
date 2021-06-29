package serviceImpl;

import domain.Board;
import mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import service.BoardService;
import service.UserService;
import util.JwtTokenProvider;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Override
    public List<Board> getBoardList() throws Exception {
        return boardMapper.getBoardList();
    }

    @Override
    public Board getBoardDetail(int board_seq) throws Exception {
        return null;
    }

    @Override
    public int insertBoard(Board board) throws Exception {
        return 0;
    }

    @Override
    public int modifyBoard(Board board, int seq) throws Exception {

        //token id 값 가져오기
        String token_id = userService.getUserId();
        //System.out.println("token_id : " + token_id);

        String writer_id = boardMapper.getUserId(seq);
        //System.out.println("writer_id : "+writer_id);

        if(token_id.equals(writer_id)){
            board.setBoard_seq(seq);
            boardMapper.updateBoard(board);
            return 1;
        }
        else{
            return 0;
            //예외처리 해야함, token id값 다를경우
        }
    }

    @Override
    public int deleteBoard(int board_seq) throws Exception {
        return 0;
    }
}
