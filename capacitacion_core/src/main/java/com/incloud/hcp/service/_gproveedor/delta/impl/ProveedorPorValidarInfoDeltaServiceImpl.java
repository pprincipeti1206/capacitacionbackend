/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/dto/EntitydeltaDTOService.java.e.vm
 */
package com.incloud.hcp.service._gproveedor.delta.impl;

import com.incloud.hcp.domain._gproveedor.domain.ProveedorPorValidarInfo;
import com.incloud.hcp.service._gproveedor.delta.ProveedorPorValidarInfoDeltaService;
import com.incloud.hcp.service._gproveedor.impl.ProveedorPorValidarInfoServiceImpl;
import com.incloud.hcp.service.support.PageRequestByExample;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * A simple DTO Facility for ProveedorPorValidarInfo.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProveedorPorValidarInfoDeltaServiceImpl extends ProveedorPorValidarInfoServiceImpl implements ProveedorPorValidarInfoDeltaService {

    /**************************/
    /* Metodos Personalizados */
    /**************************/

    /**************************/
    /* Metodos Generados      */
    /**************************/

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected Sort setFind(ProveedorPorValidarInfo req, ExampleMatcher matcher, Example<ProveedorPorValidarInfo> example, Sort sort) {
        return sort;
    }

    protected void setFindPaginated(PageRequestByExample<ProveedorPorValidarInfo> req, ExampleMatcher matcher, Example<ProveedorPorValidarInfo> example) {
        return;
    }

    protected void setCreate(ProveedorPorValidarInfo dto) {

    }

    protected void setSave(ProveedorPorValidarInfo dto) {

    }

    protected void setDelete(Integer id) {

    }

    protected void setDeleteAll() {

    }

}