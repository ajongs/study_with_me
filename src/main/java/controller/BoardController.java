package controller;

import annotation.Auth;
import domain.Board;
import domain.Comment;
import exception.NotFoundFileException;
import mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.BoardService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    //게시판 받아오기
    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ResponseEntity getBoardList() throws Exception {
        return new ResponseEntity(boardService.getBoardList(), HttpStatus.OK);
    }

    //게시물 수정
    @Auth
    @ResponseBody
    @RequestMapping(value = "/board/{seq}", method = RequestMethod.PUT, produces = "application/json;charset=utf8")
    public ResponseEntity modifyBoard(@RequestBody Board board, @PathVariable int seq) throws Exception {
        boardService.modifyBoard(board, seq);
        return new ResponseEntity("수정되었습니다.", HttpStatus.OK);
    }
    //게시물 삭제
    @Auth
    @ResponseBody
    @RequestMapping(value="/board/{seq}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBoard(@PathVariable int seq) throws Exception{
        boardService.deleteBoard(seq);
        return new ResponseEntity("삭제되었습니다.",HttpStatus.OK);
    }
    //게시물 등록
    @Auth
    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ResponseEntity insertBoard(@RequestBody Board board) throws Exception{
        boardService.insertBoard(board);
        return new ResponseEntity("게시물이 등록되었습니다.",HttpStatus.OK);
    }
    //이미지, 파일 업로드
    @ResponseBody
    @RequestMapping(value="/board/image", method = RequestMethod.POST)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity(boardService.uploadFile(file), HttpStatus.OK);
    }
    //이미지, 파일 다운로드

    //조회수 증가 api
    @ResponseBody
    @RequestMapping(value="/board/{seq}/hit", method=RequestMethod.GET)
    public ResponseEntity increaseHit(@PathVariable int seq){
        return new ResponseEntity(boardService.increaseHit(seq), HttpStatus.OK);
    }
    //댓글달기
    @Auth
    @ResponseBody
    @RequestMapping(value = "/board/{seq}/comment", method = RequestMethod.POST)
    public ResponseEntity insertComment(@PathVariable int seq, @RequestBody Comment comment){
        return new ResponseEntity(boardService.insertComment(seq, comment),HttpStatus.OK);
    }
    //대댓글 달기
    @Auth
    @ResponseBody
    @RequestMapping(value = "/board/reply", method = RequestMethod.POST)
    public ResponseEntity insertReply(@RequestBody Comment comment){
        return new ResponseEntity(boardService.insertReply(comment),HttpStatus.OK);
    }
    //댓글 받아오기
    @ResponseBody
    @RequestMapping(value="/board/{seq}/comment", method = RequestMethod.GET)
    public ResponseEntity getComment(@PathVariable int seq){
        return new ResponseEntity(boardService.getComment(seq), HttpStatus.OK);
    }
}
