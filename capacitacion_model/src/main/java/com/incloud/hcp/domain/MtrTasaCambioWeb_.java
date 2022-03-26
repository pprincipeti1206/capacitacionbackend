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
import java.math.BigDecimal;
import java.util.Date;

@StaticMetamodel(MtrTasaCambioWeb.class)
public abstract class MtrTasaCambioWeb_ {

    // Raw attributes
    public static volatile SingularAttribute<MtrTasaCambioWeb, Integer> id;
    public static volatile SingularAttribute<MtrTasaCambioWeb, Date> fechaTasa;
    public static volatile SingularAttribute<MtrTasaCambioWeb, Integer> mtrMonedaOrigenId;
    public static volatile SingularAttribute<MtrTasaCambioWeb, Integer> mtrMonedaDestinoId;
    public static volatile SingularAttribute<MtrTasaCambioWeb, BigDecimal> valor;
}