package xunit.testng.testngexaples.packageC;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ClassCTest
{
	@Test
	@Description("testCaseC inside ClassCTest inside packageC")
	public void testCaseC() {
		System.out.println("testCaseC inside ClassCTest inside packageC");
	}
}
