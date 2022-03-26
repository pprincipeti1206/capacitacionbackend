package com.incloud.hcp.service._gproveedor.impl;

import com.incloud.hcp.domain._gproveedor.domain.ProveedorCatalogo;
import com.incloud.hcp.repository._gproveedor.ProveedorCatalogoRepository;
import com.incloud.hcp.service._gproveedor.ProveedorCatalogoService;
import com.incloud.hcp.service._gproveedor.dto.ProveedorCatalogoDto;
import com.incloud.hcp.service._gproveedor.dto.mapper.ProveedorCatalogoDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Administrador on 25/09/2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProveedorCatalogoServiceImpl implements ProveedorCatalogoService {

    @Autowired
    private ProveedorCatalogoRepository proveedorCatalogoRepository;

    @Override
    public ProveedorCatalogo save(ProveedorCatalogo proveedorCatalogo) {
        return proveedorCatalogoRepository.save(proveedorCatalogo);
    }

    @Override
    public List<ProveedorCatalogo> getListCatalogoByIdProveedor(Integer idProveedor) {
        return proveedorCatalogoRepository.getProveedorCatalogoByIdProveedor(idProveedor);
    }

    @Override
    public List<ProveedorCatalogoDto> getListCatalogoDtoByIdProveedor(Integer idProveedor) {
        List<ProveedorCatalogoDto> list = new ArrayList<>();
        ProveedorCatalogoDTOMapper dtoMapper = new ProveedorCatalogoDTOMapper();
        Optional.ofNullable(proveedorCatalogoRepository)
                .map(r -> r.getProveedorCatalogoByIdProveedor(idProveedor))
                .ifPresent(l -> l.stream().map(dtoMapper::toDto).forEach(list::add));
        return list;
    }

    @Override
    public void deleteCatalogoByIdProveedorCatalogoById(Integer idProveedor, String archivoId) {
        proveedorCatalogoRepository.deleteCatalogoByIdProveedorCatalogoById(idProveedor, archivoId);
    }
}
