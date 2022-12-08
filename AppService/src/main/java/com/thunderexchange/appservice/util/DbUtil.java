package com.thunderexchange.appservice.util;

import jakarta.annotation.PostConstruct;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbUtil {

    private static DbUtil dbUtil = null;

    private DbUtil(){}

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public SqlSessionTemplate getSqlSessionTemplate(){
        return this.sqlSessionTemplate;
    }
    @PostConstruct
    private void init(){
        dbUtil = new DbUtil();
        dbUtil.setSqlSessionTemplate(this.sqlSessionTemplate);
    }
    public static long getId(){

        Long res = dbUtil.getSqlSessionTemplate().selectOne(
                "testMapper.queryBalance"
        );

        if(res == null){
            return -1;
        }else{
            return res;
        }

    }

    public static Account queryAccount(long uid, String password){
        return dbUtil.getSqlSessionTemplate().selectOne(
                "userMapper.queryAccount",
                ImmutableMap.of("UId",uid,"Password",password)
        );
    }

    public static void updateLoginTime(long uid,String nowDate,
                                       String nowTime){
        dbUtil.getSqlSessionTemplate().update(
                "userMapper.updateAccountLoginTime",
                ImmutableMap.of(
                        "UId",uid,
                        "ModifyDate",nowDate,
                        "ModifyTime",nowTime
                )
        );
    }

    public static List<PosiInfo> getPosiList(long uid){

        String suid = Long.toString(uid);
        String posiS = RedisStringCache.get(suid, CacheType.POSI);
        if(StringUtils.isEmpty(posiS)){

            List<PosiInfo> tmp = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryPosi",
                    ImmutableMap.of("UId", uid)
            );
            List<PosiInfo> result =
                    CollectionUtils.isEmpty(tmp) ? Lists.newArrayList()
                            : tmp;

            RedisStringCache.cache(suid,JsonUtil.toJson(result),CacheType.POSI);
            return result;
        }else {
            return JsonUtil.fromJsonArr(posiS,PosiInfo.class);
        }
    }

    public static List<OrderInfo> getOrderList(long uid){
        //Cache query
        String suid = Long.toString(uid);
        String orderS = RedisStringCache.get(suid, CacheType.ORDER);
        if(StringUtils.isEmpty(orderS)){
            //Cache miss, query db
            List<OrderInfo> tmp = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryOrder",
                    ImmutableMap.of("UId", uid)
            );
            List<OrderInfo> result =
                    CollectionUtils.isEmpty(tmp) ? Lists.newArrayList()
                            : tmp;
            //Update Cache
            RedisStringCache.cache(suid,JsonUtil.toJson(result),CacheType.ORDER);
            return result;
        }else {
            //Cache hit
            return JsonUtil.fromJsonArr(orderS,OrderInfo.class);
        }
    }


    public static List<TradeInfo> getTradeList(long uid){

        String suid = Long.toString(uid);
        String tradeS = RedisStringCache.get(suid, CacheType.TRADE);
        if(StringUtils.isEmpty(tradeS)){

            List<TradeInfo> tmp = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryTrade",
                    ImmutableMap.of("UId", uid)
            );
            List<TradeInfo> result =
                    CollectionUtils.isEmpty(tmp) ? Lists.newArrayList()
                            : tmp;

            RedisStringCache.cache(suid,JsonUtil.toJson(result),CacheType.TRADE);
            return result;
        }else {

            return JsonUtil.fromJsonArr(tradeS,TradeInfo.class);
        }
    }

    public static int saveOrder(OrderCmd orderCmd){
        Map<String, Object> param = Maps.newHashMap();
        param.put("UId",orderCmd.uid);
        param.put("Code",orderCmd.code);
        param.put("Direction",orderCmd.direction.getDirection());
        param.put("Type",orderCmd.orderType.getType());
        param.put("Price",orderCmd.price);
        param.put("OCount",orderCmd.volume);
        param.put("TCount",0);
        param.put("Status", OrderStatus.NOT_SET.getCode());

        param.put("Data",TimeformatUtil.yyyyMMdd(orderCmd.timestamp));
        param.put("Time",TimeformatUtil.hhMMss(orderCmd.timestamp));

        int count = dbUtil.getSqlSessionTemplate().insert(
                "orderMapper.saveOrder",param
        );
        //判断是否成功
        if(count > 0){
            return Integer.parseInt(param.get("ID").toString());
        }else {
            return -1;
        }
    }

    public static List<Map<String,Object>> queryAllSotckInfo(){
        return dbUtil.getSqlSessionTemplate()
                .selectList("stockMapper.queryStock");
    }


}
