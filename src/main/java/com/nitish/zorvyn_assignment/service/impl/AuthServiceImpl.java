package com.nitish.zorvyn_assignment.service.impl;

import com.nitish.zorvyn_assignment.dto.request.UserRegisterRequest;
import com.nitish.zorvyn_assignment.entity.User;
import com.nitish.zorvyn_assignment.repository.UserRepository;
import com.nitish.zorvyn_assignment.security.CustomUserDetails;
import com.nitish.zorvyn_assignment.service.AuthService;
import com.nitish.zorvyn_assignment.util.JWTUtil;
import com.nitish.zorvyn_assignment.util.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    @Override
    public void registerUser(UserRegisterRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        user = userRepository.save(user);
        logger.info("User details saved successfully [User id={}, User role={}]", user.getUserId(), user.getRole());
    }

    @Override
    public String login(String username, String password) {
        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authenticationManager.authenticate(authToken);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return jwtUtil.generateToken(userDetails);
    }


}
