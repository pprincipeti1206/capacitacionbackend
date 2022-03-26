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
public class PreRegistroFacturaOutput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String invoice_doc_item;
    private String gl_account;
    private String item_amount;//decimal
    private String db_cr_ind;
    private String neg_postng;
    private String comp_code;
    private String tax_code;
    private String taxjurcode;
    private String item_text;
    private String costcenter;
    private String sd_doc;
    private String sdoc_item;
    private String orderid;
    private String ref_date;//Date
    private String cmmt_item;
    private String funds_ctr;
    private String fund;
    private String bus_area;
    private String tr_part_ba;
    private String costobject;
    private String network;
    private String activity;
    private String wbs_elem;
    private String acttype;
    private String rl_est_key;
    private String person_no;
    private String co_busproc;
    private String grant_nbr;
    private String cmmt_item_long;
    private String func_area_long;
    private String quantity;//Decimal
    private String base_uom;
    private String alloc_nmbr;
    private String cshdis_ind;
    private String tax_base_amount;//Decimal
    private String profit_segm_no;
    private String plant;
    private String budget_period;

}
