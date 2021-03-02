package controller;

import domain.Board;
import mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardMapper boardMapper;

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public List<Board> getBoardList(){
        System.out.printf("%d", 5);
        return boardMapper.getBoardList();
    }
    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public int insertBoard(@RequestBody Board board){
        return boardMapper.insertBoard(board);
    }
    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public int updateBoard(@RequestBody Board board){
        return boardMapper.updateBoard(board);
    }
    @ResponseBody
    @RequestMapping(value = "/board/{board}", method = RequestMethod.DELETE)
    public int deleteBoard(@PathVariable int board){
        return boardMapper.deleteBoard(board);
    }
}
