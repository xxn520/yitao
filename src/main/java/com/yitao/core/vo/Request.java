/**
 * 
 */
package com.yitao.core.vo;

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
	 * @see ServletRequest#getAttribute(String)
	 */
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	/**
	 * @return
	 * @see HttpServletRequest#getAuthType()
	 */
	public String getAuthType() {
		return request.getAuthType();
	}

	/**
	 * @return
	 * @see HttpServletRequest#getCookies()
	 */
	public Cookie[] getCookies() {
		return request.getCookies();
	}

	/**
	 * @return
	 * @see ServletRequest#getAttributeNames()
	 */
	public Enumeration<String> getAttributeNames() {
		return request.getAttributeNames();
	}

	/**
	 * @param name
	 * @return
	 * @see HttpServletRequest#getDateHeader(String)
	 */
	public long getDateHeader(String name) {
		return request.getDateHeader(name);
	}

	/**
	 * @return
	 * @see ServletRequest#getCharacterEncoding()
	 */
	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}

	/**
	 * @param env
	 * @throws UnsupportedEncodingException
	 * @see ServletRequest#setCharacterEncoding(String)
	 */
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		request.setCharacterEncoding(env);
	}

	/**
	 * @param name
	 * @return
	 * @see HttpServletRequest#getHeader(String)
	 */
	public String getHeader(String name) {
		return request.getHeader(name);
	}

	/**
	 * @return
	 * @see ServletRequest#getContentLength()
	 */
	public int getContentLength() {
		return request.getContentLength();
	}

	/**
	 * @return
	 * @see ServletRequest#getContentLengthLong()
	 */
	public long getContentLengthLong() {
		return request.getContentLengthLong();
	}

	/**
	 * @param name
	 * @return
	 * @see HttpServletRequest#getHeaders(String)
	 */
	public Enumeration<String> getHeaders(String name) {
		return request.getHeaders(name);
	}

	/**
	 * @return
	 * @see ServletRequest#getContentType()
	 */
	public String getContentType() {
		return request.getContentType();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see ServletRequest#getInputStream()
	 */
	public ServletInputStream getInputStream() throws IOException {
		return request.getInputStream();
	}

	/**
	 * @return
	 * @see HttpServletRequest#getHeaderNames()
	 */
	public Enumeration<String> getHeaderNames() {
		return request.getHeaderNames();
	}

	/**
	 * @param name
	 * @return
	 * @see ServletRequest#getParameter(String)
	 */
	public String getParameter(String name) {
		return request.getParameter(name);
	}

	/**
	 * @param name
	 * @return
	 * @see HttpServletRequest#getIntHeader(String)
	 */
	public int getIntHeader(String name) {
		return request.getIntHeader(name);
	}

	/**
	 * @return
	 * @see HttpServletRequest#getMethod()
	 */
	public String getMethod() {
		return request.getMethod();
	}

	/**
	 * @return
	 * @see ServletRequest#getParameterNames()
	 */
	public Enumeration<String> getParameterNames() {
		return request.getParameterNames();
	}

	/**
	 * @return
	 * @see HttpServletRequest#getPathInfo()
	 */
	public String getPathInfo() {
		return request.getPathInfo();
	}

	/**
	 * @param name
	 * @return
	 * @see ServletRequest#getParameterValues(String)
	 */
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	/**
	 * @return
	 * @see HttpServletRequest#getPathTranslated()
	 */
	public String getPathTranslated() {
		return request.getPathTranslated();
	}

	/**
	 * @return
	 * @see ServletRequest#getParameterMap()
	 */
	public Map<String, String[]> getParameterMap() {
		return request.getParameterMap();
	}

	/**
	 * @return
	 * @see HttpServletRequest#getContextPath()
	 */
	public String getContextPath() {
		return request.getContextPath();
	}

	/**
	 * @return
	 * @see ServletRequest#getProtocol()
	 */
	public String getProtocol() {
		return request.getProtocol();
	}

	/**
	 * @return
	 * @see ServletRequest#getScheme()
	 */
	public String getScheme() {
		return request.getScheme();
	}

	/**
	 * @return
	 * @see HttpServletRequest#getQueryString()
	 */
	public String getQueryString() {
		return request.getQueryString();
	}

	/**
	 * @return
	 * @see ServletRequest#getServerName()
	 */
	public String getServerName() {
		return request.getServerName();
	}

	/**
	 * @return
	 * @see HttpServletRequest#getRemoteUser()
	 */
	public String getRemoteUser() {
		return request.getRemoteUser();
	}

	/**
	 * @return
	 * @see ServletRequest#getServerPort()
	 */
	public int getServerPort() {
		return request.getServerPort();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see ServletRequest#getReader()
	 */
	public BufferedReader getReader() throws IOException {
		return request.getReader();
	}

	/**
	 * @param role
	 * @return
	 * @see HttpServletRequest#isUserInRole(String)
	 */
	public boolean isUserInRole(String role) {
		return request.isUserInRole(role);
	}

	/**
	 * @return
	 * @see HttpServletRequest#getUserPrincipal()
	 */
	public Principal getUserPrincipal() {
		return request.getUserPrincipal();
	}

	/**
	 * @return
	 * @see ServletRequest#getRemoteAddr()
	 */
	public String getRemoteAddr() {
		return request.getRemoteAddr();
	}

	/**
	 * @return
	 * @see HttpServletRequest#getRequestedSessionId()
	 */
	public String getRequestedSessionId() {
		return request.getRequestedSessionId();
	}

	/**
	 * @return
	 * @see ServletRequest#getRemoteHost()
	 */
	public String getRemoteHost() {
		return request.getRemoteHost();
	}

	/**
	 * @return
	 * @see HttpServletRequest#getRequestURI()
	 */
	public String getRequestURI() {
		return request.getRequestURI();
	}

	/**
	 * @param name
	 * @param o
	 * @see ServletRequest#setAttribute(String,
	 *      Object)
	 */
	public void setAttribute(String name, Object o) {
		request.setAttribute(name, o);
	}

	/**
	 * @return
	 * @see HttpServletRequest#getRequestURL()
	 */
	public StringBuffer getRequestURL() {
		return request.getRequestURL();
	}

	/**
	 * @param name
	 * @see ServletRequest#removeAttribute(String)
	 */
	public void removeAttribute(String name) {
		request.removeAttribute(name);
	}

	/**
	 * @return
	 * @see HttpServletRequest#getServletPath()
	 */
	public String getServletPath() {
		return request.getServletPath();
	}

	/**
	 * @return
	 * @see ServletRequest#getLocale()
	 */
	public Locale getLocale() {
		return request.getLocale();
	}

	/**
	 * @param create
	 * @return
	 * @see HttpServletRequest#getSession(boolean)
	 */
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}

	/**
	 * @return
	 * @see ServletRequest#getLocales()
	 */
	public Enumeration<Locale> getLocales() {
		return request.getLocales();
	}

	/**
	 * @return
	 * @see ServletRequest#isSecure()
	 */
	public boolean isSecure() {
		return request.isSecure();
	}

	/**
	 * @param path
	 * @return
	 * @see ServletRequest#getRequestDispatcher(String)
	 */
	public RequestDispatcher getRequestDispatcher(String path) {
		return request.getRequestDispatcher(path);
	}

	/**
	 * @return
	 * @see HttpServletRequest#getSession()
	 */
	public HttpSession getSession() {
		return request.getSession();
	}

	/**
	 * @return
	 * @see HttpServletRequest#changeSessionId()
	 */
	public String changeSessionId() {
		return request.changeSessionId();
	}

	/**
	 * @return
	 * @see HttpServletRequest#isRequestedSessionIdValid()
	 */
	public boolean isRequestedSessionIdValid() {
		return request.isRequestedSessionIdValid();
	}

	/**
	 * @return
	 * @see HttpServletRequest#isRequestedSessionIdFromCookie()
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return request.isRequestedSessionIdFromCookie();
	}

	/**
	 * @param path
	 * @return
	 * @deprecated
	 * @see ServletRequest#getRealPath(String)
	 */
	public String getRealPath(String path) {
		return request.getRealPath(path);
	}

	/**
	 * @return
	 * @see HttpServletRequest#isRequestedSessionIdFromURL()
	 */
	public boolean isRequestedSessionIdFromURL() {
		return request.isRequestedSessionIdFromURL();
	}

	/**
	 * @return
	 * @see ServletRequest#getRemotePort()
	 */
	public int getRemotePort() {
		return request.getRemotePort();
	}

	/**
	 * @return
	 * @deprecated
	 * @see HttpServletRequest#isRequestedSessionIdFromUrl()
	 */
	public boolean isRequestedSessionIdFromUrl() {
		return request.isRequestedSessionIdFromUrl();
	}

	/**
	 * @return
	 * @see ServletRequest#getLocalName()
	 */
	public String getLocalName() {
		return request.getLocalName();
	}

	/**
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServletRequest#authenticate(HttpServletResponse)
	 */
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		return request.authenticate(response);
	}

	/**
	 * @return
	 * @see ServletRequest#getLocalAddr()
	 */
	public String getLocalAddr() {
		return request.getLocalAddr();
	}

	/**
	 * @return
	 * @see ServletRequest#getLocalPort()
	 */
	public int getLocalPort() {
		return request.getLocalPort();
	}

	/**
	 * @return
	 * @see ServletRequest#getServletContext()
	 */
	public ServletContext getServletContext() {
		return request.getServletContext();
	}

	/**
	 * @return
	 * @throws IllegalStateException
	 * @see ServletRequest#startAsync()
	 */
	public AsyncContext startAsync() throws IllegalStateException {
		return request.startAsync();
	}

	/**
	 * @param username
	 * @param password
	 * @throws ServletException
	 * @see HttpServletRequest#login(String,
	 *      String)
	 */
	public void login(String username, String password) throws ServletException {
		request.login(username, password);
	}

	/**
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 * @throws IllegalStateException
	 * @see ServletRequest#startAsync(ServletRequest,
	 *      ServletResponse)
	 */
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		return request.startAsync(servletRequest, servletResponse);
	}

	/**
	 * @throws ServletException
	 * @see HttpServletRequest#logout()
	 */
	public void logout() throws ServletException {
		request.logout();
	}

	/**
	 * @return
	 * @see ServletRequest#isAsyncStarted()
	 */
	public boolean isAsyncStarted() {
		return request.isAsyncStarted();
	}

	/**
	 * @return
	 * @see ServletRequest#isAsyncSupported()
	 */
	public boolean isAsyncSupported() {
		return request.isAsyncSupported();
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServletRequest#getParts()
	 */
	public Collection<Part> getParts() throws IOException, ServletException {
		return request.getParts();
	}

	/**
	 * @return
	 * @see ServletRequest#getAsyncContext()
	 */
	public AsyncContext getAsyncContext() {
		return request.getAsyncContext();
	}

	/**
	 * @return
	 * @see ServletRequest#getDispatcherType()
	 */
	public DispatcherType getDispatcherType() {
		return request.getDispatcherType();
	}

	/**
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServletRequest#getPart(String)
	 */
	public Part getPart(String name) throws IOException, ServletException {
		return request.getPart(name);
	}

	/**
	 * @param httpUpgradeHandlerClass
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServletRequest#upgrade(Class)
	 */
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
			throws IOException, ServletException {
		return request.upgrade(httpUpgradeHandlerClass);
	}

}
