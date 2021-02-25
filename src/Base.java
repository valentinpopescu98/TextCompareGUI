import java.awt.print.Printable;
import java.io.IOException;

public class Base 
{
	String text1;
	String text2;
	
	public Base(String text1, String text2)
	{
		setText1(text1);
		setText2(text2);
	}
	
	public void setText1(String t1)
	{
		text1 = t1;
	}
	
	public void setText2(String t2)
	{
		text2 = t2;
	}
	
	public String getText1()
	{
		return text1;
	}
	
	public String getText2()
	{
		return text2;
	}
	
	public boolean compareStrings(String text1, String text2)
	{
		if (text1.equals(text2))
			return true;
		
		return false;
	}
	
	public void quit()
	{
		System.exit(0);
	}
}
