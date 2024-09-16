package com.solidos.caia.api.users;

import java.util.Optional;
import java.util.List;

import org.apache.logging.log4j.util.InternalException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.api.common.utils.TokenGenerator;
import com.solidos.caia.api.users.adapters.UserEntityAdapter;
import com.solidos.caia.api.users.dto.CreateUserDto;
import com.solidos.caia.api.users.dto.UserResumeDto;
import com.solidos.caia.api.users.entities.UserEntity;
import com.solidos.caia.api.users.entities.UserEntity.UserEntityBuilder;
import com.solidos.caia.api.users.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Creates a new user.
   *
   * @param createUserDto the user to be created, without the id.
   * @throws ResponseStatusException if the user already exists.
   * @throws InternalException       if there is an error creating the user.
   */
  public void createUser(CreateUserDto createUserDto) {
    Optional<UserEntity> existsUser = userRepository.findByEmail(createUserDto.getEmail());

    if (existsUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    }

    UserEntityBuilder userEntityBuilder = UserEntity.builder();

    UserEntity userEntity = userEntityBuilder
        .firstName(createUserDto.getFirstName())
        .lastName(createUserDto.getLastName())
        .email(createUserDto.getEmail())
        .afiliation(createUserDto.getAfiliation())
        .password(passwordEncoder.encode(createUserDto.getPassword()))
        .token(TokenGenerator.generate())
        .build();

    if (userEntity == null) {
      throw new InternalException("UserEntity cannot be null after being built");
    }
    // TODO: Send Email

    try {
      userRepository.save(userEntity);
    } catch (Exception e) {
      throw new InternalException("Error creating user", e);
    }
  }

  /**
   * Confirms a user.
   *
   * @param token the user's confirmation token.
   * @throws ResponseStatusException if the user does not exist, or if the user is
   *                                 already confirmed.
   * @throws InternalException       if there is an error confirming the user.
   */
  public void confirm(String token) {
    Optional<UserEntity> user = userRepository.findByToken(token);

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    UserEntity userEntity = user.get();

    if (userEntity.getIsEnabled()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already confirmed");
    }

    userEntity.setToken(null);
    userEntity.setAccountNoExpired(true);
    userEntity.setAccountNoLocked(true);
    userEntity.setCredentialsNoExpired(true);
    userEntity.setIsEnabled(true);

    try {
      userRepository.save(userEntity);
    } catch (Exception e) {
      throw new InternalException("Error confirming user", e);
    }
  }

  public List<UserResumeDto> findByQuery(String query) {
    Pageable pageable = PageRequest.of(0, 10);
    if (query == null || query.length() < 3) {
      return userRepository.findAll().stream().map(u -> UserEntityAdapter.userEntityToUserResume(u)).toList();
    }

    return userRepository
        .findByQuery(query, pageable)
        .stream()
        .map(u -> UserEntityAdapter.userEntityToUserResume(u)).toList();
  }
}
