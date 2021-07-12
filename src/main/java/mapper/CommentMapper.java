package mapper;

import domain.Comment;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    public void insertComment(Comment comment);
    public void insertReply(Comment comment);
    public List<Comment> getComment(int seq);

    public void deleteComment(Map<String, Integer> map);
    public void deleteReply(Map<String, Integer> map);
    public List<Comment> getCommentInPage(Map<String, Integer> map);
}
