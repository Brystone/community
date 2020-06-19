package com.community.entity;

/**
 * @author stone
 * @version 1.0
 * @ClassName Page
 * @Description 分页相关
 */
public class Page {

    //当前显示页
    private int current = 1;

    //每页最多显示10行
    private int limit = 10;

    //数据总数
    private int rows;

    //查询路径
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit > 1 && limit < 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 当前页起始行
     */
    public int getOffset() {
        return limit * (current - 1);
    }

    /**
     * 获取总页数
     */
    public int getTotal() {
        if (rows % limit == 0) return rows / limit;
        else return rows / limit + 1;
    }

    /**
     * 获取起始页，例如当前页为3 前后分别显示两页  1 2 3 4 5
     * current - 2
     * 1. 当前页在边界 第一、二页
     * 2. 当前>2
     */
    public int getFrom() {
        int from = current - 2;
        return from <= 0 ? 1 : from;
    }

    /**
     * 获取结束页码
     * 边界: 最后两页
     */
    public int getTo() {
        int end = current + 2;
        int totalPage = getTotal();
        return end > totalPage ? totalPage : end;
    }
}
