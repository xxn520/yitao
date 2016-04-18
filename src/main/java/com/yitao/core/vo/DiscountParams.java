package com.yitao.core.vo;

import com.yitao.core.model.Discount;
import org.apache.commons.lang3.time.DateUtils;

import javax.ws.rs.FormParam;
import java.text.ParseException;

/**
 * Created by m2mbob on 16/4/18.
 */
public class DiscountParams extends ModelParams<Discount>{

    @FormParam("startDate")
    private String startDate;
    @FormParam("endDate")
    private String endDate;

    @Override
    public Discount getModel() {
        try {
            this.model.setStartDate(DateUtils.parseDate(startDate, new String[]{"yyyy年MM月dd日"}));
            this.model.setEndDate(DateUtils.parseDate(endDate, new String[]{"yyyy年MM月dd日"}));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return super.getModel();
    }
}
