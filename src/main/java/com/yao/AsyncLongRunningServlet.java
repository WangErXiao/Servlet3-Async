package com.yao;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * Created by yaozb on 15-4-26.
 */
public class AsyncLongRunningServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet Start::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId());


        String time = request.getParameter("time");
        int secs = Integer.valueOf(time);
        // max 10 seconds
        if (secs > 10)
            secs = 10;
        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.addListener(new AppAsynListener());
        asyncCtx.setTimeout(11000);
        ExecutorService executor = (ExecutorService) request
                .getServletContext().getAttribute("executor");

        executor.execute(new AsyncRequestProcessor(asyncCtx, secs));
        long endTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet End::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId() + "::Time Taken="
                + (endTime - startTime) + " ms.");
    }

}
