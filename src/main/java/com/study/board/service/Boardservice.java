package com.study.board.service;

import com.study.board.Repository.BoardRepository;
import com.study.board.entity.Board;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Boardservice {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board){

//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//
//        UUID uuid = UUID.randomUUID();

//        String fileName = uuid + "_" + file.getOriginalFilename();

//        File saveFile = new File(projectPath, fileName);

//        file.transferTo(saveFile);
//
//        board.setFilename(fileName);

        boardRepository.save(board);
    }

    public Page<Board> BoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    //특정 게시글 불러오기
    public Board boardview(Integer id) {
        return boardRepository.findById(id).get();
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }
}
