package trendyol.filmfinder.apitest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import trendyol.filmfinder.apitest.operations.FindFilmOperations;


public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Test started");
    	JUnitCore junit = new JUnitCore();
    	Result result = junit.run(FindFilmOperations.class);
    	
    	for(Failure failure : result.getFailures()) 
    	{
    		System.out.println(failure.toString());
    	}
    	System.out.println("Result succes is : " + result.wasSuccessful());
    }
}
