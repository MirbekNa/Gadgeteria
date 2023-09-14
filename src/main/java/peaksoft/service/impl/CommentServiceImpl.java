package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CommentRequest;
import peaksoft.dto.request.FavoriteRequest;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.CommentResponse;
import peaksoft.dto.response.FavoriteResponse;
import peaksoft.dto.response.ProductResponse;
import peaksoft.entity.Comment;
import peaksoft.entity.Favorite;
import peaksoft.entity.Product;
import peaksoft.repository.CommentRepository;
import peaksoft.repository.FavoriteRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.service.CommentService;
import peaksoft.service.FavoriteService;
import peaksoft.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentRequest, comment);
        Comment savedComment = commentRepository.save(comment);
        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(savedComment, commentResponse);
        return commentResponse;
    }

    @Override
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            BeanUtils.copyProperties(commentRequest, comment);
            Comment updatedComment = commentRepository.save(comment);
            CommentResponse commentResponse = new CommentResponse();
            BeanUtils.copyProperties(updatedComment, commentResponse);
            return commentResponse;
        } else {
            return null;
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentResponse getCommentById(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            CommentResponse commentResponse = new CommentResponse();
            BeanUtils.copyProperties(comment, commentResponse);
            return commentResponse;
        } else {
            return null;
        }
    }

    @Override
    public List<CommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> {
                    CommentResponse commentResponse = new CommentResponse();
                    BeanUtils.copyProperties(comment, commentResponse);
                    return commentResponse;
                })
                .collect(Collectors.toList());
    }
}
