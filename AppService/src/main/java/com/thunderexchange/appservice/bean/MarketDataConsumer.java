package com.gudy.counter.bean;

import com.gudy.counter.config.CounterConfig;
import com.gudy.counter.util.JsonUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import commons.hq.L1MarketData;

import javax.annotation.PostConstruct;

import static com.gudy.counter.bean.MqttBusConsumer.INNER_MARKET_DATA_CACHE_ADDR;
import static com.gudy.counter.config.WebSocketConfig.L1_MARKET_DATA_PREFIX;

@Log4j2
@Component
public class MarketDataConsumer {

    @Autowired
    private CounterConfig config;

    //<code,最新的五档行情>
    private IntObjectHashMap<L1MarketData> l1Cache = new IntObjectHashMap<>();

    @PostConstruct
    private void init() {
        EventBus eventBus = config.getVertx().eventBus();

        //处理核心发过来的行情
        eventBus.consumer(INNER_MARKET_DATA_CACHE_ADDR)
                .handler(buffer -> {
                    Buffer body = (Buffer) buffer.body();
                    if (body.length() == 0) {
                        return;
                    }

                    L1MarketData[] marketData = null;
                    try {
                        marketData = config.getBodyCodec().deserialize(body.getBytes(), L1MarketData[].class);
                    } catch (Exception e) {
                        log.error(e);
                    }

                    if (ArrayUtils.isEmpty(marketData)) {
                        return;
                    }

                    for (L1MarketData md : marketData) {
                        L1MarketData l1MarketData = l1Cache.get(md.code);
                        if (l1MarketData == null || l1MarketData.timestamp < md.timestamp) {
                            l1Cache.put(md.code, md);
                        } else {
                            log.error("l1MarketData is null or l1MarketData.timestamp < md.timestamp");
                        }
                    }


                });


        eventBus.consumer(L1_MARKET_DATA_PREFIX)
                .handler(h -> {
                    int code = Integer.parseInt(h.headers().get("code"));
                    L1MarketData data = l1Cache.get(code);
                    h.reply(JsonUtil.toJson(data));
                });
    }

}
