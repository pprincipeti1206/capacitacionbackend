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

@StaticMetamodel(MtrReemplazoFacturacion.class)
public abstract class MtrReemplazoFacturacion_ {

    // Raw attributes
    public static volatile SingularAttribute<MtrReemplazoFacturacion, Integer> id;

    // Many to one
    public static volatile SingularAttribute<MtrReemplazoFacturacion, MtrTipoGerencia> mtrTipoGerencia;
    public static volatile SingularAttribute<MtrReemplazoFacturacion, MtrUsuarioFacturacion> mtrUsuarioFacturacion;
    public static volatile SingularAttribute<MtrReemplazoFacturacion, MtrSector> mtrSector;
}
