package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PreRegistroItemInput implements Serializable {
    private static final long serialVersionUID = 1L;

    private String invoice_doc_item;
    private String po_number;
    private String po_item;
    private String ref_doc;
    private String ref_doc_year;
    private String ref_doc_it;
    private String de_cre_ind;
    private String tax_code;
    private String taxjurcode;
    private String item_amount;//decimal
    private String quantity;//decimal
    private String po_unit;
    private String po_unit_iso;
    private String po_pr_qnt;//decimal
    private String po_pr_uom;
    private String po_pr_uom_iso;
    private String cond_type;
    private String cond_st_no;
    private String cond_count;
    private String sheet_no;
    private String item_text;
    private String final_inv;
    private String sheet_item;
    private String grir_clear_srv;
    private String freight_ven;
    private String cshdis_ind;
    private String retention_docu_currency;//decimal
    private String retention_percentage;//decimal
    private String retention_due_date;//Date
    private String no_retention;
    private String valuation_type;
    private String inv_relation;
    private String inv_itm_origin;


}
