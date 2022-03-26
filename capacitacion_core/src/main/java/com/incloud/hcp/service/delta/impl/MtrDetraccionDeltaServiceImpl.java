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

import com.incloud.hcp.bean.custom.MensajeSap;
import com.incloud.hcp.bean.custom.RangeSap;
import com.incloud.hcp.domain.MtrDetraccion;
import com.incloud.hcp.repository.delta.MtrDetraccionDeltaRepository;
import com.incloud.hcp.service.delta.MtrDetraccionDeltaService;
import com.incloud.hcp.service.impl.MtrDetraccionServiceImpl;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.util.Utils;
import com.sap.conn.jco.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A simple DTO Facility for MtrDetraccion.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MtrDetraccionDeltaServiceImpl extends MtrDetraccionServiceImpl implements MtrDetraccionDeltaService {

    /**************************/
    /* Metodos Personalizados */
    /**************************/

    /***********************/
    /* Metodos de Busqueda */
    /***********************/

    @Autowired
    private MtrDetraccionDeltaRepository mtrDetraccionDeltaRepository;


    protected Sort setFindAll(Sort sort) {
        sort = Sort.by(
                new Sort.Order(Sort.Direction.ASC, "codigo"),
                new Sort.Order(Sort.Direction.ASC, "descripcion")
        );
        return sort;
    }

    protected Sort setFind(MtrDetraccion req, ExampleMatcher matcher, Example<MtrDetraccion> example, Sort sort) {
        sort = Sort.by(
                new Sort.Order(Sort.Direction.ASC, "codigo"),
                new Sort.Order(Sort.Direction.ASC, "descripcion")
        );
        return sort;
    }

    protected void setFindPaginated(PageRequestByExample<MtrDetraccion> req, ExampleMatcher matcher, Example<MtrDetraccion> example) {
        return;
    }

    /****************/
    /* METODOS CRUD */
    /****************/

    protected String setValidacionesPrevias(MtrDetraccion bean) throws Exception {
        String mensaje = "";
        return mensaje;
    }

    protected MtrDetraccion setCreate(MtrDetraccion bean) throws Exception {
        return bean;
    }

    protected void setSave(MtrDetraccion dto) throws Exception {
        return;
    }

    protected void setDelete(Integer id) throws Exception {

    }

    protected void setDeleteAll() throws Exception {

    }

    @Override
    public List<MensajeSap> actualizarDetraccion (List<RangeSap> lista) throws Exception {

        List<MensajeSap> listaMensaje = new ArrayList<MensajeSap>();
        listaMensaje.add(new MensajeSap("S", "", "Los registro de detracción se actualizaron correctamente"));

        List<MtrDetraccion> listaDetraccion = this.mtrDetraccionDeltaRepository.findAll();



        try {
            JCoDestination destination = JCoDestinationManager.getDestination("COGA_DEST_RFC");
            JCoRepository repo = destination.getRepository();
            JCoFunction stfcConnection = repo.getFunction("ZPE_MM_INDICADOR_RETENCION");

            JCoParameterList imports = stfcConnection.getImportParameterList();
            JCoTable tableImport = imports.getTable("I_WITHT");
            //JCoTable tableImport = imports.getTable("IT_TCERT");
            imports.setValue("I_LAND1", "PE");

            if (lista != null && lista.size() > 0) {
                for (RangeSap ele : lista) {
                    tableImport.appendRow();
                    tableImport.setValue("SIGN", ele.getSign());
                    tableImport.setValue("OPTION", ele.getOption());
                    tableImport.setValue("LOW", ele.getLow());
                    tableImport.setValue("HIGH", "");
                }
            }

            //Ejecutar Funcion
            stfcConnection.execute(destination);

            //Recuperar Datos de SAP
            JCoParameterList exports = stfcConnection.getExportParameterList();
            JCoTable tableExport = exports.getTable("O_MM_RETENCIONES");
            //Recuperar datos de salida




            for (int i = 0; i < tableExport.getNumRows(); i++) {

                tableExport.setRow(i);
                MtrDetraccion detraccion = new MtrDetraccion();

                Optional<MtrDetraccion> optionalDetraccion = listaDetraccion.stream()
                        .filter(x -> tableExport.getString("WITHT").equalsIgnoreCase(x.getIndDetraccion())
                                && tableExport.getString("WT_WITHCD").equalsIgnoreCase(x.getCodigo()))
                        .findFirst();
                if(optionalDetraccion.isPresent())
                    detraccion = optionalDetraccion.get();

                detraccion.setIndDetraccion(tableExport.getString("WITHT"));
                detraccion.setCodigo(tableExport.getString("WT_WITHCD"));
                if(tableExport.getBigDecimal("QSATZ") != null)
                    detraccion.setPorcentaje(tableExport.getBigDecimal("QSATZ").doubleValue());
                detraccion.setDescripcion(tableExport.getString("TEXT40"));
                detraccion.setEstado("1");
                this.mtrDetraccionDeltaRepository.save(detraccion);

            }


        } catch (AbapException e) {
            String error = Utils.obtieneMensajeErrorException(e);
            //claseDoc.setBatxt(error);
            //lista.add(claseDoc);
            // lista.add(new MessageCustom("E", e.toString()));
            //throw new RuntimeException(error);
            //return new MessageCustom("E", e.toString());
        } catch (JCoException e) {
            String error = Utils.obtieneMensajeErrorException(e);
            //claseDoc.setBatxt(error);
            //lista.add(claseDoc);
            //lista.add(new MessageCustom("E", e.toString()));
            //throw new RuntimeException(error);
            //return new MessageCustom("E", e.toString());
        }

        return listaMensaje;

    }

    /************************/
    /* METODOS CRUD Masivos */
    /************************/

    protected MtrDetraccion setUploadExcel(Cell currentCell, MtrDetraccion mtrDetraccion, int contador) throws Exception {
        mtrDetraccion = super.setUploadExcel(currentCell, mtrDetraccion, contador);
        return mtrDetraccion;
    }

    protected String setSaveMasivo(MtrDetraccion dto) throws Exception {
        return "";
    }

    protected List<MtrDetraccion> setBeforeDeleteMasivo(List<MtrDetraccion> listaDto) throws Exception {
        return listaDto;
    }

    protected void setAfterDeleteMasivo() throws Exception {
        return;
    }

    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    protected void setDownloadExcelItem(MtrDetraccion bean, HSSFRow dataRow) {

    }

    protected void setDownloadExcel(HSSFSheet sheet) {

    }

    /*****************/
    /* Otros Metodos */
    /*****************/

}
