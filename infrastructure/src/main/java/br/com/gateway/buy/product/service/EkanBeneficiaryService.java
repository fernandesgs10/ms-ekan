package br.com.gateway.buy.product.service;

import br.com.gateway.buy.product.common.MuvzException;
import br.com.gateway.buy.product.common.NotFoundException;
import br.com.gateway.buy.product.entity.BeneficiaryEntity;
import br.com.gateway.buy.product.enums.EkanBeneficiaryRouterEnum;
import br.com.gateway.buy.product.router.EkanBeneficiaryRouter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class EkanBeneficiaryService {

    private final EkanBeneficiaryRouter ekanBeneficiaryRouter;

    public void createBeneficiary(BeneficiaryEntity beneficiaryEntity, String user) {
        Object[] obj = {beneficiaryEntity, user};

        try (CamelContext ctx = new DefaultCamelContext()) {
            ctx.addRoutes(ekanBeneficiaryRouter.createBeneficiary());
            ctx.start();
            try (ProducerTemplate producerTemplate = ctx.createProducerTemplate()) {
                producerTemplate.
                        requestBody(EkanBeneficiaryRouterEnum.ROUTE_CREATE_EKAN.getName(), obj);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            if (ex.getCause() != null) {
                Throwable cause = ex.getCause();
                if (cause instanceof HttpClientErrorException.BadRequest) {
                    throw (HttpClientErrorException.BadRequest) cause;
                }
            }

            throw new MuvzException(ex.getMessage());
        }
    }

    public void removeByCoSeqBeneficiary(Integer coSeqBeneficiary) {
        try (CamelContext ctx = new DefaultCamelContext()) {
            ctx.addRoutes(ekanBeneficiaryRouter.removeBeneficiary());
            ctx.start();
            try (ProducerTemplate producerTemplate = ctx.createProducerTemplate()) {
                producerTemplate.
                        requestBody(EkanBeneficiaryRouterEnum.ROUTE_REMOVE_EKAN.getName(), coSeqBeneficiary);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            if (ex.getCause() != null) {
                Throwable cause = ex.getCause();
                if (cause instanceof HttpClientErrorException.BadRequest) {
                    throw (HttpClientErrorException.BadRequest) cause;
                }
                if (cause instanceof NotFoundException) {
                    throw (NotFoundException) cause;
                }
            }

            throw new MuvzException(ex.getMessage());
        }
    }

    public void alterBeneficiary(BeneficiaryEntity beneficiaryEntity, String user) {
        Object[] obj = {beneficiaryEntity, user};
        try (CamelContext ctx = new DefaultCamelContext()) {
            ctx.addRoutes(ekanBeneficiaryRouter.alterBeneficiary());
            ctx.start();
            try (ProducerTemplate producerTemplate = ctx.createProducerTemplate()) {
                producerTemplate.
                        requestBody(EkanBeneficiaryRouterEnum.ROUTE_ALTER_EKAN.getName(), obj);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            if (ex.getCause() != null) {
                Throwable cause = ex.getCause();
                if (cause instanceof HttpClientErrorException.BadRequest) {
                    throw (HttpClientErrorException.BadRequest) cause;
                }
                if (cause instanceof NotFoundException) {
                    throw (NotFoundException) cause;
                }
            }

            throw new MuvzException(ex.getMessage());
        }
    }

    public BeneficiaryEntity listBeneficiaryByCoSeqBeneficiary(Integer coSeqBeneficiary) {
        try (CamelContext ctx = new DefaultCamelContext()) {
            ctx.addRoutes(ekanBeneficiaryRouter.listBeneficiaryByCoSeqBeneficiary());
            ctx.start();
            try (ProducerTemplate producerTemplate = ctx.createProducerTemplate()) {
                return producerTemplate.
                        requestBody(EkanBeneficiaryRouterEnum.ROUTE_LIST__EKAN_BY_CODSEQBENEFICIARY.getName(), coSeqBeneficiary, BeneficiaryEntity.class);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            if (ex.getCause() != null) {
                Throwable cause = ex.getCause();
                if (cause instanceof HttpClientErrorException.BadRequest) {
                    throw (HttpClientErrorException.BadRequest) cause;
                }
                if (cause instanceof NotFoundException) {
                    throw (NotFoundException) cause;
                }
            }

            throw new MuvzException(ex.getMessage());
        }
    }

    public Page<BeneficiaryEntity> listBeneficiary(
                                 Integer pageNo,
                                 Integer pageSize,
                                 List<String> sortBy) {

        Object[] object = {pageNo, pageSize, sortBy};

        try (CamelContext ctx = new DefaultCamelContext()) {
            ctx.addRoutes(ekanBeneficiaryRouter.listBeneficiary());
            ctx.start();
            try (ProducerTemplate producerTemplate = ctx.createProducerTemplate()) {
                return producerTemplate.
                        requestBody(EkanBeneficiaryRouterEnum.ROUTE_LIST_EKAN.getName(), object, Page.class);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            if (ex.getCause() != null) {
                Throwable cause = ex.getCause();
                if (cause instanceof HttpClientErrorException.BadRequest) {
                    throw (HttpClientErrorException.BadRequest) cause;
                }
            }

            throw new MuvzException(ex.getMessage());
        }
    }
}