package com.incloud.hcp.service._gproveedor.facade.impl;

import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.Banco;
import com.incloud.hcp.service._gproveedor.BancoService;
import com.incloud.hcp.service._gproveedor.dto.BancoDto;
import com.incloud.hcp.service._gproveedor.facade.BancoFacade;
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
public class BancoFacadeImpl implements BancoFacade {

    private BancoService bancoService;
    private Populater<Banco, BancoDto> populater;

    @Autowired
    public void setBancoService(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @Autowired
    @Qualifier(value = "bancoPopulate")
    public void setPopulater(Populater<Banco, BancoDto> populater) {
        this.populater = populater;
    }

    @Override
    public List<BancoDto> getListAll() {
        List<BancoDto> listDto = new ArrayList<>();

        Optional.ofNullable(bancoService).map(BancoService::getListAll)
                .ifPresent(list -> list.stream().map(populater::toDto).forEach(listDto::add));

        return listDto;
    }
}
