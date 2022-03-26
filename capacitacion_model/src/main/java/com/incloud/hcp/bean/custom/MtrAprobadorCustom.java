package com.incloud.hcp.bean.custom;

import com.incloud.hcp.domain.MtrAprobador;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MtrAprobadorCustom implements Serializable {
    private static final long serialVersionUID = 1L;
    private MtrAprobador aprobador;
    private List<MtrAprobador> lista;
}
