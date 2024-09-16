package com.solidos.caia.api.auth;

import java.util.List;
import java.util.ArrayList;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.solidos.caia.api.auth.dto.AuthResponse;
import com.solidos.caia.api.auth.enums.RolesEnum;
import com.solidos.caia.api.common.utils.JwtHelper;
import com.solidos.caia.api.users.entities.UserEntity;
import com.solidos.caia.api.users.repositories.UserRepository;

@Service
public class AuthService implements UserDetailsService {
  private PasswordEncoder passwordEncoder;
  private UserRepository userRepository;
  private JwtHelper jwtHelper;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtHelper = jwtHelper;
  }

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    if (!user.getIsEnabled()) {
      throw new UsernameNotFoundException("User not found");
    }

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    authorities.add(new SimpleGrantedAuthority("ROLE_" + RolesEnum.AUTHOR.name()));

    authorities.add(new SimpleGrantedAuthority("ROLE_" + RolesEnum.ORGANIZER.name()));

    // authorities.add(new SimpleGrantedAuthority(AuthoritiesEnum.CREATE.name()));

    return new User(
        user.getEmail(),
        user.getPassword(),
        user.getIsEnabled(),
        user.getAccountNoExpired(),
        user.getCredentialsNoExpired(),
        user.getAccountNoLocked(),
        authorities);
  }

  public AuthResponse login(String email, String password) {
    Authentication authentication = authenticate(email, password);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtHelper.createToken(authentication);

    return AuthResponse.builder()
        .email(email)
        .jwt(jwt)
        .message("Login Successful")
        .status(true)
        .build();
  }

  public Authentication authenticate(String email, String password) {
    UserDetails userDetails = loadUserByUsername(email);

    if (userDetails == null) {
      throw new BadCredentialsException("Invalid Credentials");
    }

    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid Credentials");
    }

    return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
  }
}
