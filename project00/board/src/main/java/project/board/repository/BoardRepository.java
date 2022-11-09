package project.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.board.entity.Board;

import java.util.Optional;

public interface BoardRepository  {
    Board save(Board board);
    void deleteById(long id);
    Page<Board> findAll(Pageable pageable);
    Optional<Board> findById(long id);
}