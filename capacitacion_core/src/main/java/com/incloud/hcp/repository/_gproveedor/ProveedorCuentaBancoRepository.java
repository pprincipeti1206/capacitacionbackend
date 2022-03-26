package com.incloud.hcp.repository._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.ProveedorCuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrador on 29/08/2017.
 */
public interface ProveedorCuentaBancoRepository extends JpaRepository<ProveedorCuentaBancaria, Integer> {

    @Query("SELECT b FROM ProveedorCuentaBancaria b WHERE b.proveedor.idProveedor= ?1")
    List<ProveedorCuentaBancaria> getListCuentaBancariaByIdProveedor(Integer idProveedor);

    @Query("SELECT b FROM ProveedorCuentaBancaria b WHERE " +
            "b.proveedor.idProveedor= ?1 and " +
            "b.banco.idBanco = ?2 and " +
            "b.moneda.idMoneda = ?3 and " +
            "b.numeroCuenta = ?4"
    )
    List<ProveedorCuentaBancaria> getListCuentaBancariaByIdProveedorCuentaBancaria(
            Integer idProveedor,
            Integer idBanco,
            Integer idMoneda,
            String numeroCuenta);

    @Modifying
    @Query("DELETE FROM ProveedorCuentaBancaria p WHERE p.proveedor.idProveedor= ?1")
    void deleteContactoByIdProveedor(Integer idProveedor);
}
