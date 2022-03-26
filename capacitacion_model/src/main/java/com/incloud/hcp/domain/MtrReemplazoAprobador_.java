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

@StaticMetamodel(MtrReemplazoAprobador.class)
public abstract class MtrReemplazoAprobador_ {

    // Raw attributes
    public static volatile SingularAttribute<MtrReemplazoAprobador, Integer> id;
    public static volatile SingularAttribute<MtrReemplazoAprobador, Date> fechaInicio;
    public static volatile SingularAttribute<MtrReemplazoAprobador, Date> fechaFin;

    // Many to one
    public static volatile SingularAttribute<MtrReemplazoAprobador, MtrAprobador> mtrAprobador;
    public static volatile SingularAttribute<MtrReemplazoAprobador, MtrAprobador> mtrReemplazo;
}