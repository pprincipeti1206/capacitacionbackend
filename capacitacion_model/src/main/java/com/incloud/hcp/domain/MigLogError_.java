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
import java.util.Date;

@StaticMetamodel(MigLogError.class)
public abstract class MigLogError_ {

    // Raw attributes
    public static volatile SingularAttribute<MigLogError, Integer> id;
    public static volatile SingularAttribute<MigLogError, String> tabla;
    public static volatile SingularAttribute<MigLogError, Integer> idRegistro;
    public static volatile SingularAttribute<MigLogError, Date> fechaCreacion;
    public static volatile SingularAttribute<MigLogError, String> error;
}
