package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.Boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private Boardservice boardservice;

    @GetMapping("/board/write")
    public String boardwriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardwritepro(Board board) {

        boardservice.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardlist(Model model) {
        model.addAttribute("list", boardservice.BoardList());
        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardview(Model model, @RequestParam(name = "id") Integer id){
        model.addAttribute("board", boardservice.boardview(id));
        return "boardview";
    }
}
