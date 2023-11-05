package br.com.gateway.buy.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EkanBeneficiaryRouterEnum {

    ROUTE_LIST_EKAN("direct:listBeneficiary"),
    ROUTE_ALTER_EKAN("direct:alterBeneficiary"),
    ROUTE_CREATE_EKAN("direct:createBeneficiary"),
    ROUTE_REMOVE_EKAN("direct:removeByCoSeqBeneficiary"),
    ROUTE_LIST__EKAN_BY_CODSEQBENEFICIARY("direct:listBeneficiaryByCoSeqBeneficiary");

    private final String name;
}
