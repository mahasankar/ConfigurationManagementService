package configuration.management.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import configuration.management.service.ConfigurationManagementService;
import configuration.management.util.StringUtil;

@RestController
@RequestMapping("/ConfigurationManagementService/V1")
public class ConfigurationManagementServiceController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManagementServiceController.class);
	
	@Autowired
	ConfigurationManagementService configurationManagementService;
	
	@GetMapping(value = "/config/get/{parameterName}")
	public ResponseEntity<String> getConfig(@PathVariable String parameterName) 
	{
		String parameterValue;
		
		LOGGER.info("parameterName -> " + parameterName);
		
		try 
		{
			parameterValue = configurationManagementService.getConfig(parameterName);
			
			if (parameterValue == null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
			}
		} 
		catch (Exception ex) 
		{
			LOGGER.error("Encountered Exception -> " + StringUtil.exceptionToString(ex));
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to fulfill request");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(parameterValue);
	}
	
	@DeleteMapping(value = "/config/delete/{parameterName}")
	public ResponseEntity<String> deleteConfig(@PathVariable String parameterName) 
	{
		String parameterValue;
		
		LOGGER.info("parameterName -> " + parameterName);
		
		try 
		{
			parameterValue = configurationManagementService.getConfig(parameterName);
			
			if (parameterValue == null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
			}
			
			configurationManagementService.deleteConfig(parameterName);
		} 
		catch (Exception ex) 
		{
			LOGGER.error("Encountered Exception -> " + StringUtil.exceptionToString(ex));
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to fulfill request");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	@PostMapping(value = "/config/update", consumes = "application/json")
	public ResponseEntity<String> updateConfig(@RequestBody Map<String, String> payload) 
	{
		
		String parameterName  = payload.get("parameterName");
		String parameterValue = payload.get("parameterValue");
		
		LOGGER.info("payload -> " + payload);
		
		try 
		{
			configurationManagementService.updateConfig(parameterName, parameterValue);
		} 
		catch (Exception ex) 
		{
			LOGGER.error("Encountered Exception -> " + StringUtil.exceptionToString(ex));
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to fulfill request");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
