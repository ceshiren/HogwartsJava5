package xunit.junit.junitexamples.packageA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassATest 
{
	@Tag("production")
	@Test
	@DisplayName("testCaseA inside ClassATest inside packageA")
	public void testCaseA() {
		System.out.println("testCaseA inside ClassATest inside packageA");
		assertTrue(false);
	}
	@Tag("production")
	@Test
	@DisplayName("testCaseA02 inside ClassATest inside packageA")
	public void testCaseA02() {
		System.out.println("testCaseA02 inside ClassATest inside packageA");
		assertTrue(false);
	}
	@Tag("production")
	@Test
	@DisplayName("testCaseA03 inside ClassATest inside packageA")
	public void testCaseA03() {
		System.out.println("testCaseA03 inside ClassATest inside packageA");
		assertTrue(false);
	}
}
