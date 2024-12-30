package com.example.lms.payload.response;

import java.util.List;

public class PaginatedResponse<T> {
    private List<T> data;
    private int pageNo;
    private int pageSize;
    private long totalResults;

    public PaginatedResponse() {
    }

    public PaginatedResponse(List<T> data, int pageNo, int pageSize, long totalResults) {
        this.data = data;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalResults = totalResults;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }
}
