package serviceImpl;

import domain.Board;
import mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.BoardService;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;

    @Override
    public List<Board> getBoardList() throws Exception {
        return boardMapper.getBoardList();
    }

    @Override
    public Board getBoardDetail(int board_seq) throws Exception {
        return boardMapper.getBoardDetail(board_seq);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public int insertBoard(Board board) throws Exception {
        return boardMapper.insertBoard(board);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public int updateBoard(Board board) throws Exception {
        return boardMapper.updateBoard(board);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public int deleteBoard(int board_seq) throws Exception {
        return boardMapper.deleteBoard(board_seq);
    }
}
