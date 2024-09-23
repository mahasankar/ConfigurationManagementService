package configuration.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import configuration.management.jpa.repository.ConfigurationMasterRepository;
import configuration.management.provider.ConfigurationManagementServiceProviderFactory;
import configuration.management.provider.PostgreSQLDBProvider;

@SpringBootApplication
@ComponentScan(basePackages = { "configuration.management",
								"configuration.management.controller", 
								"configuration.management.provider",
								"configuration.management.service"
							  })
public class ConfigurationManagementServiceApplication 
{
	@Value("${configuration.management.service.provider.name}")
	private String configurationManagementServiceProviderName;
	
	@Autowired
	ConfigurationMasterRepository configurationMasterRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManagementServiceApplication.class);

	public static void main(String[] args) 
	{
		SpringApplication.run(ConfigurationManagementServiceApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void initializeAfterStartup() 
	{
		LOGGER.info("configurationMasterRepository is null? " + (configurationMasterRepository == null));
		
		LOGGER.info("configurationManagementServiceProviderName -> " + configurationManagementServiceProviderName);
		
		PostgreSQLDBProvider.setConfigurationMasterRepository(configurationMasterRepository);
		
		ConfigurationManagementServiceProviderFactory.initProvider(configurationManagementServiceProviderName);
	}
}
