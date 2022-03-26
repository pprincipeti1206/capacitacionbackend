package com.incloud.hcp.service._gproveedor.impl;

import com.incloud.hcp.domain._gproveedor.domain.TipoComprobante;
import com.incloud.hcp.repository._gproveedor.TipoComprobanteRepository;
import com.incloud.hcp.service._gproveedor.TipoComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoComprobanteServiceImpl implements TipoComprobanteService {

    private TipoComprobanteRepository tipoComprobanteRepository;

    @Autowired
    public void setTipoComprobanteRepository(TipoComprobanteRepository tipoComprobanteRepository) {
        this.tipoComprobanteRepository = tipoComprobanteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoComprobante> getListAll() {
        return this.tipoComprobanteRepository.findAll();
    }
}
