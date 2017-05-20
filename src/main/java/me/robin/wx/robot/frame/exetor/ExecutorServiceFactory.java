/******************************************************************************
 * create by 2017年5月14日
 ******************************************************************************/

package me.robin.wx.robot.frame.exetor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
 * @version 2017年5月14日 作者
 */
public class ExecutorServiceFactory {
    
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
     * @version 2017年5月19日 作者
     */
    static class SequenceNameThreadFactory implements ThreadFactory {
        
        /** FIXME */
        private String threadName;
        
        /**
         * 构造函数
         * 
         * @param threadName x
         */
        public SequenceNameThreadFactory(String threadName) {
            this.threadName = threadName;
        }
        
        /** FIXME */
        private AtomicInteger sequence = new AtomicInteger();
        
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName(threadName + "-" + sequence.incrementAndGet());
            return t;
        }
    }
    
    /** FIXME */
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param threadCount x
     * @param threadName x
     * @return x
     */
    public static ExecutorService createExecutorService(int threadCount, String threadName) {
        return new ThreadPoolExecutor(1, threadCount, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
            new SequenceNameThreadFactory(threadName));
    }
    
    /** FIXME */
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param threadCount x
     * @param threadName x
     * @return x
     */
    public static ScheduledExecutorService newScheduledThreadPool(int threadCount, String threadName) {
        return Executors.newScheduledThreadPool(threadCount, new SequenceNameThreadFactory(threadName));
    }
    
    /** FIXME */
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param threadCount x
     * @param threadName x
     * @return x
     */
    public static ScheduledExecutorService newSingleThreadScheduledExecutor(String threadName) {
        return newScheduledThreadPool(1, threadName);
    }
}
