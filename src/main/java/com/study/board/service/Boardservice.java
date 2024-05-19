package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;
import java.util.List;
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
}
