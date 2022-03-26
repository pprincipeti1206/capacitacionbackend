package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PreRegistroInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ebeln;
    private String invoice_ind;
    private String doc_type;
    private String pstng_date;//Date
    private String doc_date;//Date
    private String ref_doc_no;
    private String comp_code;
    private String gross_amount;//DECIMAL
    private String calc_tax_ind;
    private String exch_rate;//decimal
    private String bline_date;//Date
    private String header_txt;
    private String ref_doc_no_long;
    private String tax_code;
    private BigDecimal tax_base;
    private Integer idDetraccion;
    private String zaprob;
    private Date zfest;
    private  String zcont;
    private  String usRes;
    private BigDecimal iTipoc;
    private List<PreRegistroItemInput> listaItem;

}
