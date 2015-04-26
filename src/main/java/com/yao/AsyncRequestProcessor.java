package com.yao;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaozb on 15-4-26.
 */
public class AsyncRequestProcessor implements Runnable{
    private AsyncContext asyncContext;
    private int seconds;
    public AsyncRequestProcessor(AsyncContext asyncContext, int seconds) {
        this.asyncContext = asyncContext;
        this.seconds = seconds;
    }
    @Override
    public void run() {
        System.out.println("Async Supported? "
        +asyncContext.getRequest().isAsyncStarted());
        longProcessing(seconds);
        try {
            PrintWriter out=asyncContext.getResponse().getWriter();
            out.print("Processing done for "+seconds+" seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
        asyncContext.complete();
    }
    private void longProcessing(int secs){
        try {
            TimeUnit.SECONDS.sleep(secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
