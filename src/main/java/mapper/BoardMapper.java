package mapper;

import domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    public List<Board> getBoardList();
    public Board getBoardDetail(int board_seq);
    public void insertBoard(Board board);
    public void updateBoard(Board board);
    public String getUserId(int board_seq);
    public void deleteBoard(int board_seq);
    public String getBoardWriter(String id);
}
