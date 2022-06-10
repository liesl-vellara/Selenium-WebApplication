package webDriver;

import org.testng.annotations.Test;

public class groups {
	@Test(groups = {"smoke"}, priority = 0)
	public void smoke()
	{
		System.out.println("Smoke 0");
	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void smoke1()
	{
		System.out.println("Smoke 1");
	}

	
	@Test(groups = {"regression"}, priority = 0)
	public void regression()
	{
		System.out.println("regression 0");
	}

	
	@Test(groups = {"regression"}, priority = 1)
	public void regression1()
	{
		System.out.println("regression 1");
	}


}
