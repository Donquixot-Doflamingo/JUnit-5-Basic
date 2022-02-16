package io.harshkhandelwal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

// this is to make the test instance of the class and to modify how test instance is created
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// PER_CLASS -> the instance of test is created per class rather than making it for every method
// PER_METHOD -> this is the default ; the instance of test is created per method
class MathUtilsTest {
	
	MathUtils mathUtil;
	
	// runs before every test in the class
	@BeforeAll
	// we use static because before all can't run without instance of the class as being a method of the class. So 
	// to make it independent we use static
	
	// we don't have to use the static keyword as we are already making an test instance now 
//	static 
	void beforeAll() {
		System.out.println("Running before every method");
	}
	
	// runs before each method
	@BeforeEach 
	void init(){ 
		mathUtil = new MathUtils();
	}
	
	// runs after each method 
	@AfterEach
	void cleanUp() {
		System.out.println("Cleaning Up ...");
	}

	@Test // an annotation for JUnit to highlight that the current function is a test
	@DisplayName("add two numbers")
	// to provide a name to the function while running the test we use this annotation
	void testAdd() {
		int expected = 2;
		int actual = mathUtil.add(1, 1);
		assertEquals(expected, actual,"the add method add two numbers");
//		fail("Not yet implemented");
	}
	
	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtil.divide(1, 0), "Divide by 0 should throw");
		
	}
	
	@Test
	void testCircleArea() {
		double expected = 3.141592653589793;
		double actual = mathUtil.circleArea(1);
		assertEquals(expected, actual,"Display the area of circle");	
	}
	
	@Test
	@Disabled
	// to make the function skip the test run we use this annotation
	void disableMethod() {
		System.out.println("Disable test function");
	}

}
