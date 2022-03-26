/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 *
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/dto/EntitydeltaDTO.java.e.vm
 */
package com.incloud.hcp.service.delta;

import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.bean.custom.FacturaCustom;
import com.incloud.hcp.bean.custom.MensajeHistorialSap;
import com.incloud.hcp.domain.FacDocumentoAdjunto;
import com.incloud.hcp.domain.FacFactura;
import com.incloud.hcp.domain.MtrUsuarioFacturacion;
import com.incloud.hcp.domain.response.FacFacturaResponse;
import com.incloud.hcp.service.FacFacturaService;
import com.incloud.hcp.service.dto.*;
import com.incloud.hcp.service.support.PageResponse;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * Simple Interface for FacFactura.
 */
public interface FacFacturaDeltaService extends FacFacturaService {

    ByteArrayResource generarReporteConstanciaPdf(Integer proveedorId, Integer numCorrelativo) throws Exception;

    ByteArrayResource generarReportePdf(Integer facturaId, String codigoIdp) throws Exception;

    DatosProveedorSunatDto devuelveDatosProveedorSunat(String ruc) throws Exception;

    //List<MensajeHistorialSap> realizarPreregistroFactura(Integer idFactura) throws Exception;
    List<MensajeHistorialSap> realizarPreregistroFactura(FacFactura factura, BigDecimal montoIgv) throws Exception;

    String deleteAdjunto(String archivoID) throws Exception;

    FacDocumentoAdjunto agregarAdjuntoTemporal(MultipartFile file, String tipoAdjuntoFactura) throws Exception;

    FacFacturaDto devuelveFactura(Integer facFacturaId) throws Exception;

    FacFacturaDto grabarFactura(FacFacturaEntradaDto beanEntrada) throws Exception;
    FacFactura modificarFactura(FacturaCustom custom) throws Exception;

    void enviarEmailFactura(FacFacturaDto bean) throws Exception;

    List listaAdjuntos(Integer FacturaID);

    List deleteOneFile(String idFile) throws Exception;

    List deleteAllFilesCMIS();

    FacDocumentoAdjunto facturaAdjunto(MultipartFile file, String tipo) throws Exception;

    List<FacFactura> findProveedor(FacFactura bean) throws Exception;

    Integer devuelveCorrelativoAdjuntoOtros5MB(BigDecimal tamanno) throws Exception;

    PageResponse<FacFactura> findCondicionProveedorPaginated(FacFacturaResponse bean, PageRequest pageRequest ) throws Exception;

    PageResponse<FacFactura> findCondicionProveedorRegistroPaginated(FacFacturaResponse bean, PageRequest pageRequest ) throws Exception;

    PageResponse<FacFactura> findCondicionProveedorPublicacionPaginated(FacFacturaResponse bean, PageRequest pageRequest ) throws Exception;

    PageResponse<FacFactura> findCondicionPublicacionPaginated(FacFacturaResponse bean, PageRequest pageRequest ) throws Exception;

    PageResponse<FacFacturaAndAdjuntoFacFactuDto> findCondicionPublicacionConAdjuntoPaginated(FacFacturaResponse bean, PageRequest pageRequest ) throws Exception;

    List<FacFactura> findCondicionProveedor(FacFacturaResponse bean) throws Exception;

    FacFacturaRechazoCupaSalidaDto rechazarFacturaCupa(FacFacturaRechazoCupaEntradaDto bean) throws Exception;

    void enviarEmailFacturaRechazoCupa(FacFacturaRechazoCupaSalidaDto bean) throws Exception;

    FacFacturaActualizarCupaSalidaDto grabarFacturaCupa(FacFacturaActualizarCupaEntradaDto beanEntrada, boolean actualizar) throws Exception;

    FacFacturaActualizarCupaSalidaDto grabarFacturaCupaSinSap(FacFacturaActualizarCupaEntradaDto beanEntrada, boolean actualizar) throws Exception;


    void enviarEmailGrabarFacturaCupa(FacFacturaActualizarCupaSalidaDto bean) throws Exception;

    FacFacturaRechazoAprobador0SalidaDto rechazarFacturaAprobador0(FacFacturaRechazoAprobador0EntradaDto bean) throws Exception;

    void enviarEmailFacturaRechazoAprobador0(FacFacturaRechazoAprobador0SalidaDto bean) throws Exception;

    FacFacturaGrabarAprobador0SalidaDto grabarFacturaAprobador0(FacFacturaGrabarAprobador0EntradaDto beanEntrada) throws Exception;

    FacFacturaRechazoFirmanteSalidaDto rechazarFacturaFirmante(FacFacturaRechazoFirmanteEntradaDto bean) throws Exception;

    void enviarEmailFacturaRechazoFirmante(FacFacturaRechazoFirmanteSalidaDto bean) throws Exception;

    FacFacturaAprobacionFirmanteSalidaDto aprobarFacturaFirmante(
            FacFacturaAprobacionFirmanteEntradaDto bean,
            UserSession userSession) throws Exception;

    FacFacturaAprobacionFirmanteSalidaDto aprobarFacturaFirmanteSinException(
            UserSession userSession,
            FacFacturaAprobacionFirmanteEntradaDto bean,
            MtrUsuarioFacturacion mtrUsuarioFacturacionFirmante);

    void enviarEmailFacturaAprobacionFirmanteSgte(FacFacturaAprobacionFirmanteSalidaDto bean) throws Exception;

    void enviarEmailFacturaAprobacionFirmanteSgte(FacFacturaGrabarAprobador0SalidaDto bean) throws Exception;

    FacFacturaAprobacionFirmanteSalidaDto enviarEmailFacturaAprobacionFirmanteSgteSinException(
            FacFacturaAprobacionFirmanteSalidaDto bean) ;


    List<FacFactura> findFacturaAprobarRechazarAprobador0() throws Exception;

    List<FacFactura> findFacturaAprobarRechazarFirmante() throws Exception;

    PageResponse<FacFactura> findCondicionCupaPaginated(
            FacFacturaResponse req,
            PageRequest pageRequest) throws Exception;

    List<FacturaListaFirmantesDto> devuelveListaFirmantes(Integer facFacturaId) throws Exception;

    List<MtrUsuarioFirmanteEstrategiaDto> devuelveListaFirmantesEstrategia(Integer mtrEstrategiaId)  throws Exception;

    PageResponse<FacFactura> findCondicionValidacionAdjuntosPaginated(
            FacFacturaResponse req,
            PageRequest pageRequest) throws Exception;

    FacFacturaValidarAdjuntoSalidaDto grabarFacturaValidacionAdjunto(FacFacturaValidarAdjuntoEntradaDto beanEntrada) throws Exception;

    List<FacFacturaValidarAdjuntoSalidaDto> grabarFacturaValidacionAdjuntoLista(
            List<FacFacturaValidarAdjuntoEntradaDto> beanEntradaList) throws Exception ;

    SXSSFWorkbook downloadExcelporFiltro(FacFactura facFactura) throws Exception;

    void jobEnviarCorreoFacturaCupa() throws Exception;
    void jobEnviarCorreoFacturaAprobador0() throws Exception;
    void jobEnviarCorreoFacturaFirmante() throws Exception;

}
