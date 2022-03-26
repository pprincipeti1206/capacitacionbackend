/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.incloud.hcp.domain._gproveedor.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProveedorPreguntaInformacion.class)
public abstract class ProveedorPreguntaInformacion_ {

    // Raw attributes
    public static volatile SingularAttribute<ProveedorPreguntaInformacion, Integer> id;
    public static volatile SingularAttribute<ProveedorPreguntaInformacion, String> respuesta;

    // Many to one
    public static volatile SingularAttribute<ProveedorPreguntaInformacion, Proveedor> idProveedor;
    public static volatile SingularAttribute<ProveedorPreguntaInformacion, PreguntaInformacion> idPreguntaInformacion;
}