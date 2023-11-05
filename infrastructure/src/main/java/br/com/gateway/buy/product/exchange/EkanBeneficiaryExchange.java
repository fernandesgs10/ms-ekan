
package br.com.gateway.buy.product.exchange;

import br.com.gateway.buy.product.entity.BeneficiaryEntity;
import org.apache.camel.Exchange;
import org.springframework.data.domain.Page;

public interface EkanBeneficiaryExchange {

	void alterBeneficiary(Exchange exchange);

	void removeByCoSeqBeneficiary(Exchange exchange);

	void createBeneficiary(Exchange exchange);

	Page<BeneficiaryEntity> listBeneficiary(Exchange exchange);

	BeneficiaryEntity listBeneficiaryByCoSeqBeneficiary(Exchange exchange);

}
