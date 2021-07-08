package mapper;

import domain.Comment;

import java.util.List;

public interface CommentMapper {
    public void insertComment(Comment comment);
    public void insertReply(Comment comment);
    public List<Comment> getComment(int seq);
}
