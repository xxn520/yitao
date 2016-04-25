package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.jersey.ApiStatus;
import com.yitao.core.service.AbstractService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by m2mbob on 16/4/25.
 */
@Controller
@Path(Constants.API_PATH + "code")
public class CodeController extends AbstractService{

    @GET
    @Path("/sendCode")
    public ApiStatus sendCode(@QueryParam("mobile") String mobile) {
        //DefaultTaobaoClient client = new DefaultTaobaoClient(Constants.FISH_URL, Constants.FISH_APPKEY, Constants.FISH_SECRET);
        //OpenSmsSendvercodeRequest req=new OpenSmsSendvercodeRequest();
        //req.setSendVerCodeRequest("{\"mobile\":\""+mobile+"\"}");
        //OpenSmsSendvercodeResponse response = client.execute(req);
//        if (response.isSuccess()) {
            return new ApiStatus("发送成功",200,0L);
//        } else {
//            return new ApiStatus(response.getMsg(),Integer.parseInt(response.getErrorCode()),0L);
//        }
    }

    @GET
    @Path("/checkCode")
    public ApiStatus checkCode(
            @QueryParam("mobile")String mobile,
            @QueryParam("ver_code")String ver_code){
//        OpenSmsCheckvercodeRequest req=new OpenSmsCheckvercodeRequest();
//        req.setCheckVerCodeRequest("{\"mobile\":\"" +mobile+ "\",\"ver_code\":\""+ ver_code+"\"}");
//        OpenSmsCheckvercodeResponse response = client.execute(req);
//        if (response.isSuccess()) {
            return new ApiStatus("验证通过",200,0L);
//        } else {
//            return new ApiStatus(response.getMsg(),Integer.parseInt(response.getErrorCode()),0L);
//        }
    }

}
