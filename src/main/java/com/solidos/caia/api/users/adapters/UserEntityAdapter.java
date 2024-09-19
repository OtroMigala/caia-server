package com.solidos.caia.api.users.adapters;

import com.solidos.caia.api.users.dto.UserSummaryDto;
import com.solidos.caia.api.users.entities.UserEntity;

public class UserEntityAdapter {
  public static UserSummaryDto userEntityToUserResume(UserEntity userEntity) {
    return UserSummaryDto.builder()
        .id(userEntity.getId())
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .email(userEntity.getEmail())
        .build();
  }
}
