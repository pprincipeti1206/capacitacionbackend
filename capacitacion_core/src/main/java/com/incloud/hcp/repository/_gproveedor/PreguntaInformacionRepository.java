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

import com.incloud.hcp.domain._gproveedor.domain.PreguntaInformacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaInformacionRepository extends JpaRepository<PreguntaInformacion, Integer> {

    /**
     * Return the persistent instance of {@link PreguntaInformacion} with the given unique property value descripcionPregunta,
     * or null if there is no such persistent instance.
     *
     * @param descripcionPregunta the unique value
     * @return the corresponding {@link PreguntaInformacion} persistent instance or null
     */
    PreguntaInformacion getByDescripcionPregunta(String descripcionPregunta);

}