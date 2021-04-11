package xunit.testng.testngexaples.packageB;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ClassBTest 
{

	@Test
	@Description("testCaseB inside ClassBTest inside packageB")
	public void testCaseB() {
		System.out.println("testCaseB inside ClassBTest inside packageB");

	}
}
