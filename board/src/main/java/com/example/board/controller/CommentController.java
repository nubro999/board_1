package com.example.board.controller;

import com.example.board.entity.Comment;
import com.example.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("comments", commentService.findAll());
        return "comments/list";
    }

    @GetMapping("/new")
    public String newComment(Model model) {
        model.addAttribute("comment", new Comment());
        return "comments/new";
    }

    @PostMapping
    public String create(@ModelAttribute Comment comment) {
        commentService.save(comment);
        return "redirect:/comments";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        commentService.delete(id);
        return "redirect:/comments";
    }
}
