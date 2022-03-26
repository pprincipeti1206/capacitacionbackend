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

@StaticMetamodel(MtrDetraccion.class)
public abstract class MtrDetraccion_ {

    // Raw attributes
    public static volatile SingularAttribute<MtrDetraccion, Integer> id;
    public static volatile SingularAttribute<MtrDetraccion, String> codigo;
    public static volatile SingularAttribute<MtrDetraccion, String> descripcion;
    public static volatile SingularAttribute<MtrDetraccion, String> estado;
    public static volatile SingularAttribute<MtrDetraccion, Double> porcentaje;
    public static volatile SingularAttribute<MtrDetraccion, String> indDetraccion;

}
