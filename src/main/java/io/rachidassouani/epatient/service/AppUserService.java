package io.rachidassouani.epatient.service;

import io.rachidassouani.epatient.model.AppUser;

public interface AppUserService {
    AppUser save(String username, String password, String repeatedPassword);
    AppUser loadUserByUsername(String username);
}
