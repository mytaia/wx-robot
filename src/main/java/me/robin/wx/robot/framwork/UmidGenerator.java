/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package me.robin.wx.robot.framwork;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 * 
 * <pre>
 * [
 * 调用关系:
 * 实现接口及父类:
 * 子类:
 * 内部类列表:
 * ]
 * </pre>
 * 
 * @author 作者
 * @since 1.0
 * @version 2017年5月23日 作者
 */
public class UmidGenerator {
    
    /** 本轮时间计数 **/
    private static volatile long count = 0;
    
    /** 计数器向左移位10位 */
    private static final int MAX_COUNT = 2 ^ 10 - 1;
    
    /** 计数器向左移位10位 */
    private static final int MAX_RANDOM = 2 ^ 4;
    
    /** 节点编号 */
    private static int nodeId = 0;
    
    /** 时间戳留44位，向左移位20位 */
    private static final int TIME_OFFSET = 20;
    
    /** 计数器向左移位10位 */
    private static final int COUNT_OFFSET = 10;
    
    /** 计数器向左移位10位 */
    private static final int RAND_OFFSET = 6;
    
    /**
     * 获取下一个UMID
     * 
     * @param nodeId 节点ID
     * @return 返回UMID
     */
    public synchronized static long nextId() {
        long time = System.currentTimeMillis();
        if (++count >= MAX_COUNT) {
            count = 0;
        }
        int rnd = RandomUtils.nextInt(0, MAX_RANDOM);
        
        return (time << TIME_OFFSET) + (count << COUNT_OFFSET) + (rnd << RAND_OFFSET) + (nodeId & 0x7F);
    }
    
    /**
     * 根据时间生成umid
     * 
     * @param date 日期对象
     * @return 返回以时间对应的umid
     */
    public static String fromDate(Date date) {
        long time = date.getTime();
        return format(time << TIME_OFFSET);
    }
    
    /**
     * 获取下一个Id
     * 
     * @return 下一个Id
     */
    public static String nextIdString() {
        return format(nextId());
    }
    
    /**
     * 将一个long型转换为字符串型
     * 
     * @param value xx
     * @return 下一个Id的16进制
     */
    private static String format(long value) {
        return String.format("%016x", value);
    }
    
    /**
     * 设置节点ID
     *
     * @param nid xx
     */
    public static void initNodeId(int nid) {
        UmidGenerator.nodeId = nid;
    }
}
