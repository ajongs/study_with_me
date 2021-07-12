package service;

import domain.Board;
import domain.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    //게시글 목록 조회
    public List<Board> getBoardList() throws Exception;
    //게시글 상세조회
    public Board getBoardDetail(int board_seq) throws Exception;
    //게시글 등록
    public void insertBoard(Board board) throws Exception;
    //게시글 수정
    public void modifyBoard(Board board, int seq) throws Exception;
    //게시글 삭제
    public void deleteBoard(int board_seq) throws Exception;
    //파일 업로드
    public String uploadFile(MultipartFile file) throws IOException;
    //조회수 증가
    public String increaseHit(int seq);
    //댓글 등록
    public String insertComment(int seq, Comment comment);
    //답글 등록
    public String insertReply(Comment comment);
    //댓글 받아오기
    public List<Comment> getComment(int seq);
    //댓글 삭제
    public String deleteComment(int board_seq, int comment_seq);
}
