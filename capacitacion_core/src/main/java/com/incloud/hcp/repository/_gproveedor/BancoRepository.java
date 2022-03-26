package com.incloud.hcp.repository._gproveedor;


import com.incloud.hcp.domain._gproveedor.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrador on 04/09/2017.
 */
public interface BancoRepository extends JpaRepository<Banco, Integer> {

    @Query("SELECT b FROM Banco b WHERE b.claveBanco = ?1")
    Banco getByClaveBanco(String claveBanco);
}
