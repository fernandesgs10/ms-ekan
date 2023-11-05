package br.com.gateway.buy.product.router;

import br.com.gateway.buy.product.enums.EkanBeneficiaryRouterEnum;
import br.com.gateway.buy.product.exchange.EkanBeneficiaryExchange;
import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EkanBeneficiaryRouter {

    private final EkanBeneficiaryExchange ekanExchange;


    public RouteBuilder createBeneficiary() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from(EkanBeneficiaryRouterEnum.ROUTE_CREATE_EKAN.getName())
                        .bean(ekanExchange, "createBeneficiary");
            }
        };
    }

    public RouteBuilder removeBeneficiary() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from(EkanBeneficiaryRouterEnum.ROUTE_REMOVE_EKAN.getName())
                        .bean(ekanExchange, "removeByCoSeqBeneficiary");
            }
        };
    }

    public RouteBuilder alterBeneficiary() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from(EkanBeneficiaryRouterEnum.ROUTE_ALTER_EKAN.getName())
                        .bean(ekanExchange, "alterBeneficiary");
            }
        };
    }

    public RouteBuilder listBeneficiary() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from(EkanBeneficiaryRouterEnum.ROUTE_LIST_EKAN.getName())
                        .bean(ekanExchange, "listBeneficiary");
            }
        };
    }

    public RouteBuilder listBeneficiaryByCoSeqBeneficiary() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from(EkanBeneficiaryRouterEnum.ROUTE_LIST__EKAN_BY_CODSEQBENEFICIARY.getName())
                        .bean(ekanExchange, "listBeneficiaryByCoSeqBeneficiary");
            }
        };
    }
}