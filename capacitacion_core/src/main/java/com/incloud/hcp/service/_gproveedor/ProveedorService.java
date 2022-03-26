package com.incloud.hcp.service._gproveedor;

import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.bean.custom.SaveApprovalInput;
import com.incloud.hcp.domain._gproveedor.bean.ProveedorCustom;
import com.incloud.hcp.domain._gproveedor.bean.ProveedorFiltro;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.domain.response.SaveInformationResponse;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.service._gproveedor.dto.*;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrador on 29/08/2017.
 */
public interface ProveedorService {

    void eliminarDatosProveedor(Integer idProveedor);

    Proveedor getProveedorById(Integer idProveedor);

    Proveedor getProveedorByIdHcp(String idHcp) throws PortalException;

    Proveedor getProveedorByRuc(String ruc);
    ProveedorDto getProveedorDtoByRuc(String ruc);
    ProveedorDto getProveedorDtoByRucResponder(String ruc);

    Proveedor save(Proveedor documento);

    ProveedorDto getProveedorDtoByIdHcp(String idHcp) throws PortalException;

    ProveedorDto getProveedorDtoById(Integer idProveedor) throws PortalException;

    ProveedorDto saveDto(ProveedorDto dto) throws Exception;

    ProveedorDto toDto(Proveedor proveedor) throws PortalException;

    List<LineaComercialDto> getListLineaComercialByIdProveedor(Integer idProveedor);

    List<ProveedorCatalogoDto> getListCatalogosByIdProveedor(Integer idProveedor);

    List<Proveedor> getListProveedor();

    List<ProveedorCustom> getListProveedorByFiltro(ProveedorFiltro filtro);
    List<ProveedorCustom> getListProveedorByFiltroPaginado(ProveedorFiltro filtro);
    List<ProveedorCustom> getListProveedorByFiltroValidacion(UserSession userSession, ProveedorFiltro filtro);
    List<ProveedorCustom> getListProveedorByFiltroLicitacion(ProveedorFiltro filtro);
    List<ProveedorCustom> getListProveedorByFiltroLicitacionPaginado(ProveedorFiltro filtro);

    List<ProveedorCustom> getListProveedorByRuc(String ruc);

    List<ProveedorCustom> getListProveedorSinHcpID();

    List<ProveedorDatosGeneralesDTO> getProveedorDatosGenerales(
            String fechaCreacionIni, String fechaCreacionFin) throws PortalException;

    void evaluarDataMaestra(Integer idProveedor) throws PortalException ;
    void rechazarDataMaestra(Integer idProveedor, String motivoRechazo) throws PortalException ;
    Integer updateProveedorIDHCP(ListProveedorHCP listProveedorHCP);
    //carga Excel para la creacion en la BD
    public List<ProveedorXLSXDTO> uploadExcel(InputStream in);

    SaveInformationResponse aprobacionCompras( Integer idProveedor, String usuarioSap );
    void evaluarDataMaestra02(Integer idProveedor) throws Exception ;
    void rechazarDataMaestra02(RechazarValidacionDataMaestraEntradaDto bean) throws Exception ;

    /**
     * CC. Nuevo Flujo de Aprobaciones
     * */
    SaveInformationResponse saveTreasuryApproval( SaveApprovalInput input, String usuarioSap );
    SaveInformationResponse saveTaxApproval( SaveApprovalInput input );

    ProveedorValidacionInfoSalidaDto aprobarRechazarActualizacion(ProveedorValidacionInfoEntradaDto bean) throws Exception;

}
