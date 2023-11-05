package br.com.gateway.buy.product.token.service;

import br.com.gateway.buy.product.token.dto.AuthRequestDto;

import java.util.Map;

public interface AuthService {
     Map<String, String> authRequest(AuthRequestDto authRequestDto);

}
