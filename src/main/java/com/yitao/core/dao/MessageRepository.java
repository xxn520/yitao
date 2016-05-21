package com.yitao.core.dao;

import com.yitao.core.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

/**
 * Created by m2mbob on 16/4/11.
 */
public interface MessageRepository extends HibernateBasedRepository<Message, Long>{

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
    public Page<Message> findByMsgtoId(Long uid, Pageable pageable);

}
