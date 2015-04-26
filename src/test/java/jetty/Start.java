package jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by yaozb on 15-4-26.
 */
public class Start {
    public static void main(String[]args) throws Exception {
        Server server = new Server();
        // connector
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(9999);
        server.setConnectors(new Connector[] { connector });
        //context
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        //设定webapp,具体的路径根据自己应用来调整
        context.setResourceBase("./src/main/webapp");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();
        server.join();
    }
}
