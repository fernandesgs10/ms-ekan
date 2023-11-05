package br.com.gateway.buy.product.token.controller;


import br.com.gateway.buy.product.config.MessageResourceConfig;
import br.com.gateway.buy.product.token.dto.AuthRequestDto;
import br.com.gateway.buy.product.token.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/muvz-tech/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    @Value("${jwt.user}")
    private String user;

    @Value("${jwt.password}")
    private String password;

    private final MessageResourceConfig messageResourceConfig;

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authRequest(@RequestBody AuthRequestDto authRequestDto) {
        log.info("AuthResource.authRequest start {}", authRequestDto);

        boolean isUser = user.equals(authRequestDto.userName()) && password.equals(authRequestDto.password());
        Preconditions.checkArgument(isUser, messageResourceConfig.getMessage("user.notfound", authRequestDto.userName(), authRequestDto.password()));

        var userRegistrationResponse = authService.authRequest(authRequestDto);
        log.info("AuthResource.authRequest end {}", userRegistrationResponse);
        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.OK);
    }
}
