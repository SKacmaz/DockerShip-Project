package core;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.logging.log4j.*;
import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

import resourcerer.DockerResource;
import resourcerer.Resourcerer;

public class Haven {

	/* Get actual class name to be printed on */
	static final Logger logger = LogManager.getLogger(Haven.class.getName());

	private final static int HTTP_PORT = 8080;
	private final static int PING_PORT = 9999; // port to ping the clients at
	static String localIP; // server ip
	static HttpServer server;
	static Timer timer;

	public static void main(String[] args) throws IOException {
		logger.info("Starting Haven");

		// start Resourcer
		logger.info("Starting Resourcerer");
		
		Resourcerer schmendrik = new Resourcerer();
		String result = schmendrik.getJsonFrom(schmendrik.getAttachedResource(""));
		logger.debug(result);

		// Start Scanner
		logger.info("Starting Scanner");
		Scanner scanner = new Scanner(System.in);

		try {
			startServer();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Starting ApiServer failed with " + e);
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
		if (i == 0) {
			timer.cancel();
		}
	}

	static void startServer() throws Exception {

		// port for the HTTP connection
		localIP = InetAddress.getLocalHost().getHostAddress();
		String url = "http://" + localIP + ":" + HTTP_PORT;

		server = GrizzlyServerFactory.createHttpServer("http://0.0.0.0:8080");

		logger.info("Current URL is : " + url);
	}
}
