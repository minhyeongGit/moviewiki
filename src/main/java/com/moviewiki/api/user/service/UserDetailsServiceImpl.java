package com.moviewiki.api.user.service;

import com.moviewiki.api.user.domain.UserRole;
import com.moviewiki.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("MemberUserDetailServiceImpl userId: " + username);
        com.moviewiki.api.user.domain.User member = userRepository.findByUserId(username);
        System.out.println("loadUserByUsername member: " + member);

        User user = null;
        if (member != null) {

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>(2);

            grantedAuthorities.add(new SimpleGrantedAuthority(
                    member.isUserAdmin() ? UserRole.ADMIN.getValue() : UserRole.MEMBER.getValue()));
            System.out.println(member.getUserId());
            user = new User(member.getUserId(), member.getUserPw(), grantedAuthorities);
        }

        return user;
    }
}
