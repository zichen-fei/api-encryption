package cn.bit.api.response;

import cn.bit.api.vo.User;
import cn.bit.bdp.common.enums.Status;
import cn.bit.bdp.common.response.Response;

public class ResponseBuilder {

    public static <T> Response<T> ok(T t) {
        Response<T> responseBody = new Response<>();
        responseBody.setStatus(Status.OK.getValue());
        responseBody.setData(t);
        return responseBody;
    }

    public static <T> Response<T> ok(User user) {
        Response<T> responseBody = new Response<>();
        responseBody.setStatus(Status.OK.getValue());
        return responseBody;
    }

    public static Response build(Status status) {
        Response responseBody = new Response();
        responseBody.setStatus(status.getValue());
        return responseBody;
    }

    public static Response error(Status status, String errorMsg) {
        Response responseBody = new Response();
        responseBody.setStatus(status.getValue());
        responseBody.setErrorMsg(errorMsg);
        return responseBody;
    }

    public static <T> Response error(Status status, String errorMsg, T t) {
        Response responseBody = new Response();
        responseBody.setStatus(status.getValue());
        responseBody.setErrorMsg(errorMsg);
        responseBody.setData(t);
        return responseBody;
    }

    public static Response error(String errorMsg) {
        Response responseBody = new Response();
        responseBody.setStatus(Status.INTERNAL_SERVER_ERROR.getValue());
        responseBody.setErrorMsg(errorMsg);
        return responseBody;
    }

}
