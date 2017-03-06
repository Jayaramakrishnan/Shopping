package com.crackers.model;

import java.sql.Timestamp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;

@Data
@NodeEntity(label = "UserCredentialAud")
public class UserCredentialAud implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;
    @GraphId
    @Property(name = "idRev")
    private Integer           idRev;
    private Integer           idUserCredential;
    private Short             revtype;
    private Integer           idUser;
    private String            saltKey;
    private String            hashedKey;
    private Short             isDeleted;
    private Integer           createdBy;
    private Timestamp         createdOn;
    private Integer           updatedBy;
    private Timestamp         updatedOn;
}