package com.incloud.hcp.service._gproveedor.impl;

import com.incloud.hcp.domain._gproveedor.domain.TipoProveedor;
import com.incloud.hcp.repository._gproveedor.TipoProveedorRepository;
import com.incloud.hcp.service._gproveedor.TipoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoProveedorServiceImpl implements TipoProveedorService {

    private TipoProveedorRepository tipoProveedorRepository;

    @Autowired
    public void setTipoProveedorRepository(TipoProveedorRepository tipoProveedorRepository) {
        this.tipoProveedorRepository = tipoProveedorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoProveedor> getListAll() {
        return tipoProveedorRepository.findAll();
    }
}
