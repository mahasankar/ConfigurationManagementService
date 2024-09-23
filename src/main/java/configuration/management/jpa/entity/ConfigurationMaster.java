package configuration.management.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(schema="public", name = "CONFIGURATION_MASTER")
@NoArgsConstructor
public class ConfigurationMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CONFIG_ID")
	private long configId;
	
	@Column(name="PARAM_NAME")
	private String paramName;
	
	@Column(name="PARAM_VALUE")
	private String paramValue;
	
	public long getConfigId() 
	{
		return configId;
	}

	public void setId(long configId) 
	{
		this.configId = configId;
	}
	
	public String getParamName() 
	{
		return paramName;
	}

	public void setParamName(String paramName) 
	{
		this.paramName = paramName;
	}

	public String getParamValue() 
	{
		return paramValue;
	}

	public void setParamValue(String paramValue) 
	{
		this.paramValue = paramValue;
	}
}
