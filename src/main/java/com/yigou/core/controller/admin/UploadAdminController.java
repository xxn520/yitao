/**
 * 
 */
package com.yigou.core.controller.admin;

import com.yigou.core.Constants;
import com.yigou.core.dao.UploadRepository;
import com.yigou.core.model.Upload;
import com.yigou.core.service.AbstractCrudController;
import com.yigou.core.util.YigouUtil;
import com.yigou.core.vo.UploadParams;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author iDay
 *
 */
@Controller
@Path(Constants.ADMIN_PATH + "upload")
@RolesAllowed("ROLE_ADMIN")
public class UploadAdminController extends AbstractCrudController<UploadRepository, UploadParams, Upload, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see yunpeng.core.service.AbstractWebService#create(java.lang.Object)
	 */
	@Override
	@POST
	@Transactional
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Upload create(@BeanParam UploadParams params) {
		Upload upload = params.getModel();
		try {
			YigouUtil.saveToDisk(params, Constants.SAVE_PATH,
					Constants.FILE_URL_PREFIX);
		} catch (IOException e) {
			throw new WebApplicationException(e);
		}
		Upload u = this.repository.findByFileNameAndFilePath(upload.getFileName(), upload.getFilePath());
		if (u != null) {
			return u;
		}
		return super.create(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see yunpeng.core.service.AbstractWebService#update(yunpeng.core.vo.
	 * ModelParams)
	 */
	@Override
	public Upload update(UploadParams upload) {
		return super.update(upload);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * yunpeng.core.service.AbstractWebService#destroy(java.io.Serializable)
	 */
	@Override
	@DELETE
	@Path("{id:\\d+}")
	public Long destroy(@PathParam("id") Long id) {
		return super.destroy(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see yunpeng.core.service.AbstractWebService#edit(java.io.Serializable)
	 */
	@Override
	public Upload edit(Long id) {
		return super.edit(id);
	}

}
