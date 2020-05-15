package cn.com.k1oud.order.service;

import cn.com.k1oud.order.feign.AccountFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountFeignClient accountFeignClient;

    public void create(String userId, String commodityCode, Integer count) {

        int orderMoney = count * 100;
        jdbcTemplate.update("insert order_tbl(user_id,commodity_code,count,money) values(?,?,?,?)",
                new Object[]{userId, commodityCode, count, orderMoney});

        accountFeignClient.reduce(userId, orderMoney);

    }
}
