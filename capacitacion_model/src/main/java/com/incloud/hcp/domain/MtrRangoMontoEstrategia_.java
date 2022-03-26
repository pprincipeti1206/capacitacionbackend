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

@StaticMetamodel(MtrRangoMontoEstrategia.class)
public abstract class MtrRangoMontoEstrategia_ {

    // Raw attributes
    public static volatile SingularAttribute<MtrRangoMontoEstrategia, Integer> id;
    public static volatile SingularAttribute<MtrRangoMontoEstrategia, String> descripcion;
    public static volatile SingularAttribute<MtrRangoMontoEstrategia, Integer> valorMinimo;
    public static volatile SingularAttribute<MtrRangoMontoEstrategia, Integer> valorMaximo;

    // Many to one
    public static volatile SingularAttribute<MtrRangoMontoEstrategia, MtrMoneda> mtrMoneda;
    public static volatile SingularAttribute<MtrRangoMontoEstrategia, MtrTipoContrato> mtrTipoContrato;
}