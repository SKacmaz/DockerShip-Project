package core;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.net.InetAddress;
import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

import resourcerer.Resourcerer;

public class Haven {
	
	private final static int HTTP_PORT = 8080;
    private final static int PING_PORT = 9999;  // port to ping the clients at
    static String localIP;               // server ip
    static HttpServer server;
    static Timer timer;

	public static void main(String[] args) {
		System.out.println("Starting Haven");
		
		
		//start Resourcer
		System.out.println("Starting Resourcerer");
		Resourcerer schmendrik = new Resourcerer();
		try {
			String result = schmendrik
					.getJsonFrom("");

			System.out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Start Scanner
		System.out.println("Starting Scanner");
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
		System.out.println("Please, enter 0 to exit.");
		int i = scanner.nextInt();
		if(i == 0){
			timer.cancel();
		}
	}
	
    static void startServer() throws Exception{
    	
    	// port for the HTTP connection
     	localIP = InetAddress.getLocalHost().getHostAddress();
        String url = "http://" + localIP + ":" + HTTP_PORT;

        server = GrizzlyServerFactory.createHttpServer("http://0.0.0.0:8080");

        System.out.println( "URL: " + url );
    	
    }
}
