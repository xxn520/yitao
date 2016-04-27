package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.UploadRepository;
import com.yitao.core.model.Upload;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.util.YitaoUtil;
import com.yitao.core.vo.UploadParams;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author iDay
 *
 */
@Controller
@Path(Constants.API_PATH + "upload")
public class UploadController extends AbstractCrudController<UploadRepository, UploadParams, Upload, Long>  {

    @POST
    @Transactional
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Upload create(@BeanParam UploadParams params) {
        Upload upload = params.getModel();
        try {
            YitaoUtil.saveToDisk(params, Constants.SAVE_PATH,
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

}
