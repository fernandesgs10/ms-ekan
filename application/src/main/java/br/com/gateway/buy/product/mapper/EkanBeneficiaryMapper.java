package br.com.gateway.buy.product.mapper;

import br.com.gateway.buy.product.entity.BeneficiaryEntity;
import br.com.muvz.tech.ekan.api.Beneficiary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@SuppressWarnings("ClassCanBeRecord")
@Data
@Builder
@Component
@AllArgsConstructor
public class EkanBeneficiaryMapper implements Serializable {

    private final ModelMapper modelMapper;


    public Beneficiary converterObjectTBeneficiary(Object object) {
        return modelMapper.map(object, Beneficiary.class);
    }

    public BeneficiaryEntity converterObjectTBeneficiaryEntity(Object object) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Beneficiary, BeneficiaryEntity>() {
            @Override
            protected void configure() {
                    skip(destination.getDtCreated());
                    skip(destination.getDtEdited());
            }
        });

        return modelMapper.map(object, BeneficiaryEntity.class);

    }

    public br.com.muvz.tech.ekan.api.Page converterToPageBeneficiary(Object pageBeneficiary) {
        return modelMapper.map(pageBeneficiary, br.com.muvz.tech.ekan.api.Page.class);
    }
}