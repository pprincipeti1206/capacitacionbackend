package com.incloud.hcp.service._gproveedor.impl;


import com.incloud.hcp.domain._gproveedor.domain.Banco;
import com.incloud.hcp.repository._gproveedor.BancoRepository;
import com.incloud.hcp.service._gproveedor.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BancoServiceImpl implements BancoService {

    private BancoRepository bancoRepository;

    @Autowired
    public void setBancoRepository(BancoRepository bancoRepository) {
        this.bancoRepository = bancoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Banco> getListAll() {
        return this.bancoRepository.findAll();
    }
}
