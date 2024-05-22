package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.Boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private Boardservice boardservice;

    @GetMapping("/board/write")
    public String boardwriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardwritepro(Board board, Model model) {
        boardservice.write(board);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardlist(Model model,
        @PageableDefault(page=0, size=15, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
        @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {

        Page<Board> list;

        if (searchKeyword == null) {
            list = boardservice.BoardList(pageable);
        } else {
            list = boardservice.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(startPage + 9, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardview(Model model, @RequestParam(name = "id") Integer id) {
        model.addAttribute("board", boardservice.boardview(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam(name="id") Integer id) {
        boardservice.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardservice.boardview(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) throws Exception {
        Board boardtemp = boardservice.boardview(id);
        boardtemp.setTitle(board.getTitle());
        boardtemp.setContent(board.getContent());
        boardservice.write(boardtemp);
        return "redirect:/board/list";
    }
}
