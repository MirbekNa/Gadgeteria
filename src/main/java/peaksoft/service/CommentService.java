package peaksoft.service;

import peaksoft.dto.request.CommentRequest;
import peaksoft.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CommentRequest commentRequest);
    CommentResponse updateComment(Long commentId, CommentRequest commentRequest);
    void deleteComment(Long commentId);
    CommentResponse getCommentById(Long commentId);
    List<CommentResponse> getAllComments();
}
