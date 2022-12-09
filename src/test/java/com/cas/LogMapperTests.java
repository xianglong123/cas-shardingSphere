package com.cas;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cas.entity.Course;
import com.cas.entity.Log;
import com.cas.entity.User;
import com.cas.mapper.CourseMapper;
import com.cas.mapper.LogMapper;
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


    // ======== 测试垂直分库 ============
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
        wrapper.eq("user_id", 807620630714253313L);
        Log user = logMapper.selectOne(wrapper);
        System.out.println(JSONObject.toJSONString(user));
    }

}
