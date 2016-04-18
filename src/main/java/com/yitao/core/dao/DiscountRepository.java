package com.yitao.core.dao;

import com.yitao.core.model.Discount;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.Date;
import java.util.List;

/**
 * Created by m2mbob on 16/4/11.
 */
public interface DiscountRepository extends HibernateBasedRepository<Discount, Long>{

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
    List<Discount> findByFlagAndEndDateLessThan(boolean flag, Date date);

}
