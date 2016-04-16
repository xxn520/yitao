package com.yitao.core.vo; /**
 *
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

/**
 * @author iDay
 *
 * 提供模板中使用request各项参数。
 * request.attr.attrName		as	request.getAttribute("attrName")
 * request.sesssion.attrName	as	request.getSession().getAttribute("attrName")
 * request.header.attrName		as	request.getHeader("attrName")
 * request.param.attrName		as	request.getParameterValues("attrName")
 */
public class Request {
	private Log logger = LogFactory.getLog(getClass());
	private HttpServletRequest request;
	private Map<String, Object> session;
	private Map<String, Object> attr;
	private Map<String, Object> header;

	/**
	 * @param request
	 */
	public Request(HttpServletRequest request) {
		this.request = request;
		this.attr = new HashMap<>();
		Enumeration<String> attrs = this.request.getAttributeNames();
		while (attrs.hasMoreElements()) {
			String attributeName = attrs.nextElement();
			this.attr.put(attributeName, this.request.getAttribute(attributeName));
		}
		this.session = new HashMap<>();
		try {
			HttpSession session = this.request.getSession(true);
			if (session != null) {
				attrs = session.getAttributeNames();
				while (attrs.hasMoreElements()) {
					String attributeName = attrs.nextElement();
					this.session.put(attributeName, session.getAttribute(attributeName));
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		attrs = this.request.getHeaderNames();
		this.header = new HashMap<>();
		while (attrs.hasMoreElements()) {
			String attributeName = attrs.nextElement();
			this.header.put(attributeName, this.request.getHeader(attributeName));
		}
	}

	public Map<String, String[]> getParam() {
		return this.request.getParameterMap();
	}

	/**
	 * @return the attr
	 */
	public Map<String, Object> getAttr() {
		return attr;
	}

	/**
	 * @param attr the attr to set
	 */
	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getAuthType()
	 */
	public String getAuthType() {
		return request.getAuthType();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getCookies()
	 */
	public Cookie[] getCookies() {
		return request.getCookies();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	public Enumeration<String> getAttributeNames() {
		return request.getAttributeNames();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
	 */
	public long getDateHeader(String name) {
		return request.getDateHeader(name);
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}

	/**
	 * @param env
	 * @throws UnsupportedEncodingException
	 * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
	 */
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		request.setCharacterEncoding(env);
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
	 */
	public String getHeader(String name) {
		return request.getHeader(name);
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getContentLength()
	 */
	public int getContentLength() {
		return request.getContentLength();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getContentLengthLong()
	 */
	public long getContentLengthLong() {
		return request.getContentLengthLong();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
	 */
	public Enumeration<String> getHeaders(String name) {
		return request.getHeaders(name);
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getContentType()
	 */
	public String getContentType() {
		return request.getContentType();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see javax.servlet.ServletRequest#getInputStream()
	 */
	public ServletInputStream getInputStream() throws IOException {
		return request.getInputStream();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
	 */
	public Enumeration<String> getHeaderNames() {
		return request.getHeaderNames();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
	 */
	public String getParameter(String name) {
		return request.getParameter(name);
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
	 */
	public int getIntHeader(String name) {
		return request.getIntHeader(name);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getMethod()
	 */
	public String getMethod() {
		return request.getMethod();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getParameterNames()
	 */
	public Enumeration<String> getParameterNames() {
		return request.getParameterNames();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getPathInfo()
	 */
	public String getPathInfo() {
		return request.getPathInfo();
	}

	/**
	 * @param name
	 * @return
	 * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
	 */
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getPathTranslated()
	 */
	public String getPathTranslated() {
		return request.getPathTranslated();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getParameterMap()
	 */
	public Map<String, String[]> getParameterMap() {
		return request.getParameterMap();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getContextPath()
	 */
	public String getContextPath() {
		return request.getContextPath();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getProtocol()
	 */
	public String getProtocol() {
		return request.getProtocol();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getScheme()
	 */
	public String getScheme() {
		return request.getScheme();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getQueryString()
	 */
	public String getQueryString() {
		return request.getQueryString();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getServerName()
	 */
	public String getServerName() {
		return request.getServerName();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getRemoteUser()
	 */
	public String getRemoteUser() {
		return request.getRemoteUser();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getServerPort()
	 */
	public int getServerPort() {
		return request.getServerPort();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see javax.servlet.ServletRequest#getReader()
	 */
	public BufferedReader getReader() throws IOException {
		return request.getReader();
	}

	/**
	 * @param role
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
	 */
	public boolean isUserInRole(String role) {
		return request.isUserInRole(role);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getUserPrincipal()
	 */
	public Principal getUserPrincipal() {
		return request.getUserPrincipal();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getRemoteAddr()
	 */
	public String getRemoteAddr() {
		return request.getRemoteAddr();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
	 */
	public String getRequestedSessionId() {
		return request.getRequestedSessionId();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getRemoteHost()
	 */
	public String getRemoteHost() {
		return request.getRemoteHost();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getRequestURI()
	 */
	public String getRequestURI() {
		return request.getRequestURI();
	}

	/**
	 * @param name
	 * @param o
	 * @see javax.servlet.ServletRequest#setAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	public void setAttribute(String name, Object o) {
		request.setAttribute(name, o);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getRequestURL()
	 */
	public StringBuffer getRequestURL() {
		return request.getRequestURL();
	}

	/**
	 * @param name
	 * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String name) {
		request.removeAttribute(name);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getServletPath()
	 */
	public String getServletPath() {
		return request.getServletPath();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getLocale()
	 */
	public Locale getLocale() {
		return request.getLocale();
	}

	/**
	 * @param create
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
	 */
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getLocales()
	 */
	public Enumeration<Locale> getLocales() {
		return request.getLocales();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#isSecure()
	 */
	public boolean isSecure() {
		return request.isSecure();
	}

	/**
	 * @param path
	 * @return
	 * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
	 */
	public RequestDispatcher getRequestDispatcher(String path) {
		return request.getRequestDispatcher(path);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#getSession()
	 */
	public HttpSession getRawSession() {
		return request.getSession();
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#changeSessionId()
	 */
	public String changeSessionId() {
		return request.changeSessionId();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
	 */
	public boolean isRequestedSessionIdValid() {
		return request.isRequestedSessionIdValid();
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return request.isRequestedSessionIdFromCookie();
	}

	/**
	 * @param path
	 * @return
	 * @deprecated
	 * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
	 */
	public String getRealPath(String path) {
		return request.getRealPath(path);
	}

	/**
	 * @return
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
	 */
	public boolean isRequestedSessionIdFromURL() {
		return request.isRequestedSessionIdFromURL();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getRemotePort()
	 */
	public int getRemotePort() {
		return request.getRemotePort();
	}

	/**
	 * @return
	 * @deprecated
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
	 */
	public boolean isRequestedSessionIdFromUrl() {
		return request.isRequestedSessionIdFromUrl();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getLocalName()
	 */
	public String getLocalName() {
		return request.getLocalName();
	}

	/**
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.http.HttpServletRequest#authenticate(javax.servlet.http.HttpServletResponse)
	 */
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		return request.authenticate(response);
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getLocalAddr()
	 */
	public String getLocalAddr() {
		return request.getLocalAddr();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getLocalPort()
	 */
	public int getLocalPort() {
		return request.getLocalPort();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getServletContext()
	 */
	public ServletContext getServletContext() {
		return request.getServletContext();
	}

	/**
	 * @return
	 * @throws IllegalStateException
	 * @see javax.servlet.ServletRequest#startAsync()
	 */
	public AsyncContext startAsync() throws IllegalStateException {
		return request.startAsync();
	}

	/**
	 * @param username
	 * @param password
	 * @throws ServletException
	 * @see javax.servlet.http.HttpServletRequest#login(java.lang.String,
	 *      java.lang.String)
	 */
	public void login(String username, String password) throws ServletException {
		request.login(username, password);
	}

	/**
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 * @throws IllegalStateException
	 * @see javax.servlet.ServletRequest#startAsync(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		return request.startAsync(servletRequest, servletResponse);
	}

	/**
	 * @throws ServletException
	 * @see javax.servlet.http.HttpServletRequest#logout()
	 */
	public void logout() throws ServletException {
		request.logout();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#isAsyncStarted()
	 */
	public boolean isAsyncStarted() {
		return request.isAsyncStarted();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#isAsyncSupported()
	 */
	public boolean isAsyncSupported() {
		return request.isAsyncSupported();
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.http.HttpServletRequest#getParts()
	 */
	public Collection<Part> getParts() throws IOException, ServletException {
		return request.getParts();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getAsyncContext()
	 */
	public AsyncContext getAsyncContext() {
		return request.getAsyncContext();
	}

	/**
	 * @return
	 * @see javax.servlet.ServletRequest#getDispatcherType()
	 */
	public DispatcherType getDispatcherType() {
		return request.getDispatcherType();
	}

	/**
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.http.HttpServletRequest#getPart(java.lang.String)
	 */
	public Part getPart(String name) throws IOException, ServletException {
		return request.getPart(name);
	}

	/**
	 * @param httpUpgradeHandlerClass
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.http.HttpServletRequest#upgrade(java.lang.Class)
	 */
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
			throws IOException, ServletException {
		return request.upgrade(httpUpgradeHandlerClass);
	}

}