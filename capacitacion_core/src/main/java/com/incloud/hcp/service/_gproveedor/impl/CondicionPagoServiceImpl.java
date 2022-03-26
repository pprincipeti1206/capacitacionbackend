package com.incloud.hcp.service._gproveedor.impl;

import com.incloud.hcp.domain._gproveedor.domain.CondicionPago;
import com.incloud.hcp.repository._gproveedor.CondicionPagoReposity;
import com.incloud.hcp.service._gproveedor.CondicionPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CondicionPagoServiceImpl implements CondicionPagoService {

    private CondicionPagoReposity condicionPagoReposity;

    @Autowired
    public void setCondicionPagoReposity(CondicionPagoReposity condicionPagoReposity) {
        this.condicionPagoReposity = condicionPagoReposity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CondicionPago> getListAll() {
        return condicionPagoReposity.findAll();
    }
}
