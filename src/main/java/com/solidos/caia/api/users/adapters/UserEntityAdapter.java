package com.solidos.caia.api.users.adapters;

import org.springframework.stereotype.Component;

import com.solidos.caia.api.users.dto.UserResumeDto;
import com.solidos.caia.api.users.entities.UserEntity;

@Component
public class UserEntityAdapter {
  public static UserResumeDto userEntityToUserResume(UserEntity userEntity) {
    return UserResumeDto.builder()
        .id(userEntity.getId())
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .email(userEntity.getEmail())
        .build();
  }
}
