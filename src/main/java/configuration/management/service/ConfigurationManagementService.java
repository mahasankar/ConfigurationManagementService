package configuration.management.service;

import org.springframework.stereotype.Service;

import configuration.management.provider.ConfigurationManagementServiceProvider;

@Service
public class ConfigurationManagementService 
{
	private static ConfigurationManagementServiceProvider provider;
	
	public String getConfig(String parameterName) throws Exception
	{
		return provider.getConfig(parameterName);
	}
	
	public void deleteConfig(String parameterName) throws Exception
	{
		provider.deleteConfig(parameterName);
	}
	
	public void updateConfig(String parameterName, String parameterValue) throws Exception
	{
		provider.updateConfig(parameterName, parameterValue);
	}
	
	public static void setProvider(ConfigurationManagementServiceProvider providerToSet)
	{
		provider = providerToSet;
	}
}
