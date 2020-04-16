package cn.bit.api.response;

import cn.bit.bdp.common.enums.Status;
import cn.bit.bdp.common.exception.ViewException;
import cn.bit.bdp.common.response.Response;

public class ResponseSyntax {
    public static <T> T syntax(Response<T> responseBody) throws ViewException {
        return doSyntax(responseBody);
    }

    private static <T> T doSyntax(Response<T> responseBody) throws ViewException {
        if (responseBody != null) {
            if (!responseBody.isError()) {
                return responseBody.getData();
            }
            String errorMsg = responseBody.getErrorMsg();
            Status status = Status.valueOf(responseBody.getStatus());
            throw new ViewException(errorMsg, status);
        }
        throw new ViewException();
    }
}
