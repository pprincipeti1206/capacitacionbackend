package com.incloud.hcp.cmis;

/**
 * Created by Administrador on 26/09/2017.
 */
public class CmisFile {
    private String id;
    private String name;
    private String url;
    private String type;
    private Long size;
    private CmisFile parent;

    /**
     * CC. Nuevo Flujo Aprobación. Campo para almacenar el tipo de documento
     * */
    private String codigoTipoDocumento;

    public CmisFile() {
        this.parent = null;
    }

    public CmisFile(String id, String name, String url, String type) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
    }

    public CmisFile(String id, String name, String url, String type, Long size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }


    public String getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(String codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CmisFile getParent() {
        return parent;
    }

    public void setParent(CmisFile parent) {
        this.parent = parent;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CmisFile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", parent=" + parent +
                '}';
    }
}
