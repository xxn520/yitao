/**
 * 
 */
package com.yitao.core.dao;

import com.yitao.core.model.Upload;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

/**
 * @author iDay
 *
 */
public interface UploadRepository extends HibernateBasedRepository<Upload, Long> {
	
	@QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
	Upload findByFileNameAndFilePath(String fileName, String filePath);
}
