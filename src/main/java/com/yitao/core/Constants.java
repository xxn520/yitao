/**
 * 
 */
package com.yitao.core;

/**
 * @author iDay
 *
 */
public abstract class Constants {

	public static final String ADMIN_PATH = "/admin/";
	public static final String ROOT_PATH = "/";
	public static final String API_PATH = "/api/";
	public static final String SAVE_PATH = "/usr/local/tomcat8/webapps/yitao/upload";
	public static final String FILE_URL_PREFIX = "/upload/";

	public static final String ERROR_MESSAGE_KEY = "errorMessage";
	public static final String SUCCESS_MESSAGE_KEY = "successMessage";

    /**
     * 阿里大鱼
     */
    public static final String FISH_APPKEY = "23353575";
    public static final String FISH_SECRET = "8eb86c896cffb167e822cc1f2d241a0e";
    public static final String FISH_URL = "http://gw.api.taobao.com/router/rest";

    /**
	 * 模板引擎中引入Request对象的key
	 */
	public static final String MVC_FILTER_REQUEST_KEY = "request";
	/**
	 * 模板引擎中引入SystemVariable的key
	 */
	public static final String MVC_FILTER_APP_KEY = "app";
	/**
	 * 模板引擎中引入model的key
	 */
	public static final String MVC_FILTER_MODEL_KEY = "model";
	/**
	 * 模板引擎中引入当前用户对象的key
	 */
	public static final String MVC_FILTER_CURRENT_USER_KEY = "currentUser";
	/**
	 * 模板引擎中location的key
	 */
	public static final String MVC_FILTER_LOCATION = "location";
	/**
	 * 重定向参数
	 */
	public static final String REQUEST_PARAM_REDIRECT_KEY = "redirect";
	
	
}
