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
public class RangeSap  implements Serializable{
    private static final long serialVersionUID = 1L;
    private String sign;
    private String option;
    private String low;
    private String high;

    /*public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangeSap)) return false;
        RangeSap rangeSap = (RangeSap) o;
        return getSign().equals(rangeSap.getSign()) &&
                getOption().equals(rangeSap.getOption()) &&
                getLow().equals(rangeSap.getLow()) &&
                getHigh().equals(rangeSap.getHigh());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSign(), getOption(), getLow(), getHigh());
    }

    @Override
    public String toString() {
        return "RangeSap{" +
                "sign='" + sign + '\'' +
                ", option='" + option + '\'' +
                ", low='" + low + '\'' +
                ", high='" + high + '\'' +
                '}';
    }*/
}
