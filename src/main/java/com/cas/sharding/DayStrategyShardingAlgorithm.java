package com.cas.sharding;

import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
 
 
/**
 * @Description: sharding分表规则：按单月分表
 * @Author: lg
 * @Date: 2022/6/9
 * @Version: V1.0
 */
@Component
public class DayStrategyShardingAlgorithm implements StandardShardingAlgorithm<LocalDateTime> {

    private static final Logger log = LoggerFactory.getLogger(DayStrategyShardingAlgorithm.class);

    private static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");


    /**
     * 精确分片算法类名称，用于=和IN
     * @param availableTargetNames:配置文件中定义的表名称集合
     * @param shardingValue:insert,select,update,delete时，分表字段request_date字段值
     * @return 返回函数值shardingValue年份对应的表名。比如 request_date=2022-06-01，返回表名dicp_access_log_202206
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<LocalDateTime> shardingValue) {
        if (shardingValue != null) {
            //获取年份
            String ymd = shardingValue.getValue().format(yyyyMMdd) ;
            //根据年月判断表名集合是否存在对应表表名集合，存在时返回表名
            for (String each : availableTargetNames) {
                if (each.endsWith(ymd)) {
                    return each;
                }
            }
            log.info("content_channel没有匹配到可用表");
        } else {
            log.info("分片列为空");
        }
        return null;
    }

    /**
     * 范围分片算法类名称，用于BETWEEN，可选
     * @param availableTargetNames:配置文件中定义的表名称集合
     * @param rangeShardingValue:insert,select,update,delete时，分表字段request_date 范围查询字段值。比如 between 2022-01-01 and 2022-02-01，存储的就是 2022-01-01和2022-02-01
     * @return 返回函数值rangeShardingValue 年份对应的表名集合比如 between 2022-01-01 and 2022-02-01 ，根据这时间段年份，得到返回的集合是dicp_access_log_202201,dicp_access_log_202202
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<LocalDateTime> rangeShardingValue) {
        Collection<String> collect = new ArrayList<>();
        if (rangeShardingValue != null) {
            //获得范围区间值
            Range<LocalDateTime> valueRange = rangeShardingValue.getValueRange();
            //获得返回区间值下限
            LocalDateTime slowerEndpointDate = valueRange.lowerEndpoint();
            //获得范围区间值上限
            LocalDateTime supperEndpointDate = valueRange.upperEndpoint();
            //获得下限年份
            int nStartYear = Integer.parseInt(slowerEndpointDate.format(yyyyMMdd));
            //获得上限年份
            int nEndYear = Integer.parseInt(supperEndpointDate.format(yyyyMMdd));
            //根据上下限范围，循环取值判断对应的表名称，返回符合条件的表名称集合
            for (int i = nStartYear; i <= nEndYear; i++) {
                for (String each : availableTargetNames) {
                    if (each.endsWith(String.valueOf(i))) {
                        if(!collect.contains(each)){
                            collect.add(each);
                        }
                    }
                }
            }
            return collect;
        }
        return null;
    }
 

    @Override
    public void init() {
 
    }
 
    @Override
    public String getType() {
        //　自定义 这里需要spi支持
        return null;
    }
 
}