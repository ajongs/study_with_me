package domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class Board {
    private int board_seq;
    @NotNull
    private String board_subject;
    @NotNull
    private String board_content;
    @NotNull
    private String board_writer;
    @NotNull
    private String ins_user_id;
    private Timestamp ins_date;
    private Timestamp upd_date;
    private int hit;

    public int getBoard_seq() {
        return board_seq;
    }

    public void setBoard_seq(int board_seq) {
        this.board_seq = board_seq;
    }

    public String getBoard_subject() {
        return board_subject;
    }

    public void setBoard_subject(String board_subject) {
        this.board_subject = board_subject;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_writer() {
        return board_writer;
    }

    public void setBoard_writer(String board_writer) {
        this.board_writer = board_writer;
    }

    public String getIns_user_id() {
        return ins_user_id;
    }

    public void setIns_user_id(String ins_user_id) {
        this.ins_user_id = ins_user_id;
    }

    public Timestamp getIns_date() {
        return ins_date;
    }

    public void setIns_date(Timestamp ins_date) {
        this.ins_date = ins_date;
    }

    public Timestamp getUpd_date() {
        return upd_date;
    }

    public void setUpd_date(Timestamp upd_date) {
        this.upd_date = upd_date;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }
}
