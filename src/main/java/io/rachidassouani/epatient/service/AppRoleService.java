package io.rachidassouani.epatient.service;

import io.rachidassouani.epatient.model.AppRole;

public interface AppRoleService {
    AppRole save (String name, String description);
    void addRoleToUser(String roleName, String username);
    void removeRoleFromUser(String roleName, String username);
}
