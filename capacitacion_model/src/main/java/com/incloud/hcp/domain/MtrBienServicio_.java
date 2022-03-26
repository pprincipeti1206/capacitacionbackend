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

@StaticMetamodel(MtrBienServicio.class)
public abstract class MtrBienServicio_ {

    // Raw attributes
    public static volatile SingularAttribute<MtrBienServicio, Integer> id;
    public static volatile SingularAttribute<MtrBienServicio, String> codigoSap;
    public static volatile SingularAttribute<MtrBienServicio, String> descripcion;
    public static volatile SingularAttribute<MtrBienServicio, String> tipoItem;
    public static volatile SingularAttribute<MtrBienServicio, String> descripcionBreve;

    // Many to one
    public static volatile SingularAttribute<MtrBienServicio, MtrGrupoArticulo> mtrGrupoArticulo;
    public static volatile SingularAttribute<MtrBienServicio, MtrUnidadMedida> mtrUnidadMedida;
}
