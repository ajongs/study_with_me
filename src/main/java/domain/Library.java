package domain;

import java.util.List;

public class Library {
    private int total;
    private String kwd;
    private int pageNum;
    private int pageSize;
    private String category;
    private String sort;
    private List<BookInfo> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getKwd() {
        return kwd;
    }

    public void setKwd(String kwd) {
        this.kwd = kwd;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<BookInfo> getResult() {
        return result;
    }

    public void setResult(List<BookInfo> result) {
        this.result = result;
    }
}
