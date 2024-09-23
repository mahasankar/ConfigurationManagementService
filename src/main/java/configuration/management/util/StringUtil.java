package configuration.management.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StringUtil 
{
	public static String exceptionToString(Throwable ex)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream(); 
	    ex.printStackTrace(new PrintStream(out));
	    
	    return new String(out.toByteArray());
	}
}
