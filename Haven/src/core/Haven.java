package core;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.Console;
import java.net.InetAddress;
import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

public class Haven {
	
	private final static int HTTP_PORT = 8080;
    private final static int PING_PORT = 9999;  // port to ping the clients at
    static String localIP;               // server ip
    static HttpServer server;
    static Timer timer;

	public static void main(String[] args) {
		System.out.println("Hello World!");
		Scanner scanner = new Scanner(System.in);

		try {
			startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Running...");
			}
		}, 10000, 10000);
		System.out.println("Please, enter any number to exit.");
		int i = scanner.nextInt();
	}
	
    static void startServer() throws Exception{
    	
    	// port for the HTTP connection
     	localIP = InetAddress.getLocalHost().getHostAddress();
        String url = "http://" + localIP + ":" + HTTP_PORT;

        server = GrizzlyServerFactory.createHttpServer(url);

        System.out.println( "URL: " + url );
    	
    }
}
