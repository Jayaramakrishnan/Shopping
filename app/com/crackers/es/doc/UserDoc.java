package com.crackers.es.doc;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.crackers.dto.UserDto;
import com.crackers.es.ESConstants;
import com.crackers.es.controllers.IndexAllRecordsController;

public class UserDoc
{

    private UserDto       users;
    private List<Integer> roles;
    private List<Integer> accessibleUsers;

    public UserDoc(UserDocBuilder userDocBuilder) {
        super();
        this.users = userDocBuilder.getUsers();
        this.roles = userDocBuilder.getRoles();
        this.accessibleUsers = userDocBuilder.getAccessibleUsers();
    }

    public UserDoc() {
    }

    public UserDto getUsers()
    {
        return users;
    }

    public void setUsers(UserDto users)
    {
        this.users = users;
    }

    public List<Integer> getAccessibleUsers()
    {
        return accessibleUsers;
    }

    public void setAccessibleUsers(List<Integer> accessibleUsers)
    {
        this.accessibleUsers = accessibleUsers;
    }

    public List<Integer> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Integer> roles)
    {
        this.roles = roles;
    }

    public static class UserDocBuilder
    {

        private UserDto                   users;
        private List<Integer>             roles;
        private List<Integer>             accessibleUsers;
        private ApplicationContext        context                   = com.crackers.common.CommonConstants.CONTEXT;
        private IndexAllRecordsController indexAllRecordsController = context.getBean(IndexAllRecordsController.class);

        public List<Integer> getRoles()
        {
            return roles;
        }

        public void setRoles(List<Integer> roles)
        {
            this.roles = roles;
        }

        public List<Integer> getAccessibleUsers()
        {
            return accessibleUsers;
        }

        public void setAccessibleUsers(List<Integer> accessibleUsers)
        {
            this.accessibleUsers = accessibleUsers;
        }

        public UserDto getUsers()
        {
            return users;
        }

        public void setUsers(UserDto users)
        {
            this.users = users;
        }

        public UserDocBuilder setUserDto(UserDto userDto)
        {
            String userSettingsId = ESConstants.USER_SETTINGS + userDto.getIdUser();
            UserDoc userDoc = indexAllRecordsController.getUserSettingsIndexDocById(userSettingsId);
            if (userDoc != null)
            {
                this.roles = userDoc.getRoles();
                if (userDoc.getAccessibleUsers() != null)
                {
                    this.accessibleUsers = userDoc.getAccessibleUsers();
                }
            }
            this.users = userDto;
            return this;
        }

        public UserDoc build()
        {
            return new UserDoc(this);
        }
    }
}