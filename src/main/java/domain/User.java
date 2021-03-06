package domain;

import annotation.ValidationGroups;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {
    private int seq;
    @NotNull(groups = {ValidationGroups.signIn.class})
    private int class_no;
    @NotBlank(message = "이름을 입력하지 않았습니다.", groups = {ValidationGroups.signIn.class})
    private String name;
    @NotBlank(groups = {ValidationGroups.signIn.class})
    private String nickname;
    @NotBlank(groups = {ValidationGroups.signIn.class})
    private String major;
    @NotBlank(groups = {ValidationGroups.signIn.class})
    private String field;
    @NotBlank(groups = {ValidationGroups.signIn.class, ValidationGroups.logIn.class})
    private String id;
    @NotBlank(groups = {ValidationGroups.signIn.class, ValidationGroups.logIn.class})
    private String pw;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getClass_no() {
        return class_no;
    }

    public void setClass_no(int class_no) {
        this.class_no = class_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
