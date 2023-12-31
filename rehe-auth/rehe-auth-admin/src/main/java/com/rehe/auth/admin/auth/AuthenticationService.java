package com.rehe.auth.admin.auth;

import com.rehe.auth.admin.config.JwtService;
import com.rehe.auth.admin.mapper.UserTestMapper;
import com.rehe.auth.admin.provider.mobile.MobileAuthenticationToken;
import com.rehe.auth.admin.provider.openid.OpenIdAuthenticationToken;
import com.rehe.auth.admin.test.mapper.AppUserMapper;
import com.rehe.auth.admin.user.User;
import com.rehe.common.db.DBSource;
import com.rehe.common.db.DynamicDataSourceEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UserTestMapper userTestMapper;
    private final AppUserMapper appUserMapper;
    private final AuthenticationServiceTest authenticationServiceTest;

//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(request.getRole())
//                .build();
//        var savedUser = repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        saveUserToken(savedUser, jwtToken);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }

    public String authenticate() {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        "test",
                        "1231233"
                )
        );
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
        String jwtToken = jwtService.generateToken(new User());
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        return jwtToken;
    }
    public String authenticate1() {
        authenticationManager.authenticate(
                new OpenIdAuthenticationToken(
                        "test"
                )
        );
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
        String jwtToken = jwtService.generateToken(new User());
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        return jwtToken;
    }

    public String authenticate2() {
        authenticationManager.authenticate(
                new MobileAuthenticationToken(
                        "test","sd"
                )
        );
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
        String jwtToken = jwtService.generateToken(new User());
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        return jwtToken;
    }

//    private void saveUserToken(User user, String jwtToken) {
//        var token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .expired(false)
//                .revoked(false)
//                .build();
//        tokenRepository.save(token);
//    }

//    private void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }

//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            var user = this.repository.findByEmail(userEmail)
//                    .orElseThrow();
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
//                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
//                var authResponse = AuthenticationResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }

    @DBSource(value = DynamicDataSourceEnum.SLAVE)
    public void test1(){
        System.out.println("========");
        List s1 = userTestMapper.test();
        System.out.println(s1.get(0).toString());

        List s2 = appUserMapper.test();
        System.out.println(s2.get(0).toString());
        authenticationServiceTest.test2();
    }
//    @DBSource(value = DynamicDataSourceEnum.SLAVE)
//    public void test2(){
//        System.out.println("3333333");
//        List s2 = appUserMapper.test();
//        System.out.println(s2.get(0).toString());
//    }
}
