package configuration.management.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import configuration.management.jpa.entity.ConfigurationMaster;

public interface ConfigurationMasterRepository extends JpaRepository<ConfigurationMaster, Long> 
{
	List<ConfigurationMaster> findByParamName(String paramName);
}
