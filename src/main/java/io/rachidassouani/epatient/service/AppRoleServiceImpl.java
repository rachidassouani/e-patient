package io.rachidassouani.epatient.service;

import io.rachidassouani.epatient.dao.AppRoleRepository;
import io.rachidassouani.epatient.dao.AppUserRepository;
import io.rachidassouani.epatient.model.AppRole;
import io.rachidassouani.epatient.model.AppUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppRoleServiceImpl implements AppRoleService {

    private final AppRoleRepository appRoleRepository;
    private final AppUserRepository appUserRepository;

    public AppRoleServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppRole save(String name, String description) {
        AppRole appRole = appRoleRepository.findByName(name);
        if (appRole != null) {
            throw new IllegalArgumentException("Role is already exist");
        }
        AppRole appRoleToSave = new AppRole();
        appRoleToSave.setName(name);
        appRoleToSave.setDescription(description);
        AppRole savedRole = appRoleRepository.save(appRoleToSave);
        return savedRole;
    }

    @Override
    public void addRoleToUser(String roleName, String username) {
        AppRole appRole = appRoleRepository.findByName(roleName);
        AppUser appUser = appUserRepository.findByUsername(username);

        if (appRole == null || appUser == null) {
            throw new IllegalArgumentException("cannot find role and/or user");
        }
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String roleName, String username) {
        AppRole appRole = appRoleRepository.findByName(roleName);
        AppUser appUser = appUserRepository.findByUsername(username);

        if (appRole == null || appUser == null) {
            throw new IllegalArgumentException("cannot find role and/or user");
        }
        appUser.getRoles().remove(appRole);
    }
}
