Cateogry

WITH [
		{idCateogry:1, categoryName:"SoundCrackers", isDeleted:0, sortOrder:1, createdBy:1, createdOn:TIMESTAMP(), updatedBy:1, updatedOn:TIMESTAMP()},
		{idCateogry:2, categoryName:"Sparkles", isDeleted:0, sortOrder:1, createdBy:1, createdOn:TIMESTAMP(), updatedBy:1, updatedOn:TIMESTAMP()}
	] AS values
FOREACH (val IN values | 
  CREATE (applicationConfig:ApplicationConfig 
   		{idCateogry:val.idCateogry, categoryName:val.categoryName, isDeleted:val.isDeleted, sortOrder: val.sortOrder, createdBy:val.createdBy, createdOn:val.createdOn, updatedBy:val.createdBy, updatedOn:val.createdOn}))