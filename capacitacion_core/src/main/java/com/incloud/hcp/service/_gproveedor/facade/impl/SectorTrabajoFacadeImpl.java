package com.incloud.hcp.service._gproveedor.facade.impl;


import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.SectorTrabajo;
import com.incloud.hcp.service._gproveedor.SectorTrabajoService;
import com.incloud.hcp.service._gproveedor.dto.SectorTrabajoDto;
import com.incloud.hcp.service._gproveedor.facade.SectorTrabajoFacade;
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
public class SectorTrabajoFacadeImpl implements SectorTrabajoFacade {

    private SectorTrabajoService sectorTrabajoService;
    private Populater<SectorTrabajo, SectorTrabajoDto> populater;

    @Autowired
    public void setSectorTrabajoService(SectorTrabajoService sectorTrabajoService) {
        this.sectorTrabajoService = sectorTrabajoService;
    }

    @Autowired
    @Qualifier(value = "sectorTrabajoPopulate")
    public void setPopulater(Populater<SectorTrabajo, SectorTrabajoDto> populater) {
        this.populater = populater;
    }

    @Override
    public List<SectorTrabajoDto> getListAll() {
        List<SectorTrabajoDto> listDto = new ArrayList<>();
        Optional.ofNullable(sectorTrabajoService).map(SectorTrabajoService::getListAll)
                .ifPresent(list -> list.stream().map(this.populater::toDto).forEach(listDto::add));

        return listDto;
    }
}
