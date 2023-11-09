package br.com.gateway.buy.product.infrastructure;

import br.com.gateway.buy.product.common.NotFoundException;
import br.com.gateway.buy.product.config.MessageResourceConfig;
import br.com.gateway.buy.product.entity.BeneficiaryEntity;
import br.com.gateway.buy.product.entity.DocumentEntity;
import br.com.gateway.buy.product.exchange.EkanBeneficiaryExchange;
import br.com.gateway.buy.product.generate.PaginationSort;
import br.com.gateway.buy.product.repository.BeneficiaryRepository;
import br.com.gateway.buy.product.repository.DocumentaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Slf4j
@Repository
@RequiredArgsConstructor
public class EkanBeneficiaryExchangeImpl implements EkanBeneficiaryExchange {

    private final BeneficiaryRepository beneficiaryRepository;
    private final DocumentaryRepository documentaryRepository;
    private final MessageResourceConfig messageResourceConfig;

    public void createBeneficiary(Exchange exchange) {
        Object[] object = (Object[]) exchange.getIn().getBody();
        BeneficiaryEntity beneficiaryEntity = (BeneficiaryEntity) object[0];
        String user = (String) object[1];
        beneficiaryEntity.setNmCreated(user);

        BeneficiaryEntity beneficiary = beneficiaryRepository.save(beneficiaryEntity);

        beneficiaryEntity.getListDocuments().forEach(documentEntity -> {
            documentEntity.setBeneficiaryEntity(beneficiary);
            documentaryRepository.save(documentEntity);
        });
    }

    public void removeByCoSeqBeneficiary(Exchange exchange) {
        Integer coSeqBeneficiary = (Integer) exchange.getIn().getBody();

        BeneficiaryEntity beneficiary = beneficiaryRepository.findById(coSeqBeneficiary.longValue()).orElseThrow(() ->
                new NotFoundException(messageResourceConfig.getMessage("general.notfound", "nmAddressZipCode", coSeqBeneficiary)));

        beneficiary.getListDocuments().forEach(documentaryRepository::delete);
        beneficiaryRepository.delete(beneficiary);
    }

    public void alterBeneficiary(Exchange exchange) {
        Object[] object = (Object[]) exchange.getIn().getBody();
        BeneficiaryEntity beneficiaryEntity = (BeneficiaryEntity) object[0];
        String user = (String) object[1];

        BeneficiaryEntity beneficiary = beneficiaryRepository.findById(beneficiaryEntity.getCoSeqBeneficiary()).orElseThrow(() ->
                new NotFoundException(messageResourceConfig.getMessage("general.notfound", "nmAddressZipCode", beneficiaryEntity.getCoSeqBeneficiary())));

        List<DocumentEntity> list = new ArrayList<>();
        beneficiaryEntity.getListDocuments().forEach(doc -> {
            DocumentEntity document = documentaryRepository.findById(doc.getCoSeqDocumentary()).orElseThrow(() ->
                    new NotFoundException(messageResourceConfig.getMessage("general.notfound", "nmAddressZipCode", doc.getCoSeqDocumentary())));

            document.setTpDocument(doc.getTpDocument());
            document.setNmDescription(doc.getNmDescription());
            document.setNmEdited(user);

            list.add(documentaryRepository.save(document));
        });

            beneficiary.setNmName(beneficiaryEntity.getNmName());
            beneficiary.setDdBirthDate(beneficiaryEntity.getDdBirthDate());
            beneficiary.setNmTelephone(beneficiaryEntity.getNmTelephone());
            beneficiary.setNmEdited(user);

            beneficiary.setListDocuments(list);
            beneficiaryRepository.save(beneficiary);
    }

    @Override
    public BeneficiaryEntity listBeneficiaryByCoSeqBeneficiary(Exchange exchange) {
        Integer coSeqBeneficiary = (Integer) exchange.getIn().getBody();

        return beneficiaryRepository.findById(coSeqBeneficiary.longValue()).orElseThrow(() ->
                new NotFoundException(messageResourceConfig.getMessage("general.notfound", "nmAddressZipCode", coSeqBeneficiary)));
    }

    @Override
    public Page<BeneficiaryEntity> listBeneficiary(Exchange exchange) {
        Object[] object = (Object[]) exchange.getIn().getBody();
        Integer pageNo = (Integer) object[0];
        Integer pageSize = (Integer) object[1];
        List<String> sortBy = (List<String>) object[2];

        List<Sort.Order> sort = PaginationSort.getOrders(sortBy);
        Map<String, String> orderMap = new HashMap<>();
        orderMap.put("nmName", "nmName");

        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sort));
        Pageable pg = PaginationSort.ajustarSort(paging, orderMap);

         return beneficiaryRepository.findBeneficiaryAll(pg);
    }

}