package com.yitao.core.vo;

import com.yitao.core.model.Message;
import com.yitao.core.model.User;

import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/16.
 */
public class MessageParams extends ModelParams<Message>{

    @FormParam("msgto_id")
    private Long msgtoId;
    @FormParam("msgtype")
    private int msgtype;

    @Override
    public Message getModel() {
        this.model.setMsgto(new User(msgtoId));
        this.model.setMsgtype(Message.Type.values()[msgtype]);
        return super.getModel();
    }
}
