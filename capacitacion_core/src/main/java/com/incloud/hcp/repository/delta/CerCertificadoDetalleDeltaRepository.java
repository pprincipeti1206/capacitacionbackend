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

import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.CerCertificadoDetalle;
import com.incloud.hcp.domain.CerNotaPedidoDetalle;
import com.incloud.hcp.repository.CerCertificadoDetalleRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CerCertificadoDetalleDeltaRepository extends CerCertificadoDetalleRepository {

    @Query("select u.cerNotaPedidoDetalle from CerCertificadoDetalle u " +
            "where u.cerCertificado.id = ?1 and u.cerNotaPedidoDetalle.idPadre is null " +
            "order by u.cerNotaPedidoDetalle.posicionSap")
    List<CerNotaPedidoDetalle> findByDetalleSoloPosiciones(Integer cerCertificadoId);

    List<CerCertificadoDetalle> findByCerCertificado(CerCertificado cerCertificado);

    CerCertificadoDetalle getByCerCertificadoAndCerNotaPedidoDetalle(
            CerCertificado cerCertificado,
            CerNotaPedidoDetalle cerNotaPedidoDetalle);

    @Query("select u.cerNotaPedidoDetalle from CerCertificadoDetalle u " +
            "where u.cerCertificado.id = ?1 and u.cerNotaPedidoDetalle.idPadre = ?2 " )
    List<CerNotaPedidoDetalle> findByCerCertificadoAndCerNotaPedidoDetallePadre(
            Integer cerCertificadoId,
            Integer cerNotaPedidoDetalleIdPadre);

    @Query("select count(u.id) from CerCertificadoDetalle u " +
            "where u.cerCertificado.id = ?1 and u.cerNotaPedidoDetalle.idPadre = ?2 " )
    Integer countByCerCertificadoAndCerNotaPedidoDetallePadre(
            Integer cerCertificadoId,
            Integer cerNotaPedidoDetalleIdPadre);

    @Query("select u.cerNotaPedidoDetalle from CerCertificadoDetalle u " +
            "where u.cerCertificado.id = ?1 ")
    List<CerNotaPedidoDetalle> findByNotaPedido(Integer cerCertificadoId);

    @Query("select u.cerNotaPedidoDetalle from CerCertificadoDetalle u " +
            "where u.cerNotaPedidoDetalle.cerNotaPedido.id = ?1 " +
            "and u.cerNotaPedidoDetalle.idPadre = ?2 " +
            "and u.cerCertificado.id = ?3 " +
            "order by u.cerNotaPedidoDetalle.posicionSap, u.cerNotaPedidoDetalle.extrow")
    List<CerNotaPedidoDetalle> findByCertificadoNotaPedidoDetalleSubPosicion(
            Integer cerNotaPedidoId,
            Integer idPadre,
            Integer cerCertificadoId);

    @Query("select u from CerCertificadoDetalle u " +
            "where u.cerCertificado.id = ?1 ")
    List<CerCertificadoDetalle> obtenerListaDetallePorCertificadoId(Integer cerCertificadoId);

    @Query("select u from CerCertificadoDetalle u where u.cerCertificado.indTieneFactura = 'N' and u.cerCertificado.mtrEstado.id =?1 ")
    List<CerCertificadoDetalle> findByByIndTieneFactura(Integer estado);

}
