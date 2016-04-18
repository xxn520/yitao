package com.yitao.core.task;

import com.yitao.core.dao.DiscountRepository;
import com.yitao.core.model.Discount;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by m2mbob on 16/4/18.
 * 折扣过期定时任务
 */
@Component
public class DiscountOverdueTask {

    @Inject
    private DiscountRepository discountRepository;

    @Scheduled(cron = "59 59 23 * * *")
    @Transactional
    public void excute(){
        Date date = new Date();
        List<Discount> discounts = this.discountRepository.findByFlagAndEndDateLessThan(false, date);
        for (Discount discount: discounts){
            discount.setFlag(true);
        }
        this.discountRepository.save(discounts);
    }

}
