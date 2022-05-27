package io.rachidassouani.epatient.service;


import io.rachidassouani.epatient.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserService appUserService;

    public UserDetailsServiceImpl(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.loadUserByUsername(username);
        if (appUser == null) {
            throw new IllegalArgumentException("user not found");
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        appUser.getRoles().forEach(role -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(simpleGrantedAuthority);
        });
        User user = new User(appUser.getUsername(), appUser.getPassword(), authorities);
        return user;
    }
}
