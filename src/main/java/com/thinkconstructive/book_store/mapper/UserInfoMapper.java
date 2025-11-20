package com.thinkconstructive.book_store.mapper;

import com.thinkconstructive.book_store.dto.UserInfoDto;
import com.thinkconstructive.book_store.entity.UserInfo;

public class UserInfoMapper {
    public static UserInfoDto toUserInfoDto(UserInfo userInfo) {
        UserInfoDto userInfoDto = new UserInfoDto(userInfo.getUserName(), userInfo.getPassword(), userInfo.getRole());
        return userInfoDto;

    }
    public static UserInfo toUserInfo(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo(userInfoDto.userName(), userInfoDto.password(), userInfoDto.role());
        return userInfo;
    }


}
