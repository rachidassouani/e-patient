package io.rachidassouani.epatient.service;

import io.rachidassouani.epatient.dao.AppUserRepository;
import io.rachidassouani.epatient.model.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser save(String username, String password, String repeatedPassword) {

        if (!password.equals(repeatedPassword)) {
            throw new IllegalArgumentException("passwords are not the same");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setActive(true);
        AppUser savedUser = appUserRepository.save(appUser);
        return savedUser;
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
