package com.cas;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cas.entity.Course;
import com.cas.entity.Record;
import com.cas.mapper.RecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2025/02/10 12:59 下午
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordMapperTests {

    @Autowired
    private RecordMapper recordMapper;

    // ======== 测试水平分表 ============
    @Test
    public void add() {
        Record record = new Record();
        record.setId(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
        record.setContent(UUID.randomUUID().toString().substring(0, 10));
        record.setCreateDate(LocalDate.of(2022, 1, 1));
        record.setCreateTime(LocalDateTime.now());
        record.setUpdateTime(LocalDateTime.now());
        recordMapper.insert(record);
    }


    /**
     * 批量插入
     */
    @Test
    public void addBatch() {
        for (int i = 0; i < 10; i ++) {
            recordMapper.insert(createDO());
        }
    }

    private Record createDO() {
        Record record = new Record();
        record.setId(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
        record.setContent(UUID.randomUUID().toString().substring(0, 10));
        LocalDate createDate = LocalDate.now().minusYears(new Random().nextInt(3));
        System.out.println(createDate);
        record.setCreateDate(createDate);
        record.setCreateTime(LocalDateTime.now());
        record.setUpdateTime(LocalDateTime.now());
        return record;
    }

    /**
     * 区间查询 between
     */
    @Test
    public void queryByBetween() {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.between("create_date",  LocalDate.of(2020, 1, 1),  LocalDate.of(2025, 10, 1));
        List<Object> objects = recordMapper.selectObjs(wrapper);
        System.out.println("======= " + objects.size());
    }

    /**
     * 区间查询 in
     */
    @Test
    public void queryByIn() {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.in("create_date",  LocalDate.of(2022, 1, 1),  LocalDate.of(2025, 2, 10));
        List<Object> objects = recordMapper.selectObjs(wrapper);
        System.out.println("======= " + objects.size());
    }

}
