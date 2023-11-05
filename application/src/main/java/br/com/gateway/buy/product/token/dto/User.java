package br.com.gateway.buy.product.token.dto;

import java.util.List;

public record User(String username, String password, List<String> roles) {
}