/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package com.incloud.hcp.repository._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.ProveedorPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProveedorPermisoRepository extends JpaRepository<ProveedorPermiso, Integer> {

    @Modifying
    @Query("DELETE FROM ProveedorPermiso p WHERE p.idProveedor.idProveedor= ?1")
    void deletePermisoByIdProveedor(Integer idProveedor);

    @Query("SELECT p FROM ProveedorPermiso p WHERE p.idProveedor.idProveedor = ?1")
    List<ProveedorPermiso> getListaByIdProveedor(Integer idProveedor);
}