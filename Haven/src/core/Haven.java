package core;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

import resourcerer.DockerResourceEndpoint;
import resourcerer.Resourcerer;

public class Haven {

//	static final Logger LOGGER = LogManager.getLogger(Haven.class.getName());
	static final Logger LOGGER = Logger.getLogger(Haven.class.getName());

	private final static int HTTP_PORT = 8081;
	private final static int PING_PORT = 9999; // port to ping the clients at
	static String localIP; // server ip
	static HttpServer server;
	static Timer timer;

	public static void main(String[] args) throws IOException {
		LOGGER.info("");
		LOGGER.info("###############################");
		LOGGER.info("   Starting Haven");
		LOGGER.info("###############################");

		// start Resourcer
		LOGGER.info("Starting Resourcerer");
		
		Resourcerer schmendrik = new Resourcerer();
		String result = schmendrik.getJsonFrom(schmendrik.getAttachedResource(""));
		LOGGER.debug("Result form running Schmendrik: " + result);

		// Start Scanner
		LOGGER.info("Starting Scanner");
		Scanner scanner = new Scanner(System.in);

		try {
			startServer();
		} catch (Exception e) {
			LOGGER.error("Starting ApiServer failed with " + e);
		}

		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				LOGGER.debug("Running...");
			}
		}, 10000, 10000);
		LOGGER.debug("Please, enter 0 to exit.");
		int i = scanner.nextInt();
		if (i == 0) {
			timer.cancel();
		}
	}

	static void startServer() throws Exception {

		// port for the HTTP connection
		localIP = InetAddress.getLocalHost().getHostAddress();
		String url = "http://" + localIP + ":" + HTTP_PORT;

		server = GrizzlyServerFactory.createHttpServer("http://0.0.0.0:8081");

		LOGGER.info("Current URL is : " + url);
	}
}
