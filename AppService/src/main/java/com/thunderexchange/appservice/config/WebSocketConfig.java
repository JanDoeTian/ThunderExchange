package com.gudy.counter.config;

import io.vertx.core.Vertx;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Log4j2
@Configuration
public class WebSocketConfig {

    public static final String L1_MARKET_DATA_PREFIX = "l1-market-data";

    public final static String TRADE_NOTIFY_ADDR_PREFIX = "tradechange-";

    public final static String ORDER_NOTIFY_ADDR_PREFIX = "orderchange-";


    @Autowired
    private CounterConfig config;

    @PostConstruct
    private void init() {
        Vertx vertx = config.getVertx();

        //只允许成交 委托的变动通过websocket总线往外发送
        BridgeOptions options = new BridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddress(L1_MARKET_DATA_PREFIX))
                .addOutboundPermitted(new PermittedOptions().setAddressRegex(ORDER_NOTIFY_ADDR_PREFIX + "[0-9]+"  ))
                .addOutboundPermitted(new PermittedOptions().setAddressRegex(TRADE_NOTIFY_ADDR_PREFIX + "[0-9]+"));

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        sockJSHandler.bridge(options, event -> {
            if (event.type() == BridgeEventType.SOCKET_CREATED) {
                log.info("client : {} connected", event.socket().remoteAddress());
            } else if (event.type() == BridgeEventType.SOCKET_CLOSED) {
                log.info("client : {} closed", event.socket().remoteAddress());
            }
            event.complete(true);
        });

        Router router = Router.router(vertx);
        router.route("/eventbus/*").handler(sockJSHandler);
        vertx.createHttpServer().requestHandler(router).listen(config.getPubPort());

    }
}
