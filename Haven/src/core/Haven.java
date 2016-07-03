package core;

import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.json.JSONObject;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

import model.IResource;
import resourcerer.DockerEndpoint;
import resourcerer.MockedEndpoint;
import resourcerer.Resourcerer;
/**
 * This is the main class of Haven. It initializes all other components and the communication with the resources.
 * 
 * @author Kim Reichert, Jochen Joswig
 */
public class Haven {

//	static final Logger LOGGER = LogManager.getLogger(Haven.class.getName());
	static final Logger LOGGER = Logger.getLogger(Haven.class.getName());

	private final static int HTTP_PORT = 8081;
	private final static int PING_PORT = 9999; // port to ping the clients at
	static String localIP; // server ip
	static HttpServer server;
	static Timer timer;
	
	private final static boolean IS_MOCKED = false; //IF CHANGED ALSO ADAPT MyContextResolver!!

	public static void main(String[] args) throws IOException {
		LOGGER.info("");
		LOGGER.info("###############################");
		LOGGER.info("   Starting Haven");
		LOGGER.info("###############################");

		// Start Resourcerer
		LOGGER.info("##########");
		LOGGER.info("Starting Resourcerer");
		
		Resourcerer schmendrik;
		
		if(IS_MOCKED)
		{
		 schmendrik = new Resourcerer(new MockedEndpoint());
		 String r = schmendrik.getEndpoint("MockedEndpoint").testEnpoint();
		 LOGGER.info("Result from testing endpoint: "  + r);
		}
		else
		{
			schmendrik = new Resourcerer(new DockerEndpoint());
			String r = schmendrik.getEndpoint("DockerSwarm").testEnpoint();
			LOGGER.info("Result from testing endpoint: "  + r);
		}
		
		//Temporary for testing
		IResource[] foundResources = schmendrik.getResourceSet();
		LOGGER.info("Discovered " + foundResources.length + " active resources");
		

		// Start Scanner
		LOGGER.info("##########");
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
			scanner.close();
			timer.cancel();
		}
	}

	static void startServer() throws Exception {

		// port for the HTTP connection
		localIP = InetAddress.getLocalHost().getHostAddress();
		String url = "http://" + localIP + ":" + HTTP_PORT;

		server = GrizzlyServerFactory.createHttpServer("http://0.0.0.0:8081");

		LOGGER.info("Current URL of HAVEN is : " + url);
	}
}