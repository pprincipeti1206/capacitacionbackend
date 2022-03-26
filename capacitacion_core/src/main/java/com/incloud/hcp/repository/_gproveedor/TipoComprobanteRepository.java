package com.incloud.hcp.repository._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.TipoComprobante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Joel on 02/09/2017.
 */
public interface TipoComprobanteRepository extends JpaRepository<TipoComprobante, Integer> {

    public TipoComprobante findByCodigoTipoComprobante(String codigo);
}
