package configuration.management.provider;

import org.springframework.stereotype.Component;

@Component
public interface ConfigurationManagementServiceProvider 
{
	public String getConfig(String parameterName) throws Exception;
	
	public void deleteConfig(String parameterName) throws Exception;
	
	public void updateConfig(String parameterName, String parameterValue) throws Exception;
}
