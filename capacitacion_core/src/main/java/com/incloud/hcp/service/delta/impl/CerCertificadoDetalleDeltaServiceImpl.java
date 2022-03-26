/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/dto/EntitydeltaDTOService.java.e.vm
 */
package com.incloud.hcp.service.delta.impl;

import com.incloud.hcp.domain.*;
import com.incloud.hcp.enums.NotaPedidoTipoPosicionEnum;
import com.incloud.hcp.mapper.CerCertificadoDetalleMapper;
import com.incloud.hcp.mapper.CerNotaPedidoDetalleMapper;
import com.incloud.hcp.repository.delta.CerDocumentoAdjuntoDeltaRepository;
import com.incloud.hcp.service._framework.cmis.CmisService;
import com.incloud.hcp.service.delta.AppParametriaDeltaService;
import com.incloud.hcp.service.delta.CerCertificadoDetalleDeltaService;
import com.incloud.hcp.service.dto.CerCertificadoDetalleDto;
import com.incloud.hcp.service.dto.CerCertificadoDetalleImputacionModificarDto;
import com.incloud.hcp.service.impl.CerCertificadoDetalleServiceImpl;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.utils.Constants;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * A simple DTO Facility for CerCertificadoDetalle.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CerCertificadoDetalleDeltaServiceImpl extends CerCertificadoDetalleServiceImpl implements CerCertificadoDetalleDeltaService {

    @Autowired
    private CmisService cmisService;

    @Autowired
    private CerDocumentoAdjuntoDeltaRepository cerDocumentoAdjuntoDeltaRepository;

    @Autowired
    private AppParametriaDeltaService appParametriaDeltaService;

    @Autowired
    private CerNotaPedidoDetalleMapper cerNotaPedidoDetalleMapper;

    @Autowired
    private CerCertificadoDetalleMapper cerCertificadoDetalleMapper;


    /**************************/
    /* Metodos Personalizados */
    /**************************/
    public CerCertificadoDetalleImputacionModificarDto saveImputacionAdjuntos(
            CerCertificadoDetalleImputacionModificarDto bean,
            CerCertificado certificado
            ) throws Exception {
        if (this.appParametriaDeltaService.devuelveApagarCertificado()) {
            throw new Exception(Constants.MENSAJE_SISTEMA_APAGADO);
        }

        List<CerCertificadoDetalleDto> cerCertificadoDetalleDtoList = bean.getCerCertificadoDetalleDtoList();
        for (CerCertificadoDetalleDto beanDetalle : cerCertificadoDetalleDtoList) {
            CerCertificadoDetalle cerCertificadoDetalle = beanDetalle.getCerCertificadoDetalle();
            log.error("Ingresando saveImputacionAdjuntos cerCertificadoDetalle: " + cerCertificadoDetalle.toString());

            if (Optional.ofNullable(cerCertificadoDetalle.getTotalLinea()).isPresent()) {
                log.error("Ingresando saveImputacionAdjuntos getTotalLinea: " + cerCertificadoDetalle.getTotalLinea());
                BigDecimal totalLinea = cerCertificadoDetalle.getTotalLinea().setScale(2, BigDecimal.ROUND_HALF_UP);
                cerCertificadoDetalle.setTotalLinea(totalLinea);
            }
            this.cerCertificadoDetalleDeltaRepository.save(cerCertificadoDetalle);
            log.error("Ingresando DESPUES saveImputacionAdjuntos cerCertificadoDetalle: " + cerCertificadoDetalle.toString());
            log.error("Ingresando DESPUES saveImputacionAdjuntos getTotalLinea: " + cerCertificadoDetalle.getTotalLinea());
        }
        List<CerDocumentoAdjunto> cerDocumentoAdjuntoList = bean.getCerDocumentoAdjuntoList();
        if (cerDocumentoAdjuntoList != null && cerDocumentoAdjuntoList.size() > 0) {
            for(CerDocumentoAdjunto beanDocumentoAdjunto : cerDocumentoAdjuntoList) {
                log.error("saveImputacionAdjuntos bean cerDocumentoAdjuntoList: " + beanDocumentoAdjunto.toString());
                this.cerDocumentoAdjuntoDeltaRepository.save(beanDocumentoAdjunto);
            }

        }
        return bean;

    }

    public List<CerCertificadoDetalleDto> findByDetalle(Integer cerCertificadoId ) throws Exception {

        Optional<CerCertificado> optionalCerCertificado = this.cerCertificadoDeltaRepository.findById(cerCertificadoId);
        if (!optionalCerCertificado.isPresent()) {
            throw new Exception("No se encontró Certificado con ID: " + cerCertificadoId);
        }
        CerCertificado cerCertificado = optionalCerCertificado.get();
        return this.findByDetalle(cerCertificado);

    }


    public List<CerCertificadoDetalleDto> findByDetalle(CerCertificado cerCertificado) throws Exception {

        CerNotaPedido cerNotaPedido = cerCertificado.getCerNotaPedido();
        List<CerNotaPedidoDetalle> cerNotaPedidoDetallePosicionList =
                this.cerNotaPedidoDetalleDeltaRepository.findByDetalleSoloPosiciones(cerNotaPedido.getId());
        List<CerCertificadoDetalleDto> listaFinal = new ArrayList<CerCertificadoDetalleDto>();

        if (cerNotaPedidoDetallePosicionList != null && cerNotaPedidoDetallePosicionList.size() > 0) {
            for (CerNotaPedidoDetalle bean : cerNotaPedidoDetallePosicionList) {
                CerCertificadoDetalleDto beanPosicionDto = new CerCertificadoDetalleDto();
                beanPosicionDto.setTipoPosicion(NotaPedidoTipoPosicionEnum.POSICION.getEstado());
                beanPosicionDto.setCerNotaPedidoDetalle(bean);

                CerCertificadoDetalle cerCertificadoDetalleBuscarPos = new CerCertificadoDetalle();
                cerCertificadoDetalleBuscarPos.setCerCertificado(cerCertificado);
                cerCertificadoDetalleBuscarPos.setCerNotaPedidoDetalle(bean);
                List<CerCertificadoDetalle> listaCerCertificadoDetallePos = this.cerCertificadoDetalleMapper.
                        getByCerCertificadoAndCerNotaPedidoDetalle(cerCertificadoDetalleBuscarPos);
                if(listaCerCertificadoDetallePos.size() > 0) {
                    for (CerCertificadoDetalle cerCertificadoDetallePos : listaCerCertificadoDetallePos) {
                        beanPosicionDto.setCerCertificadoDetalle(cerCertificadoDetallePos);
                        //if (Optional.ofNullable(cerCertificadoDetallePos).isPresent()) {
                        if (Optional.ofNullable(cerCertificadoDetallePos).isPresent()) {
                            listaFinal.add(beanPosicionDto);
                        }
                    }
                }else {
                    Integer contador =
                            this.cerCertificadoDetalleDeltaRepository.
                                    countByCerCertificadoAndCerNotaPedidoDetallePadre(cerCertificado.getId(), bean.getId());
                    if (contador != null && contador > 0) {
                        listaFinal.add(beanPosicionDto);
                    }
                }


                List<CerCertificadoDetalleDto> listaSubposiciones = new ArrayList<CerCertificadoDetalleDto>();
                List<CerNotaPedidoDetalle> subPosicionList = this.cerCertificadoDetalleDeltaRepository.
                        findByCertificadoNotaPedidoDetalleSubPosicion(
                                cerNotaPedido.getId(),
                                bean.getId(),
                                cerCertificado.getId());
                if (subPosicionList != null && subPosicionList.size() > 0) {
                    for (CerNotaPedidoDetalle beanSubposicion : subPosicionList) {
                        CerCertificadoDetalle cerCertificadoDetalleBuscarSPos = new CerCertificadoDetalle();
                        cerCertificadoDetalleBuscarSPos.setCerCertificado(cerCertificado);
                        cerCertificadoDetalleBuscarSPos.setCerNotaPedidoDetalle(beanSubposicion);

                        List<CerCertificadoDetalle> listaCerCertificadoDetalleSub =
                                this.cerCertificadoDetalleMapper.
                                        getByCerCertificadoAndCerNotaPedidoDetalle(cerCertificadoDetalleBuscarSPos);
                        if (Optional.ofNullable(listaCerCertificadoDetalleSub).isPresent()) {

                            for (CerCertificadoDetalle cerCertificadoDetalleSub: listaCerCertificadoDetalleSub) {
                                CerCertificadoDetalleDto beanSubPosicionDto = new CerCertificadoDetalleDto();
                                beanSubPosicionDto.setTipoPosicion(NotaPedidoTipoPosicionEnum.SUBPOSICION.getEstado());
                                beanSubPosicionDto.setCerNotaPedidoDetalle(beanSubposicion);
                                beanSubPosicionDto.setCerCertificadoDetalle(cerCertificadoDetalleSub);
                                listaSubposiciones.add(beanSubPosicionDto);
                            }

                        }
                    }
                }
                Iterator<CerCertificadoDetalleDto> it =  listaSubposiciones.iterator();
                CerCertificadoDetalleDto temp = null;
                while(it.hasNext()){
                    temp = (CerCertificadoDetalleDto)it.next();
                    if(!listaFinal.contains(temp)){
                        listaFinal.add(temp);
                    }
                }
            }
        }
        return listaFinal;
    }




    /***********************/
    /* Metodos de Busqueda */
    /***********************/

    protected Sort setFindAll(Sort sort) {
        sort = Sort.by(
                new Sort.Order(Sort.Direction.ASC, "cerCertificado.id"),
                new Sort.Order(Sort.Direction.ASC, "cerNotaPedidoDetalle.id"),
                new Sort.Order(Sort.Direction.ASC, "subpackno")
        );
        return sort;
    }

    protected Sort setFind(CerCertificadoDetalle req, ExampleMatcher matcher, Example<CerCertificadoDetalle> example, Sort sort) {
        sort = Sort.by(
                new Sort.Order(Sort.Direction.ASC, "cerCertificado.id"),
                new Sort.Order(Sort.Direction.ASC, "cerNotaPedidoDetalle.id"),
                new Sort.Order(Sort.Direction.ASC, "subpackno")
        );
        return sort;
    }

    protected void setFindPaginated(PageRequestByExample<CerCertificadoDetalle> req, ExampleMatcher matcher, Example<CerCertificadoDetalle> example) {
        return;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected String setValidacionesPrevias(CerCertificadoDetalle bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected CerCertificadoDetalle setCreate(CerCertificadoDetalle bean) throws Exception {
        return bean;
    }

    protected void setSave(CerCertificadoDetalle dto) throws Exception {
        return;
    }

    protected void setDelete(Integer id) throws Exception {

    }

    protected void setDeleteAll() throws Exception {

    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected CerCertificadoDetalle setUploadExcel(Cell currentCell, CerCertificadoDetalle cerCertificadoDetalle, int contador) throws Exception {
        cerCertificadoDetalle = super.setUploadExcel(currentCell, cerCertificadoDetalle, contador);
        return cerCertificadoDetalle;
    }

    protected String setSaveMasivo(CerCertificadoDetalle dto) throws Exception {
        return "";
    }

    protected List<CerCertificadoDetalle> setBeforeDeleteMasivo(List<CerCertificadoDetalle> listaDto) throws Exception {
        return listaDto;
    }

    protected void setAfterDeleteMasivo() throws Exception {
        return;
    }

    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    protected void setDownloadExcelItem(CerCertificadoDetalle bean, HSSFRow dataRow) {

    }

    protected void setDownloadExcel(HSSFSheet sheet) {

    }

    /*****************/
    /* Otros Metodos */
    /*****************/

    protected String setGraphDescripcionByMtrUnidadMedida(MtrUnidadMedida mtrUnidadMedida) {
        return mtrUnidadMedida.getId().toString();
    }

    protected String setGraphPieChartTituloByMtrUnidadMedida() {
        return "MtrUnidadMedida";
    }

    protected String setGraphBarChartTituloByMtrUnidadMedida() {
        return "MtrUnidadMedida";
    }

    protected String setGraphDescripcionByCerCertificado(CerCertificado cerCertificado) {
        return cerCertificado.getId().toString();
    }

    protected String setGraphPieChartTituloByCerCertificado() {
        return "CerCertificado";
    }

    protected String setGraphBarChartTituloByCerCertificado() {
        return "CerCertificado";
    }

}
