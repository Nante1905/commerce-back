package com.nante.commerce.services.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.services.employe.EmployeService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeService employeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employe employe;
        try {
            employe = this.employeService.findByEmail(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new User(employe.getEmail(), "", getAuthorities(employe));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Employe emp) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("EMP"));
        authorities.add(new SimpleGrantedAuthority(emp.getDirection().getCode()));
        authorities.add(new SimpleGrantedAuthority(emp.getCodePoste()));
        authorities.add(new SimpleGrantedAuthority(emp.getDirection().getCode() + " " + emp.getCodePoste()));
        return authorities;
    }

}
