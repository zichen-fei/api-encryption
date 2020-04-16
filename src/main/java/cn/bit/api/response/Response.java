package cn.bit.api.response;

import cn.bit.bdp.common.enums.Status;

public class Response<T> {
    private Integer status = Status.OK.getValue();
    private String errorMsg;
    private T data;
    private boolean error = false;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        if (status > 299) {
            this.error = true;
        }
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        if (errorMsg != null && !"".equals(errorMsg.trim())) {
            error = true;
        }
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }
}
