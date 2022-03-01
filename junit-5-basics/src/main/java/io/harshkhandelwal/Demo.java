package io.harshkhandelwal;

import java.io.IOException;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Demo {

	public String fetch(String url) throws ClientProtocolException, IOException{
		String result = "";
		
		// Create a HttpClient object
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		// Create an HttpGet Object
		
		HttpGet httpget = new HttpGet(url);
		
		// Printing the method used
	    
		System.out.println("Request Type: "+httpget.getMethod());
	      
		//Execute the Get Request
		HttpResponse httpresponse = httpclient.execute(httpget);

		Scanner sc = new Scanner(httpresponse.getEntity().getContent());

	      // Printing the status line
	      System.out.println(httpresponse.getStatusLine());
	      while(sc.hasNext()) {
	         System.out.println(sc.nextLine());
	      }
	      
	    sc.close();
	    
		return result;
	}
	
	public static void main(String[] args)  throws IOException {
		// TODO Auto-generated method stub
		String url = "https://services.odata.org/TripPinRESTierService/(S(ohu3foymzb4q0ipdywyzolgd))/";
		Demo dm = new Demo();
		System.out.println(dm.fetch(url));
	}
	
}
