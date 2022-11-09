package project.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.board.entity.Board;

public interface BoardJPARepository extends JpaRepository<Board, Long>, BoardCustomRepository, BoardRepository {
}