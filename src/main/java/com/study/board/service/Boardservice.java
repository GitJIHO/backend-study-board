package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Boardservice {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.save(board);
    }

    public List<Board> BoardList() {

        return boardRepository.findAll();
    }

    //특정 게시글 불러오기
    public Board boardview(Integer id) {
        return boardRepository.findById(id).get();
    }
}
