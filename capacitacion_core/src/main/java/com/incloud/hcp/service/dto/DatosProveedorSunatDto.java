package com.incloud.hcp.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class DatosProveedorSunatDto {

    private String $id;

    @JsonProperty("Ruc")
    private String ruc;

    @JsonProperty("RazonSocial")
    private String razonSocial;

    @JsonProperty("Estado")
    private String estado;

    @JsonProperty("CondicionDomicilio")
    private String condicionDomicilio;

    @JsonProperty("Ubigeo")
    private String ubigeo;

    @JsonProperty("TipoVia")
    private String tipoVia;

    @JsonProperty("NombreVia")
    private String nombreVia;

    @JsonProperty("CodigoZona")
    private String codigoZona;

    @JsonProperty("TipoZona")
    private String tipoZona;

    @JsonProperty("Numero")
    private String numero;

    @JsonProperty("Interior")
    private String interior;

    @JsonProperty("Lote")
    private String lote;

    @JsonProperty("Departamento")
    private String departamento;

    @JsonProperty("Manzana")
    private String manzana;

    @JsonProperty("Kilometro")
    private String kilometro;


}




