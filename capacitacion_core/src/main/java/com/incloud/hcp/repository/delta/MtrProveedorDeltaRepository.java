/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/repository/EntitydeltaRepository.java.e.vm
 */
package com.incloud.hcp.repository.delta;

import com.incloud.hcp.domain.MtrProveedor;
import com.incloud.hcp.repository.MtrProveedorRepository;

public interface MtrProveedorDeltaRepository extends MtrProveedorRepository {

    MtrProveedor findByCodigoIdp(String codigoIdp);

    MtrProveedor findByRuc(String ruc);
}
