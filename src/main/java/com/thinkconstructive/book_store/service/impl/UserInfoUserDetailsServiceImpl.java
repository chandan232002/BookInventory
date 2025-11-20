package com.thinkconstructive.book_store.service.impl;

import com.thinkconstructive.book_store.entity.UserInfo;
import com.thinkconstructive.book_store.mapper.UserInfoUserDetailsServiceImplMapper;
import com.thinkconstructive.book_store.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserInfoUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<UserInfo> userInfo = userInfoRepository.findByUserName(username);
        return userInfo.map(UserInfoUserDetailsServiceImplMapper::new).orElseThrow(() -> new UsernameNotFoundException("username"+username+" not found"));

    }

}
