package io.harshkhandelwal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

//TODO to make the test instance of the class and to modify how test instance is created
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// PER_CLASS -> the instance of test is created per class rather than making it for every method
// PER_METHOD -> this is the default ; the instance of test is created per method
class MathUtilsTest {
	
	MathUtils mathUtil;
	
	// using test info and test report for giving out test information
	TestInfo testInfo;
	TestReporter testReporter;
	// TODO to learn about before each, after each and before all
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
	void init(TestInfo testInfo, TestReporter testReporter){ 
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtil = new MathUtils();
	}
	
	// runs after each method 
	@AfterEach
	void cleanUp() {
		testReporter();
		System.out.println("Cleaning Up ...");
	}
	
	@Test // TODO used annotation for JUnit to highlight that the current function is a test
	@DisplayName("add two numbers")
	// TODO to provide a name to the function while running the test we use this annotation
	void testAdd() {
		int expected = 2;
		int actual = mathUtil.add(1, 1);
		// TODO used assert equal to compare two values and implement a string if failed
		assertEquals(expected, actual,"the add method add two numbers");
	}

// TODO Used assertThrows to throw an expected exception
	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtil.divide(1, 0), "Divide by 0 should throw");
		
	}
	
// TODO to make the function skip the test run we use this annotation
	@Test
	@Disabled
	void disableMethod() {
		System.out.println("Disable test function");
	}
// TODO to make a test only to work on JRE11 and MACOS 
	@Test
	@EnabledOnJre(JRE.JAVA_10)
	@EnabledOnOs(OS.MAC)
	void test() {
		System.out.println("Will only work on MAC with JRE 10");
	}
	
//TODO Using assumptions in test cases
	@Test
	void assume() {
		Boolean server = false;
		assumeTrue(server);
		System.out.println("This will not fail even if the server is not up and will be skipped");
			
	}
	
//TODO used Assert All 
	@Test
	void assertAllExample() {
		assertAll(
					() -> assertEquals(2, mathUtil.add(1, 1)),
					() -> assertEquals(3, mathUtil.add(2, 1)),
					() -> assertEquals(4, mathUtil.add(2, 2))
				);
	}
	
// TODO to make a nested class test
	@Nested
	@DisplayName("Testing Add Methods")
	class AddTest{
		@Test 
		@DisplayName("add two +ve numbers")
		void testAddPos() {
			assertEquals(2, mathUtil.add(1, 1),"the add method add two numbers");
		}
		
		@Test 
		@DisplayName("add two -ve numbers")
		void testAddNeg() {
			assertEquals(-2, mathUtil.add(-1, -1),"the add method add two numbers");
		}
	}
	
// TODO Using supplier for assert messages
	@Test
	void supplier(){
	/** FIXME Need of lambda function in assert fail string
		 * we use lambda in fail string of assert because we don't want to allot the string a storage until the test fails 
		 	else it will not be much optimized
		 *  so to make it store only when the test fails we use lambda fun' as it 
		 	promise to call it only when the test fails
	 */
		int expected = 2;
		int actual = mathUtil.add(1, 1);
		assertEquals(expected, actual,() -> "add two numbers, o/p" + expected + "but was" + actual);
	}

// TODO Make a repeated test and access each repetition individually
	@RepeatedTest(4)
	void testCircleArea(RepetitionInfo repetitionInfo) {
		System.out.println("number of times repeated -: " + repetitionInfo.getCurrentRepetition());
		double expected = 3.141592653589793;
		double actual = mathUtil.circleArea(1);
		assertEquals(expected, actual,"Display the area of circle");	
	}
	
	@Test
	@Tag("Information")
	void testInformation(){
		System.out.println("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
	}
	
	@Tag("Information")
	void testReporter(){
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
	}
	
}
