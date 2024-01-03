package cn.edu.qfnu.demo.model;

import java.util.List;

public class RequestPageResult<T> {
    /**
     * 数据库中数据总条数
     */
    private Integer totalPage;
    private List<T> data;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}