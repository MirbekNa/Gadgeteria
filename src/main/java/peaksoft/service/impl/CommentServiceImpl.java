package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Comment;
import peaksoft.repository.CommentRepository;
import peaksoft.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, Comment comment) {
        Optional<Comment> existingComment = commentRepository.findById(commentId);
        if (existingComment.isPresent()) {
            Comment updatedComment = existingComment.get();
            updatedComment.setComment(comment.getComment());
            updatedComment.setDateOfComment(comment.getDateOfComment());
            // Дополнительные поля, если есть
            return commentRepository.save(updatedComment);
        } else {
            return null;
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
