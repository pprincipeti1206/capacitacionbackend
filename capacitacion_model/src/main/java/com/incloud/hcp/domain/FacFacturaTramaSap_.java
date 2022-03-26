/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.incloud.hcp.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FacFacturaTramaSap.class)
public abstract class FacFacturaTramaSap_ {

    // Raw attributes
    public static volatile SingularAttribute<FacFacturaTramaSap, Integer> id;
    public static volatile SingularAttribute<FacFacturaTramaSap, String> nombreRfc;
    public static volatile SingularAttribute<FacFacturaTramaSap, String> textoTrama01;
    public static volatile SingularAttribute<FacFacturaTramaSap, String> textoTrama02;
    public static volatile SingularAttribute<FacFacturaTramaSap, String> textoTrama03;
    public static volatile SingularAttribute<FacFacturaTramaSap, String> usuarioEjecutor;

    // Many to one
    public static volatile SingularAttribute<FacFacturaTramaSap, FacFactura> facFactura;
}
