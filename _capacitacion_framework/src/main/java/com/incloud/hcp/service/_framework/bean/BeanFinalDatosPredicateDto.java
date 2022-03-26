package com.incloud.hcp.service._framework.bean;

import com.incloud.hcp.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class BeanFinalDatosPredicateDto<T extends BaseDomain> {

    private long total;
    private List<T> listaFinal;

}
