package configuration.management.provider;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import configuration.management.service.ConfigurationManagementService;
import configuration.management.util.StringUtil;

public class ConfigurationManagementServiceProviderFactory 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManagementServiceProviderFactory.class);
	
	public static void initProvider(String providerName)
	{
		try 
		{
			ConfigurationManagementServiceProvider provider = 
			(ConfigurationManagementServiceProvider) Class.forName(providerName).getDeclaredConstructor().newInstance();
			
			ConfigurationManagementService.setProvider(provider);
			
			LOGGER.error("Successfully initialized ConfigurationManagementServiceProvider for provider " + providerName);
		} 
		catch (  InstantiationException    | IllegalAccessException | IllegalArgumentException 
			   | InvocationTargetException | NoSuchMethodException  | SecurityException 
			   | ClassNotFoundException ex) 
		{
			LOGGER.error("Encountered exception initializing ConfigurationManagementServiceProvider for provider " + providerName + " -> ");
			LOGGER.error(StringUtil.exceptionToString(ex));
		}
	}

}
