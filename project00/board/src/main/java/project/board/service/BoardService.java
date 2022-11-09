package project.board.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import project.board.entity.AddBoardForm;
import project.board.entity.Board;
import project.board.repository.BoardRepository;

import java.io.File;
import java.util.UUID;

public class BoardService {
    private final BoardRepository boardRepository;

    @Value("${spring.servlet.multipart.location}")
    private String filepath;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board addBoard(Board newBoard, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String filename = file.getOriginalFilename();
                String fileExtension = filename.substring(filename.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();

                file.transferTo(new File(filepath + uuid + fileExtension));
                newBoard.setFileName(filename);
                newBoard.setFileUuidName(uuid + fileExtension);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return boardRepository.save(newBoard);
    }

    public void deleteBoard(long id) {
        boardRepository.deleteById(id);
    }

    public void updateBoard(Long id, AddBoardForm addBoardForm, MultipartFile file) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        board.setContent(addBoardForm.getContent());
        board.setTitle(addBoardForm.getTitle());
        if (!file.isEmpty()) {
            try {
                String filename = file.getOriginalFilename();
                String fileExtension = filename.substring(filename.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();

                file.transferTo(new File(filepath + uuid + fileExtension));
                File deleteFile = new File(filepath + board.getFileUuidName());
                deleteFile.delete();
                board.setFileName(filename);
                board.setFileUuidName(uuid + fileExtension);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        boardRepository.save(board);
    }

    public Page<Board> getAllBoard(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1, 10);
        return boardRepository.findAll(pageable);
    }

    public Board getBoard(long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }
}
