package com.yitao.core.dao;

import com.yitao.core.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;

/**
 * Created by m2mbob on 16/4/11.
 */
public interface ProductRepository extends HibernateBasedRepository<Product, Long>{

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
    @Query("select i from Product i left join i.categories category where category.id=:cid order by i.id desc")
    public Page<Product> findByCategoryId(Pageable pageable, @Param("cid") Long cid);

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
    @Query("select i from Product i where i.id in (select distinct (i.id) from Product i inner join i.categories category where category.parent.id=:cId and i.id != :itemId)")
    public Page<Product> findByCategoryId(Pageable pageable, @Param("itemId") Long itemId, @Param("cId")Long cId);

}
