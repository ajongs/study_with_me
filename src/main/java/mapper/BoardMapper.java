package mapper;

import domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardMapper {
    public List<Board> getBoardList();
    public Board getBoardDetail(int board_seq);
    public void insertBoard(Board board);
    public void updateBoard(Board board);
    public String getUserId(int board_seq);
    public void deleteBoard(int board_seq);
    public String getBoardWriter(String id);
    public void increaseHit(int seq);
}
