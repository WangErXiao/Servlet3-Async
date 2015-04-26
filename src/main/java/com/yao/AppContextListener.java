package com.yao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yaozb on 15-4-26.
 */
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //把线程池添加到Context上
        ExecutorService executorService= Executors.newCachedThreadPool();
        servletContextEvent.getServletContext().setAttribute("executor",executorService);
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ExecutorService executorService=(ExecutorService)servletContextEvent.getServletContext().getAttribute("executor");
        executorService.shutdown();
    }
}
