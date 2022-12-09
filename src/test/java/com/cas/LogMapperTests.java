package com.cas;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cas.entity.Course;
import com.cas.entity.Log;
import com.cas.entity.Logm;
import com.cas.entity.User;
import com.cas.mapper.CourseMapper;
import com.cas.mapper.LogMapper;
import com.cas.mapper.LogmMapper;
import com.cas.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/12/8 12:59 下午
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.cas")
public class LogMapperTests {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private LogmMapper logmMapper;


    // ======== 按日期切片 ============
    @Test
    public void addLogDb() {
        Log log = new Log();
        log.setMsg("Java");
        log.setRequestTime(LocalDateTime.now());
        logMapper.insert(log);
    }

    @Test
    public void addLogDbRange() {
        for (int i = 0; i < 3; i++) {
            Log log = new Log();
            log.setMsg("Java");
            log.setRequestTime(LocalDateTime.now().minusDays(i));
            logMapper.insert(log);
        }
    }

    @Test
    public void findLogDb() {
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        wrapper.eq("log_id", 1601059340192595970L);
        // 按时间分，一定要加入分片的字段，年份/月份/天 任选一个维度，不能精确到时间
        wrapper.eq("request_time", LocalDateTime.now().minusDays(1));
        Log user = logMapper.selectOne(wrapper);
        System.out.println(JSONObject.toJSONString(user));
    }


    // ======== 按月切片 ============
    @Test
    public void addLogmDb() {
        Logm log = new Logm();
        log.setMsg("Java");
        log.setRequestTime(LocalDateTime.now());
        logmMapper.insert(log);
    }

    @Test
    public void addLogmDbRange() {
        for (int i = 0; i < 2; i++) {
            Logm log = new Logm();
            log.setMsg("Java");
            log.setRequestTime(LocalDateTime.now().minusMonths(i));
            logmMapper.insert(log);
        }
    }

    @Test
    public void findLogmDb() {
        QueryWrapper<Logm> wrapper = new QueryWrapper<>();
        wrapper.eq("log_id", 1601059340192595970L);
        // 按时间分，一定要加入分片的字段，年份/月份/天 任选一个维度，不能精确到时间
        wrapper.eq("request_time", LocalDateTime.now().minusDays(1));
        Logm user = logmMapper.selectOne(wrapper);
        System.out.println(JSONObject.toJSONString(user));
    }

}
