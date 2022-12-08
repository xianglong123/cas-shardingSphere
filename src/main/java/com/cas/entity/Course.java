package com.cas.entity;


/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/12/8 12:55 下午
 * @desc
 */
public class Course {
    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }
}
