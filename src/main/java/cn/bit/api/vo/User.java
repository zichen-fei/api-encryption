package cn.bit.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String id;

    private String username;

    private String nickName;

    private String email;

    private String mobile;

    private String password;

    private String title;

    private String status;

    private String createBy;

    private Date createTime;

    private Date lastUpdateTime;

    private String tenant;

    private String origin;

    public User() {
    }

    public User(String id, String username, String nickName, String email, String mobile, String password, String title, String status, String createBy, Date createTime, Date lastUpdateTime, String tenant, String origin) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.title = title;
        this.status = status;
        this.createBy = createBy;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.tenant = tenant;
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant == null ? null : tenant.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public void maskPassword() {
        this.setPassword("N/A");
    }

    public void maskMobile() {
        this.setMobile(mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", id)
                .append("username", username)
                .append("nickName", nickName)
                .append("email", email)
                .append("mobile", mobile)
                .append("password", password)
                .append("title", title)
                .append("status", status)
                .append("createBy", createBy)
                .append("createTime", createTime)
                .append("lastUpdateTime", lastUpdateTime)
                .append("tenant", tenant)
                .append("origin", origin)
                .toString();
    }
}