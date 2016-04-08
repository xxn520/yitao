/**
 * 
 */
package com.yitao.core.vo;

import com.yitao.core.model.Upload;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * @author iDay
 *
 */
public class UploadParams extends ModelParams<Upload> {

	@FormDataParam("file")
	private FormDataBodyPart body;

	/**
	 * 
	 */
	public UploadParams() {
		super();
	}

	/**
	 * @param model
	 */
	public UploadParams(Upload model) {
		super(model);
	}

	/**
	 * @return the body
	 */
	public FormDataBodyPart getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(FormDataBodyPart body) {
		this.body = body;
	}

}
