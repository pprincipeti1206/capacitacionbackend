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

@StaticMetamodel(CerNotaPedidoHistorial.class)
public abstract class CerNotaPedidoHistorial_ {

    // Raw attributes
    public static volatile SingularAttribute<CerNotaPedidoHistorial, Integer> id;
    public static volatile SingularAttribute<CerNotaPedidoHistorial, String> descripcion;
    public static volatile SingularAttribute<CerNotaPedidoHistorial, String> usuarioHistoral;
    public static volatile SingularAttribute<CerNotaPedidoHistorial, Date> fechaHistorial;
    public static volatile SingularAttribute<CerNotaPedidoHistorial, Date> fechaFinVigencia;
    public static volatile SingularAttribute<CerNotaPedidoHistorial, Integer> plazo;

    // Many to one
    public static volatile SingularAttribute<CerNotaPedidoHistorial, CerNotaPedido> cerNotaPedido;
    public static volatile SingularAttribute<CerNotaPedidoHistorial, MtrEstado> mtrEstado;
}
