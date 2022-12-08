package com.cas;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cas.entity.Course;
import com.cas.entity.User;
import com.cas.mapper.CourseMapper;
import com.cas.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/12/8 12:59 下午
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTests{

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    // ======== 测试垂直分库 ============
    @Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("Java");
        user.setUstatus("Normal");
        userMapper.insert(user);
    }

    @Test
    public void findUserDb() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 807620630714253313L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(JSONObject.toJSONString(user));
    }

    // ======== 测试水平分库 ============
    @Test
    public void addCourseDb() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("Java" + i);
            course.setCstatus("Normal" + i);
            course.setUserId(101L);
            courseMapper.insert(course);
        }
    }

    @Test
    public void findCourseDb() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 101);
        wrapper.eq("cid", 807610762536157184L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(JSONObject.toJSONString(course));
    }

    // ======== 测试水平分表 ============
    @Test
    public void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("Java" + i);
            course.setCstatus("Normal" + i);
            course.setUserId(100L);
            courseMapper.insert(course);
        }
    }

    @Test
    public void findCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", 807602279996719104L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

}
