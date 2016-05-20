package core;

import java.net.InetAddress;
import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

public class Haven {
	
	private final static int HTTP_PORT = 8080;
    private final static int PING_PORT = 9999;  // port to ping the clients at
    static String localIP;               // server ip
    static HttpServer server;

	public static void main(String[] args) {
		System.out.println("Hello World!");

		try {
			startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    static void startServer() throws Exception{
    	
    	// port for the HTTP connection
     	localIP = InetAddress.getLocalHost().getHostAddress();
        String url = "http://" + localIP + ":" + HTTP_PORT;

        server = GrizzlyServerFactory.createHttpServer(url);

        System.out.println( "URL: " + url );
    	
    }
}
