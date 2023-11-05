package br.com.gateway.buy.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeDocumentEnum {

    RG(1, "Rg"),
    CPF(2, "Cpf");

    private final Integer codigo;
    private final String descricao;

}
