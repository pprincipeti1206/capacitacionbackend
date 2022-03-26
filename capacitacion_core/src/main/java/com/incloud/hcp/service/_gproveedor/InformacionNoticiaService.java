package com.incloud.hcp.service._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.InformacionNoticia;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by MARCELO on 26/09/2017.
 */

public interface InformacionNoticiaService {

    InformacionNoticia getInformacionNoticiaById(Integer id);

    List<InformacionNoticia> getInfoNoticiaByTipoInfoNoticia(int idTipoInformacionNoticia);

    ResponseEntity<Map> save(InformacionNoticia info);

    ResponseEntity<Map> update(InformacionNoticia info);

    ResponseEntity<Map> delete(InformacionNoticia info);
}
