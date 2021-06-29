package controller;

import annotation.Auth;
import domain.Board;
import mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BoardService;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    //게시판 받아오기
    @ResponseBody
    @RequestMapping(value = "/BoardList", method = RequestMethod.GET)
    public ResponseEntity getBoardList() throws Exception {
        return new ResponseEntity(boardService.getBoardList(), HttpStatus.OK);
    }

    //글 수정
    @Auth
    @ResponseBody
    @RequestMapping(value = "/board/{seq}", method = RequestMethod.PUT, produces = "application/json;charset=utf8")
    public String modifyBoard(@RequestBody Board board, @PathVariable int seq) throws Exception {
        if(boardService.modifyBoard(board, seq)==1)
            return "정상적으로 변경 되었습니다.";
        else
            return "오류!!!...";
    }
    //글 삭제
    @Auth
    @ResponseBody
    @RequestMapping(value="/board/{seq}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBoard(@PathVariable int seq){

        return new ResponseEntity(HttpStatus.OK);
    }
    //글 등록
    @Auth
    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ResponseEntity insertBoard(Board board){

        return new ResponseEntity(HttpStatus.OK);
    }
}
