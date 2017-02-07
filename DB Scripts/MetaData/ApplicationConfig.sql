ApplicationConfig

WITH [
		{idApplicationConfig:1, configKey:"version", configValue:"1", createdBy:1, createdOn:TIMESTAMP()},
	    {idApplicationConfig:2, configKey:"restbase.url", configValue:"localhost:9008", createdBy:1, createdOn:TIMESTAMP()}, 
		{idApplicationConfig:3, configKey:"sessionTimeout", configValue:"180000", createdBy:1, createdOn:TIMESTAMP()}
	] AS values
FOREACH (val IN values | 
  CREATE (applicationConfig:ApplicationConfig 
   		{idApplicationConfig:val.idApplicationConfig, configKey:val.configKey, configValue:val.configValue, createdBy:val.createdBy, createdOn:val.createdOn}))