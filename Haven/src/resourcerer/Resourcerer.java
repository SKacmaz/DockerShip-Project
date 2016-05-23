package resourcerer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Resourcerer {

	private List<URL> resources;

	public Resourcerer(URL... resourceUrls) {
		resources = new ArrayList<URL>();
		for (URL url: resourceUrls)
		{
			resources.add(url);
		}
	}

	public String getJsonFrom(String urlToRead) throws IOException {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}
}
