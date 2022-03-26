/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/dto/EntitydeltaDTO.java.e.vm
 */
package com.incloud.hcp.service.delta;

import com.incloud.hcp.domain.MtrProveedor;
import com.incloud.hcp.service.MtrProveedorService;

import java.io.InputStream;
import java.util.List;

/**
 * Simple Interface for MtrProveedor.
 */
public interface MtrProveedorDeltaService extends MtrProveedorService {

    MtrProveedor devuelveProveedorLogueado() throws Exception;
    List<MtrProveedor> listaCreacionProveedor(InputStream in) throws Exception;

}
