package com.yitao.core.jersey;

/**
 * Created by m2mbob on 16/4/25.
 */
public class ApiStatus {

    private String methodName;

    private Integer code;

    private Long postId;


    public ApiStatus() {
        super();
    }

    public ApiStatus(String methodName, Integer code, Long postId) {
        super();
        this.methodName = methodName;
        this.code = code;
        this.postId = postId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }


}
