package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //Bean 에 등록해줌
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public void save(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> list(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board boardDetail(int id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 상세보기 실패 : 아이디 없습니다");
        });

    }

    @Transactional
    public void delete(int id) {
         boardRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 수정 실패 "); // 영속화 완료
        });
     board.setTitle(requestBoard.getTitle());
     board.setContent(requestBoard.getContent());
     //해당 함수 종료시에 트랜잭션이 Service가 종료될 때
        //트랜잭션이 종료 이때 더티체킹 - 자동 업데이트 flush


    }
}
