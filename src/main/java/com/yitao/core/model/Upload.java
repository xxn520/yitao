/**
 * 
 */
package com.yitao.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author iDay
 *
 */
@Entity
@Table(indexes = @Index(unique = true, columnList = "fileName, filePath") )
public class Upload extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1678060031286111811L;

	private String fileName;
	private String url;
	@JsonIgnore
	private String filePath;
	private String contentType;
	private long size;


	/**
	 * 
	 */
	public Upload() {
		super();
	}

	/**
	 * @param id
	 */
	public Upload(Long id) {
		super(id);
	}

	/**
	 * @param filename
	 */
	public Upload(String filename) {
		this.fileName = filename;
	}

	/**
	 * @return the filename
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFileName(String filename) {
		this.fileName = filename;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the realPath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param realPath
	 *            the realPath to set
	 */
	public void setFilePath(String realPath) {
		this.filePath = realPath;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "File [filename=" + fileName + ", url=" + url + ", filePath=" + filePath + ", contentType=" + contentType
				+ ", size=" + size + "]";
	}

}
