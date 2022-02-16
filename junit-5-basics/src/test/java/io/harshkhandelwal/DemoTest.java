package io.harshkhandelwal;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

class DemoTest {

Demo dm = null;

	// initialing the class Demo
	@BeforeEach
	public  void intialize() {
		dm = new Demo();
	}
	
	@Test
	public void test() throws ClientProtocolException , IOException {
		
		String s = "https://services.odata.org/TripPinRESTierService/(S(ohu3foymzb4q0ipdywyzolgd))/";
		String ex = "The page cannot be displayed because an internal server error has occurred.";
		String str = dm.fetch(s);
		assertNotEquals(ex,str);
	}
}
