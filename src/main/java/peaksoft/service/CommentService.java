package peaksoft.service;

import peaksoft.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    Comment updateComment(Long commentId, Comment comment);
    void deleteComment(Long commentId);
    Comment getCommentById(Long commentId);
    List<Comment> getAllComments();
}
