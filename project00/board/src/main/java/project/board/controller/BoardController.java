package project.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.board.entity.AddBoardForm;
import project.board.entity.Board;
import project.board.service.BoardService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    @Value("${spring.servlet.multipart.location}")
    private String filepath;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String getAllBoard(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("boardList", boardService.getAllBoard(pageable));
        return "board/boardList";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "board/board";
    }

    @PutMapping("/{id}")
    public String updateBoard(@PathVariable Long id,
                              @Validated @ModelAttribute("board") AddBoardForm form,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes,
                              @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "redirect:/boards/modify/" + id;
        }
        boardService.updateBoard(id, form, file);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/boards/" + id;
    }

    @GetMapping("/modify/{id}")
    public String modifyBoard(@PathVariable Long id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board/modifyBoard";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }

    @GetMapping("/add")
    public String addBoard(Model model) {
        model.addAttribute("board", new Board());
        return "board/addBoard";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("board") AddBoardForm form,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes,
                      @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "board/addBoard";
        }
        Board newBoard = boardService.addBoard(Board.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .build(), file);
        redirectAttributes.addAttribute("boardId", newBoard.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/download/{filename}")
    public void fileDownload(HttpServletResponse response, HttpServletRequest request, @PathVariable String filename) {
        File file = new File(filepath + filename);
        String downName = null;
        String browser = request.getHeader("User-Agent");
        try {
            if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
                downName = URLEncoder.encode(filename, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
            } else {
                downName = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition","attachment;filename=\"" + downName+"\"");
        response.setContentType("application/octer-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ServletOutputStream servletOutputStream = response.getOutputStream()) {
            byte[] buf  = new byte[1024];
            int data = 0;

            while ((data = (fileInputStream.read(buf, 0, buf.length))) != -1){
                servletOutputStream.write(buf, 0, data);
            }
            servletOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
