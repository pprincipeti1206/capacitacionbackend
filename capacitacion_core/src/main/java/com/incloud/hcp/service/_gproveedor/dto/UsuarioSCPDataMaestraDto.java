package com.incloud.hcp.service._gproveedor.dto;

public class UsuarioSCPDataMaestraDto {

    private String codigoSCP;
    private String displayName;
    private String email;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCodigoSCP() {
        return codigoSCP;
    }

    public void setCodigoSCP(String codigoSCP) {
        this.codigoSCP = codigoSCP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UsuarioSCPDataMaestraDto{" +
                "codigoSCP='" + codigoSCP + '\'' +
                ", displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
