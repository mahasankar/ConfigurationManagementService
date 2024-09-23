package configuration.management.provider;

import java.util.List;

import configuration.management.jpa.entity.ConfigurationMaster;
import configuration.management.jpa.repository.ConfigurationMasterRepository;

public class PostgreSQLDBProvider implements ConfigurationManagementServiceProvider 
{
	private static ConfigurationMasterRepository configurationMasterRepository;
			
	@Override
	public String getConfig(String parameterName) throws Exception
	{
		List<ConfigurationMaster> listConfigurationMaster = configurationMasterRepository.findByParamName(parameterName);
		
		if (listConfigurationMaster != null && listConfigurationMaster.size() > 0)
		{
			ConfigurationMaster configurationMaster =  listConfigurationMaster.get(0);
			
			return configurationMaster.getParamValue();
		}
		
		return null;
	}
	
	@Override
	public void deleteConfig(String parameterName) throws Exception
	{
		List<ConfigurationMaster> listConfigurationMaster = configurationMasterRepository.findByParamName(parameterName);
		
		if (listConfigurationMaster != null && listConfigurationMaster.size() > 0)
		{
			ConfigurationMaster configurationMaster =  listConfigurationMaster.get(0);
			
			configurationMasterRepository.deleteById(configurationMaster.getConfigId());
		}
	}

	@Override
	public void updateConfig(String parameterName, String parameterValue) throws Exception
	{
		List<ConfigurationMaster> listConfigurationMaster = configurationMasterRepository.findByParamName(parameterName);
		
		ConfigurationMaster configurationMaster = null;
		
		if (listConfigurationMaster != null && listConfigurationMaster.size() > 0)
		{
			configurationMaster =  listConfigurationMaster.get(0);
			configurationMaster.setParamValue(parameterValue);
		}
		else
		{
			configurationMaster = new ConfigurationMaster();
			configurationMaster.setParamName(parameterName);
			configurationMaster.setParamValue(parameterValue);
		}
		
		configurationMasterRepository.save(configurationMaster);
	}
	
	public static ConfigurationMasterRepository getConfigurationMasterRepository() 
	{
		return configurationMasterRepository;
	}

	public static void setConfigurationMasterRepository(ConfigurationMasterRepository configurationMasterRepository) 
	{
		PostgreSQLDBProvider.configurationMasterRepository = configurationMasterRepository;
	}
}
