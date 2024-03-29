package com.example.twitterjournalbackend.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/comment")
public class CommentsController {
    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping(path = "{userId}")
    public List<Comments> getComments(@PathVariable("userId") String userId){
        return commentsService.getComments(userId);
    }

    @PostMapping()
    public void addNewComment(@RequestBody Comments comment) {
        comment.setDateCreate(LocalDateTime.now());
        comment.setLastUpd(LocalDateTime.now());
        commentsService.addNewComments(comment);
    }

    @DeleteMapping(path = "{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        commentsService.deleteComments(commentId);
    }

    @PutMapping(path = "{commentId}")
    public void updateComment(@PathVariable("commentId") Long commentId,
                              @RequestBody(required = false) String newComment) {
        commentsService.updateComment(commentId, newComment, LocalDateTime.now());
    }
}
