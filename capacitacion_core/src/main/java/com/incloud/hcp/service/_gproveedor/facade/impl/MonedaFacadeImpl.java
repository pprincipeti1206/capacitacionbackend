package com.incloud.hcp.service._gproveedor.facade.impl;


import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.Moneda;
import com.incloud.hcp.service._gproveedor.MonedaService;
import com.incloud.hcp.service._gproveedor.dto.MonedaDto;
import com.incloud.hcp.service._gproveedor.facade.MonedaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MonedaFacadeImpl implements MonedaFacade {

    private MonedaService monedaService;
    private Populater<Moneda, MonedaDto> populater;

    @Autowired
    public void setMonedaService(MonedaService monedaService) {
        this.monedaService = monedaService;
    }

    @Autowired
    @Qualifier(value = "monedaPopulate")
    public void setPopulater(Populater<Moneda, MonedaDto> populater) {
        this.populater = populater;
    }

    @Override
    public List<MonedaDto> getListAll() {
        List<MonedaDto> listDto = new ArrayList<>();
        Optional.ofNullable(monedaService).map(MonedaService::getListAll)
                .ifPresent(list -> list.stream().map(populater::toDto).forEach(listDto::add));

        return listDto;
    }
}
