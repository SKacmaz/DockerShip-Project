package resourcerer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class Resourcerer {

	private List<URL> resources;
	public Resourcerer(URL...resourceUrls)
	{
		resources = new ArrayList<URL>();
//		resources.addAll(resourceUrls.)
	}
	public String getJsonFrom(String urlToRead) throws IOException
	{
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
	
	
	public static void main(String[] args) {
		Resourcerer schmendrik = new Resourcerer();	
		try {
			String result  = 
					schmendrik.getJsonFrom("http://ec2-52-29-189-238.eu-central-1.compute.amazonaws.com:2375/images/json");
		
			System.out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
