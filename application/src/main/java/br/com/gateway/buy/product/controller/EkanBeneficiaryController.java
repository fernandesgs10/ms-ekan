package br.com.gateway.buy.product.controller;

import br.com.gateway.buy.product.config.MessageResourceConfig;
import br.com.gateway.buy.product.entity.BeneficiaryEntity;
import br.com.gateway.buy.product.generate.PageGeneric;
import br.com.gateway.buy.product.mapper.EkanBeneficiaryMapper;
import br.com.gateway.buy.product.service.EkanBeneficiaryService;
import br.com.muvz.tech.ekan.api.Beneficiary;
import br.com.muvz.tech.ekan.api.EkanApi;
import br.com.muvz.tech.ekan.api.Page;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("${openapi.ms-muvz-tech.base-path:/v1/muvz-tech}/ekan")
@SecurityRequirement(name = "in-memory")
public class EkanBeneficiaryController extends PageGeneric implements EkanApi {

    private final EkanBeneficiaryMapper ekanBeneficiaryMapper;
    private final EkanBeneficiaryService ekanBeneficiaryService;
    private final MessageResourceConfig messageResourceConfig;

    @Override
    @PostMapping("/beneficiary/createBeneficiary")
    public ResponseEntity<Void> createBeneficiary(
            @ApiParam(value = "Optional description in new beneficiary" ,required=true )
            @Valid @RequestBody Beneficiary beneficiary) {
        log.info("class=EkanController method=createBeneficiary step=beneficiary={}", beneficiary);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        BeneficiaryEntity beneficiaryEntity =
                ekanBeneficiaryMapper.converterObjectTBeneficiaryEntity(beneficiary);

        ekanBeneficiaryService.createBeneficiary(beneficiaryEntity, user.getUsername());

        log.info("class=EkanController method=createBeneficiary step=end response");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @DeleteMapping("/beneficiary/remove/coSeqBeneficiary/{coSeqBeneficiary}")
    public ResponseEntity<Void> removeByCoSeqBeneficiary(
            @ApiParam(value = "remove by remove",required=true)
            @PathVariable("coSeqBeneficiary") Integer coSeqBeneficiary) {

        log.info("class=EkanController method=removeByCoSeqBeneficiary step=coSeqBeneficiary={}", coSeqBeneficiary);

        ekanBeneficiaryService.removeByCoSeqBeneficiary(coSeqBeneficiary);

        log.info("class=EkanController method=listBeneficiaryByCoSeqBeneficiary step=end response");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @PutMapping("/beneficiary/alter-beneficiary")
    public ResponseEntity<Void> alterBeneficiary(
            @ApiParam(value = "Optional description in update beneficiary", required=true)
            @Valid @RequestBody Beneficiary beneficiary) {

        log.info("class=EkanController method=alterBeneficiary step=beneficiary={}", beneficiary);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        BeneficiaryEntity beneficiaryEntity =
                ekanBeneficiaryMapper.converterObjectTBeneficiaryEntity(beneficiary);

        ekanBeneficiaryService.alterBeneficiary(beneficiaryEntity, user.getUsername());

        log.info("class=EkanController method=listBeneficiaryByCoSeqBeneficiary step=end response");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @GetMapping("/beneficiary/coSeqBeneficiary/{coSeqBeneficiary}")
    public ResponseEntity listBeneficiaryByCoSeqBeneficiary(
            @ApiParam(value = "coSeqBeneficiary beneficiary",required=true)
            @PathVariable("coSeqBeneficiary") Integer coSeqBeneficiary) {

        log.info("class=EkanController method=listBeneficiaryByCoSeqBeneficiary step=coSeqBeneficiary={}",
                coSeqBeneficiary);

        BeneficiaryEntity beneficiaryReturn = ekanBeneficiaryService.listBeneficiaryByCoSeqBeneficiary(coSeqBeneficiary);
        Beneficiary beneficiary = ekanBeneficiaryMapper.converterObjectTBeneficiary(beneficiaryReturn);

        log.info("class=EkanController method=listBeneficiaryByCoSeqBeneficiary step=end response{}", beneficiary);
        return ResponseEntity.status(HttpStatus.OK).body(beneficiary);
    }

    @Override
    @GetMapping("/beneficiary")
    public ResponseEntity<Page> listBeneficiary(
            @ApiParam(value = "The number of items to skip before starting to collect the result set.", defaultValue = "0")
            @Valid @RequestParam(value = "pageNo", required = false, defaultValue="0") Integer pageNo,
            @ApiParam(value = "The number of items to return.", defaultValue = "20")
            @Valid @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize,
            @Valid @RequestParam(value = "sortBy", required = false) List<String> sortBy) {

        log.info("class=EkanController method=listBeneficiary step=pageNo={}, pageSize={}, sortBy={}",
                pageNo, pageSize, sortBy);

        org.springframework.data.domain.Page<BeneficiaryEntity> page = ekanBeneficiaryService.listBeneficiary(pageNo, pageSize, sortBy);

        org.springframework.data.domain.Page<Beneficiary> pageBeneficiaryDto = convertPage(page,
                ekanBeneficiaryMapper::converterObjectTBeneficiary);

        br.com.muvz.tech.ekan.api.Page pageConvert =
                ekanBeneficiaryMapper.converterToPageBeneficiary(pageBeneficiaryDto);

        pageConvert.content(Collections.singletonList(pageBeneficiaryDto.getContent()));

        log.info("class=EkanController method=listBeneficiary step=end response{}", pageConvert);
        return ResponseEntity.status(HttpStatus.OK).body(pageConvert);
    }
}


