/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.incloud.hcp.domain._gproveedor.domain;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProveedorCuentaBancariaExtranj.class)
public abstract class ProveedorCuentaBancariaExtranj_ {

    // Raw attributes
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, Integer> id;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> nombreBenef;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> codigoTipoCuentaBenef;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> cuentaBenef;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> direccionBenef;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> regionPaisBenef;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> ciudadPaisBenef;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> referenciaBenef;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> nombreBancoPagador;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> regionPaisBancoPagador;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> ciudadPaisBancoPagador;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> direccionBancoPagador;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> tipoCodigoBancoPagador;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> codigoBancoPagador;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> nombreBancoInterme;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> regionPaisBancoInterme;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> ciudadPaisBancoInterme;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> tipoCodigoBancoInterme;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> codigoBancoInterme;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, String> codigoBancoPagadorInterme;

    // Many to one
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, Ubigeo> idUbigeoPaisBancoInterme;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, Ubigeo> idUbigeoPaisBenef;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, Ubigeo> idUbigeoPaisBancoPagador;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, Moneda> idMonedaBancoPagador;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, Moneda> idMonedaBancoInterme;
    public static volatile SingularAttribute<ProveedorCuentaBancariaExtranj, Proveedor> idProveedor;
}