package com.incloud.hcp.service._gproveedor.facade.impl;

import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.TipoProveedor;
import com.incloud.hcp.service._gproveedor.TipoProveedorService;
import com.incloud.hcp.service._gproveedor.dto.TipoProveedorDto;
import com.incloud.hcp.service._gproveedor.facade.TipoProveedorFacade;
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
public class TipoProveedorFacadeImpl implements TipoProveedorFacade {

    private TipoProveedorService tipoProveedorService;
    private Populater<TipoProveedor, TipoProveedorDto> tipoProveedorPopulater;

    @Autowired
    public void setTipoProveedorService(TipoProveedorService tipoProveedorService) {
        this.tipoProveedorService = tipoProveedorService;
    }

    @Autowired
    @Qualifier(value = "tipoProveedorPopulate")
    public void setTipoProveedorPopulater(Populater tipoProveedorPopulater) {
        this.tipoProveedorPopulater = tipoProveedorPopulater;
    }

    @Override
    public List<TipoProveedorDto> getListAll() {
        List<TipoProveedorDto> listDto = new ArrayList<>();

        Optional.ofNullable(tipoProveedorService.getListAll())
                .ifPresent(list -> list.stream().map(tipoProveedorPopulater::toDto).forEach(listDto::add));

        return listDto;
    }
}
