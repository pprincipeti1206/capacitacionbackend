package com.incloud.hcp.common.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class GraphDataset {

    private Long[] data;
    private String[] backgroundColor;
    private String[] borderColor;
    private String[] hoverBackgroundColor;
    private String[] hoverBorderColor;
    private String label;


}
