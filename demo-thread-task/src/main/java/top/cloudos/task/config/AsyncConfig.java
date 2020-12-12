package top.cloudos.task.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/12 9:24
 * @description 线程池配置类
 **/
@Configuration
@EnableAsync
public class AsyncConfig {
    /**
     * 接收报文核心线程数
     */
    @Value("${user.core.poolsize}")
    private int userCorePoolSize;
    /**
     * 接收报文最大线程数
     */
    @Value("${user.max.poolsize}")
    private int userMaxPoolSize;
    /**
     * 接收报文队列容量
     */
    @Value("${user.queue.capacity}")
    private int userQueueCapacity;
    /**
     * 接收报文线程活跃时间（秒）
     */
    @Value("${user.keepAlive.seconds}")
    private int userKeepAliveSeconds;
    /**
     * 接收报文默认线程名称
     */
    @Value("${user.thread.name.prefix}")
    private String userThreadNamePrefix;


    /**
     * 接口的线程池
     *
     * @return
     */
    @Bean(name = "UserTask")
    public ThreadPoolTaskExecutor userTaskExecutor() {
        //newFixedThreadPool
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(userCorePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(userMaxPoolSize);
        // 设置队列容量
        executor.setQueueCapacity(userQueueCapacity);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(userKeepAliveSeconds);
        // 设置默认线程名称
        executor.setThreadNamePrefix(userThreadNamePrefix);
        // 设置拒绝策略
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务  
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行 
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
}
