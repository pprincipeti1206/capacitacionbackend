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

@StaticMetamodel(ProveedorValidacionInfoDetalle.class)
public abstract class ProveedorValidacionInfoDetalle_ {

    // Raw attributes
    public static volatile SingularAttribute<ProveedorValidacionInfoDetalle, Integer> id;
    public static volatile SingularAttribute<ProveedorValidacionInfoDetalle, String> nombreCampoBd;
    public static volatile SingularAttribute<ProveedorValidacionInfoDetalle, String> indEsLista;
    public static volatile SingularAttribute<ProveedorValidacionInfoDetalle, String> valorActual;
    public static volatile SingularAttribute<ProveedorValidacionInfoDetalle, String> valorNuevo;
    public static volatile SingularAttribute<ProveedorValidacionInfoDetalle, String> valorListaActual;
    public static volatile SingularAttribute<ProveedorValidacionInfoDetalle, String> valorListaNuevo;

    // Many to one
    public static volatile SingularAttribute<ProveedorValidacionInfoDetalle, ProveedorValidacionInfo> idProveedorPorValidarInfo;
}