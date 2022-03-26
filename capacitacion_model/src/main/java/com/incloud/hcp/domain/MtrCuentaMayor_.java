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

@StaticMetamodel(MtrCuentaMayor.class)
public abstract class MtrCuentaMayor_ {

    // Raw attributes
    public static volatile SingularAttribute<MtrCuentaMayor, Integer> id;
    public static volatile SingularAttribute<MtrCuentaMayor, String> codigoCuentaMayor;
    public static volatile SingularAttribute<MtrCuentaMayor, String> descripcion;
    public static volatile SingularAttribute<MtrCuentaMayor, String> estatus;

    // Many to one
    public static volatile SingularAttribute<MtrCuentaMayor, MtrTipoImputacion> mtrTipoImputacion;
    public static volatile SingularAttribute<MtrCuentaMayor, MtrSociedad> mtrSociedad;
}
