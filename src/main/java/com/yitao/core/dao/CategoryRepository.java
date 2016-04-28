package com.yitao.core.dao;

import com.yitao.core.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by m2mbob on 16/4/11.
 */
public interface CategoryRepository extends HibernateBasedRepository<Category, Long>{

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
    Page<Category> findByParentIsNull(Pageable pageable);

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
    Page<Category> findByParentIsNotNull(Pageable pageable);

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
    List<Category> findByParentIsNull();

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
    Page<Category> findByParentId(Long id, Pageable pageable);

}
