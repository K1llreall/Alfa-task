package com.alfa.demo.feign.GIF;

import java.util.List;

public class Gif {
    private List<GifObject> data;
    private PaginationObject pagination;
    private MetaObject meta;

    public PaginationObject getPagination() {
        return pagination;
    }

    public void setPagination(PaginationObject pagination) {
        this.pagination = pagination;
    }

    public MetaObject getMeta() {
        return meta;
    }

    public void setMeta(MetaObject meta) {
        this.meta = meta;
    }

    public List<GifObject> getData() {
        return data;
    }

    public void setData(List<GifObject> data) {
        this.data = data;
    }
}
