/**
 * 
 */
package com.yigou.core.util;

import com.yigou.core.Constants;
import com.yigou.core.vo.UploadParams;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.springframework.util.DigestUtils;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author iDay
 *
 */
public abstract class YigouUtil {

	public static String mvcPath(String servletPath) {
		String path = servletPath.replace(".html", "");
		String replacement = path.startsWith(Constants.ADMIN_PATH) ? Constants.ADMIN_PATH : Constants.ROOT_PATH;
		path = replacement + path.replaceFirst(replacement, "").replace("/", "-").replaceAll("[\\d]+", "detail");
		return path;
	}

	public static void saveToDisk(UploadParams upload, String path, String urlPath) throws IOException {
		path = path.endsWith("/") ? path : path + "/";
		String rawFilename = upload.getBody().getFormDataContentDisposition().getFileName();
		rawFilename = new String(rawFilename.getBytes("iso-8859-1"), "utf-8");
		File file = new File(FileUtils.getTempDirectoryPath() + Calendar.getInstance().getTimeInMillis() + ".tmp");
		upload.getBody().getEntityAs(BodyPartEntity.class).moveTo(file);
		upload.getModel().setSize(FileUtils.sizeOf(file));
		String md5 = DigestUtils.md5DigestAsHex(FileUtils.openInputStream(file));
		String filename = md5 + "."
				+ FilenameUtils.getExtension(upload.getBody().getFormDataContentDisposition().getFileName());
		upload.getModel().setFilePath(path + filename);
		upload.getModel().setUrl(urlPath + filename);
		upload.getModel().setFileName(rawFilename);
		upload.getModel().setContentType(upload.getBody().getMediaType().toString());
		File f = new File(upload.getModel().getFilePath());
		if (!(f.exists() && DigestUtils.md5DigestAsHex(new FileInputStream(f)).equals(md5))) {
			FileUtils.copyFile(file, f);
		}
	}
	
	public static MultivaluedMap<String, String> paramsToMap(String query) {
		if (StringUtils.isEmpty(query)) {
			return null;
		}
		String[] keyValuePairs = query.trim().split("&");
		MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
		for (String keyValuePair : keyValuePairs) {
			String[] keyValue = keyValuePair.split("=");
			map.add(keyValue[0], keyValue[1]);
		}
		return map;
	}
}
