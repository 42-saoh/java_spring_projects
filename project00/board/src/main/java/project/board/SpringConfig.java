package project.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.board.repository.BoardJPARepository;
import project.board.repository.BoardRepository;
import project.board.service.BoardService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final EntityManager em;
    private final DataSource dataSource;

    public SpringConfig(EntityManager em, DataSource dataSource) {
        this.em = em;
        this.dataSource = dataSource;
    }

    @Bean
    public BoardService boardService(BoardJPARepository boardJPARepository) {
        return new BoardService(boardJPARepository);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }

}
