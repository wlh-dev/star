package com.star.wlh.user.entity;


import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;
@TableName(value = "shuangseqiu")

public class ShuangSeQiuEntity {
    private String issueNumber;
    private String drawDate;
    private String drawNumberRed;
    private String drawNumberBlue;

    public ShuangSeQiuEntity() {
    }

    public ShuangSeQiuEntity(String issueNumber, String drawDate, String drawNumberRed, String drawNumberBlue) {
        this.issueNumber = issueNumber;
        this.drawDate = drawDate;
        this.drawNumberRed = drawNumberRed;
        this.drawNumberBlue = drawNumberBlue;
    }

    @Override
    public String toString() {
        return "ShuangSeQiuEntity{" +
                "issueNumber='" + issueNumber + '\'' +
                ", drawDate='" + drawDate + '\'' +
                ", drawNumberRed='" + drawNumberRed + '\'' +
                ", drawNumberBlue='" + drawNumberBlue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShuangSeQiuEntity that = (ShuangSeQiuEntity) o;
        return Objects.equals(issueNumber, that.issueNumber) && Objects.equals(drawDate, that.drawDate) && Objects.equals(drawNumberRed, that.drawNumberRed) && Objects.equals(drawNumberBlue, that.drawNumberBlue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueNumber, drawDate, drawNumberRed, drawNumberBlue);
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public String getDrawNumberRed() {
        return drawNumberRed;
    }

    public void setDrawNumberRed(String drawNumberRed) {
        this.drawNumberRed = drawNumberRed;
    }

    public String getDrawNumberBlue() {
        return drawNumberBlue;
    }

    public void setDrawNumberBlue(String drawNumberBlue) {
        this.drawNumberBlue = drawNumberBlue;
    }
}
