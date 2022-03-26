package com.incloud.hcp.repository._gproveedor;


import com.incloud.hcp.domain._gproveedor.domain.InformacionNoticia;
import com.incloud.hcp.domain._gproveedor.domain.TipoInformacionNoticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by MARCELO on 26/09/2017.
 */
public interface InformacionNoticiaRepository extends JpaRepository<InformacionNoticia, Integer> {

    public List<InformacionNoticia> findByTipoInformacionNoticia(TipoInformacionNoticia tipoInformacionNoticia);

    @Modifying
    @Query("select i from InformacionNoticia i where LOWER(i.titulo) = LOWER(?1) and i.tipoInformacionNoticia.idTipoInformacionNoticia = ?2")
    public List<InformacionNoticia> findByTituloTipo(String titulo, Integer id);

    @Query("select i from InformacionNoticia i where i.idInformacionNoticia <> ?1 and LOWER(i.titulo) = LOWER(?2)")
    public List<InformacionNoticia> findByTituloById(Integer id, String titulo);
}
