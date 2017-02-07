ApplicationConfig

WITH [
		{idApplicationConfig:1, configKey:"version", configValue:"1", createdBy:1, createdOn:TIMESTAMP()},
	    {idApplicationConfig:2, configKey:"restbase.url", configValue:"localhost:9008", createdBy:1, createdOn:TIMESTAMP()}, 
		{idApplicationConfig:3, configKey:"sessionTimeout", configValue:"180000", createdBy:1, createdOn:TIMESTAMP()},
		{idApplicationConfig:4, configKey:"appStartYear", configValue:"2017", createdBy:1, createdOn:TIMESTAMP()},
		{idApplicationConfig:5, configKey:"applicationNameInheader", configValue:"Crackers", createdBy:1, createdOn:TIMESTAMP()},
		{idApplicationConfig:6, configKey:"applicationNameInLogin" , configValue:"Crackers", createdBy:1, createdOn:TIMESTAMP()},
		{idApplicationConfig:7, configKey:"isApplicationLogoAvailable", configValue:"1", createdBy:1, createdOn:TIMESTAMP()},
		{idApplicationConfig:8, configKey:"applicationTitleName", configValue:"Crackers", createdBy:1, createdOn:TIMESTAMP()},
		{idApplicationConfig:9, configKey:"date.format", configValue:"dd/MM/yyyy", createdBy:1, createdOn:TIMESTAMP()},
        {idApplicationConfig:10, configKey:"autoSaveTimeout", configValue:"180000", createdBy:1, createdOn:TIMESTAMP()},
        {idApplicationConfig:11, configKey:"isTLSOn", configValue:"1", createdBy:1, createdOn:TIMESTAMP()},
        {idApplicationConfig:12, configKey:"isSendEmailOn", configValue:"1", createdBy:1, createdOn:TIMESTAMP()},
        {idApplicationConfig:13, configKey:"read_from_conf", configValue:"0", createdBy:1, createdOn:TIMESTAMP()}
	] AS values
FOREACH (val IN values | 
  CREATE (applicationConfig:ApplicationConfig 
   		{idApplicationConfig:val.idApplicationConfig, configKey:val.configKey, configValue:val.configValue, createdBy:val.createdBy, createdOn:val.createdOn}))