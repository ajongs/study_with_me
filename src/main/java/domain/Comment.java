package domain;

import java.sql.Timestamp;

public class Comment {
    private int comment_seq;
    private int board_seq;
    private int parent_seq;
    private String comment_content;
    private String comment_writer;
    private String comment_id;
    private Timestamp comment_date;
    private boolean is_deleted;

    public int getComment_seq() {
        return comment_seq;
    }

    public void setComment_seq(int comment_seq) {
        this.comment_seq = comment_seq;
    }

    public int getBoard_seq() {
        return board_seq;
    }

    public void setBoard_seq(int board_seq) {
        this.board_seq = board_seq;
    }

    public int getParent_seq() {
        return parent_seq;
    }

    public void setParent_seq(int parent_seq) {
        this.parent_seq = parent_seq;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_writer() {
        return comment_writer;
    }

    public void setComment_writer(String comment_writer) {
        this.comment_writer = comment_writer;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public Timestamp getComment_date() {
        return comment_date;
    }

    public void setComment_date(Timestamp comment_date) {
        this.comment_date = comment_date;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
