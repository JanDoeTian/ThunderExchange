package com.gudy.counter.config;

import com.alipay.remoting.exception.CodecException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import commons.bean.CommonMsg;
import commons.order.OrderCmd;
import commons.tcp.TcpDirectSender;
import commons.uuid.GudyUuid;

import javax.annotation.PostConstruct;

import static commons.bean.MsgConstants.COUNTER_NEW_ORDER;
import static commons.bean.MsgConstants.NORMAL;

@Log4j2
@Configuration
public class GatewayConn {

    @Autowired
    private CounterConfig config;

    private TcpDirectSender directSender;

    @PostConstruct
    private void init(){
        directSender = new TcpDirectSender(config.getSendIp(),config.getSendPort(),config.getVertx());
        directSender.startup();
    }

    public void sendOrder(OrderCmd orderCmd){
        byte[] data = null;
        try {
            data = config.getBodyCodec().serialize(orderCmd);
        }catch (Exception e){
            log.error("encode error for ordercmd:{}",orderCmd,e);
            return;
        }

        CommonMsg msg  = new CommonMsg();
        msg.setBodyLength(data.length);
        msg.setChecksum(config.getCs().getChecksum(data));
        msg.setMsgSrc(config.getId());
        msg.setMsgDst(config.getGatewayId());
        msg.setMsgType(COUNTER_NEW_ORDER);
        msg.setStatus(NORMAL );
        msg.setMsgNo(GudyUuid.getInstance().getUUID());
        msg.setBody(data);
        directSender.send(config.getMsgCodec().encodeToBuffer(msg));
    }

}
