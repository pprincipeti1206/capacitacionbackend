package com.incloud.hcp.bean.custom;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class HistorialCustom {
    private Integer id;
    private String tipoEntidad;
    private String descripcion;
    private String responsable;
    private LocalDateTime fecha;
    //fff.get

}
