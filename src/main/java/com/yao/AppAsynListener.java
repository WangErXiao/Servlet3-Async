package com.yao;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yaozb on 15-4-26.
 */
public class AppAsynListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("AppAsyncListener onComplete");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println("AppAsyncListener onTimeout");
        //we can send appropriate response to client
        ServletResponse response = asyncEvent.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.write("TimeOut Error in Processing");
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        System.out.println("AppAsyncListener onError");
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println("AppAsyncListener onStartAsync");
    }
}
