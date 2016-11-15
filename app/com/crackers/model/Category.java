package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */
@NodeEntity(label = "Category")
public class Category implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    // Fields
    private Integer           idCategory;
    private String            categoryName;
    private Short             isDeleted;
    private Integer           sortOrder;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;

    @GraphId
    @Property(name = "idCategory")
    public Integer getIdCategory()
    {
        return this.idCategory;
    }

    public void setIdCategory(Integer idCategory)
    {
        this.idCategory = idCategory;
    }

    @Property(name = "sortOrder")
    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    @Property(name = "categoryName")
    public String getCategoryName()
    {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    @Property(name = "isDeleted")
    public Short getIsDeleted()
    {
        return this.isDeleted;
    }

    public void setIsDeleted(Short isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    @Property(name = "createdBy")
    public Integer getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    @Property(name = "createdOn")
    public Timestamp getCreatedOn()
    {
        return this.createdOn;
    }

    public void setCreatedOn(Timestamp createdOn)
    {
        this.createdOn = createdOn;
    }

    @Property(name = "updatedBy")
    public Integer getUpdatedBy()
    {
        return this.updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    @Property(name = "updatedOn")
    public Timestamp getUpdatedOn()
    {
        return this.updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn)
    {
        this.updatedOn = updatedOn;
    }
}