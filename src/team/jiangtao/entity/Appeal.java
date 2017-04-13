package team.jiangtao.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by tose on 2017/4/13.
 */
@Entity
@IdClass(AppealPK.class)
public class Appeal {
    private String dpmId;
    private String crsId;
    private String tchId;
    private String stuId;
    private Date date;
    private String content;
    private String response;
    private byte status;

    @Id
    @Column(name = "dpm_id", nullable = false, length = 8)
    public String getDpmId() {
        return dpmId;
    }

    public void setDpmId(String dpmId) {
        this.dpmId = dpmId;
    }

    @Id
    @Column(name = "crs_id", nullable = false, length = 8)
    public String getCrsId() {
        return crsId;
    }

    public void setCrsId(String crsId) {
        this.crsId = crsId;
    }

    @Id
    @Column(name = "tch_id", nullable = false, length = 8)
    public String getTchId() {
        return tchId;
    }

    public void setTchId(String tchId) {
        this.tchId = tchId;
    }

    @Id
    @Column(name = "stu_id", nullable = false, length = 8)
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Id
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 128)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "response", nullable = true, length = 128)
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appeal appeal = (Appeal) o;

        if (status != appeal.status) return false;
        if (dpmId != null ? !dpmId.equals(appeal.dpmId) : appeal.dpmId != null) return false;
        if (crsId != null ? !crsId.equals(appeal.crsId) : appeal.crsId != null) return false;
        if (tchId != null ? !tchId.equals(appeal.tchId) : appeal.tchId != null) return false;
        if (stuId != null ? !stuId.equals(appeal.stuId) : appeal.stuId != null) return false;
        if (date != null ? !date.equals(appeal.date) : appeal.date != null) return false;
        if (content != null ? !content.equals(appeal.content) : appeal.content != null) return false;
        if (response != null ? !response.equals(appeal.response) : appeal.response != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dpmId != null ? dpmId.hashCode() : 0;
        result = 31 * result + (crsId != null ? crsId.hashCode() : 0);
        result = 31 * result + (tchId != null ? tchId.hashCode() : 0);
        result = 31 * result + (stuId != null ? stuId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (int) status;
        return result;
    }
}