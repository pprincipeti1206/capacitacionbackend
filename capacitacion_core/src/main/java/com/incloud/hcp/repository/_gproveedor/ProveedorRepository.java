package com.incloud.hcp.repository._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.EstadoProveedor;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrador on 29/08/2017.
 */
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    @Query("SELECT p FROM Proveedor p WHERE p.ruc = ?1")
    Proveedor getProveedorByRuc(String ruc);

    @Query("SELECT p FROM Proveedor p WHERE p.idHcp = ?1")
    Proveedor getProveedorByIdHcp(String idHcp);

    @Query("SELECT p FROM Proveedor p WHERE p.ruc = ?1 AND p.password = ?2")
    Proveedor getProveedorByRucAndPassword(String ruc, String password);

    @Query("SELECT p FROM Proveedor p WHERE p.acreedorCodigoSap = ?1")
    Proveedor getProveedorByAcreedorCodigoSap(String acreedorSap);

    Proveedor getProveedorByIdProveedor(Integer id);

    @Modifying
    @Query("UPDATE Proveedor t SET t.acreedorCodigoSap = ?1 WHERE t.idProveedor=?2")
    public void updateAcreedorCodigoSAP(String acreedorCodigoSap, Integer idProveedor);

    @Modifying
    @Query("UPDATE Proveedor t SET t.idHcp = ?1 WHERE t.ruc=?2")
    public void updateIdHCP(String idHCP, String ruc);

    public List<Proveedor> findByFechaCreacionBetweenOrderByRuc(Date fechaCreacionIni, Date fechaCreacionFin);

    public List<Proveedor> findByFechaCreacionGreaterThanEqualOrderByRuc(Date fechaCreacionIni);

    public List<Proveedor> findAllByOrderByRuc();


    @Transactional
    @Modifying
    @Query( "update Proveedor p set " +
            "p.idEstadoProveedor = ?2, " +
            "p.codigoMaximoEstadoAprobado = ?3, " +
            "p.indHomologado = ?4," +
            "p.cantidadFlujosCompletados = ?5," +
            "p.enFlujoAprobacion = ?6 " +
            "where p.idProveedor = ?1" )
    void saveStatusSupplierInfo( Integer idProveedor                ,
                                 EstadoProveedor idEstadoProveedor          ,
                                 String  codigoMaximoEstadoAprobado ,
                                 String  indHomologado              ,
                                 Integer cantidadFlujosCompletados  ,
                                 String  enFlujoAprobacion
    );

}
