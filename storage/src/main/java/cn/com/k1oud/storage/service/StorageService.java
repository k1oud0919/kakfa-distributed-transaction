package cn.com.k1oud.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class StorageService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deduct(String commodityCode, int count) {
        jdbcTemplate.update("update storage_tbl set count = count - ? where commodity_code = ?",
                new Object[] {count, commodityCode});
    }
}

