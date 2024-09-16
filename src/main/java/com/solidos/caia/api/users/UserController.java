package com.solidos.caia.api.users;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.api.common.models.CommonResponse;
import com.solidos.caia.api.users.dto.CreateUserDto;
import com.solidos.caia.api.users.dto.UserResumeDto;

import jakarta.annotation.Nullable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
@PreAuthorize("permitAll()")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Creates a new user.
   *
   * @param createUserDto the user's data. See {@link CreateUserDto}.
   * @return a response with a success message.
   */
  @PostMapping
  public ResponseEntity<CommonResponse<Void>> postMethodName(@RequestBody @Validated CreateUserDto createUserDto) {

    userService.createUser(createUserDto);

    CommonResponse<Void> commonResponse = CommonResponse.<Void>builder()
        .status(HttpStatus.CREATED.value())
        .message("User created successfully")
        .build();

    return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
  }

  /**
   * Confirms a user.
   *
   * @param token the user's confirmation token.
   * @return a response with a success message.
   */
  @PostMapping("/confirm")
  public ResponseEntity<CommonResponse<Void>> confirm(@RequestParam String token) {
    userService.confirm(token);

    CommonResponse<Void> commonResponse = CommonResponse.<Void>builder()
        .status(HttpStatus.CREATED.value())
        .message("User confirmed  successfully")
        .build();

    return ResponseEntity.ok(commonResponse);
  }

  @GetMapping()
  public ResponseEntity<CommonResponse<List<UserResumeDto>>> getUsers(@RequestParam @Nullable String query) {
    List<UserResumeDto> users = userService.findByQuery(query);

    var commonResponse = CommonResponse.<List<UserResumeDto>>builder()
        .status(HttpStatus.OK.value())
        .message("Users found" + query)
        .data(users)
        .build();

    return ResponseEntity.ok(commonResponse);
  }
}
