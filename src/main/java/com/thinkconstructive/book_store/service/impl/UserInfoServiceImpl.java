package com.thinkconstructive.book_store.service.impl;

import com.thinkconstructive.book_store.dto.UserInfoDto;
import com.thinkconstructive.book_store.entity.UserInfo;
import com.thinkconstructive.book_store.mapper.UserInfoMapper;
import com.thinkconstructive.book_store.repository.UserInfoRepository;
import com.thinkconstructive.book_store.service.JWTService;
import com.thinkconstructive.book_store.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;
    @Override
    public UserInfoDto createUser(UserInfoDto userInfoDto) {
        UserInfo userInfo = UserInfoMapper.toUserInfo(userInfoDto);

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));


        return UserInfoMapper.toUserInfoDto(userInfoRepository.save(userInfo));
    }

    @Override
    public String getUserInfo(UserInfoDto userInfoDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userInfoDto.userName(),userInfoDto.password()
                )
        );
        if (authentication.isAuthenticated())
             return jwtService.generateToken(userInfoDto.userName());
        else
            return "Failure";
    }

}
