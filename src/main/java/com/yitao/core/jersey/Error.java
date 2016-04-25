package com.yitao.core.jersey;

/**
 * Created by m2mbob on 16/4/25.
 */
public class Error {

    private String errorCode;
    private String description;

    /**
     *
     */
    public Error() {
        super();
    }

    /**
     * @param errorCode
     * @param description
     */
    public Error(String errorCode, String description) {
        super();
        this.errorCode = errorCode;
        this.description = description;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
