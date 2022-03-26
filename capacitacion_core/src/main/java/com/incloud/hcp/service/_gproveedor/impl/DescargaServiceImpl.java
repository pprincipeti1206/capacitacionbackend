package com.incloud.hcp.service._gproveedor.impl;


import com.incloud.hcp.domain._gproveedor.domain.HomologacionRespuesta;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.mapper._gproveedor.ContactoMapper;
import com.incloud.hcp.mapper._gproveedor.CuentaBancariaMapper;
import com.incloud.hcp.mapper._gproveedor.HomologacionMapper;
import com.incloud.hcp.mapper._gproveedor.LineaComercialMapper;
import com.incloud.hcp.service._gproveedor.DescargaService;
import com.incloud.hcp.service._gproveedor.ProveedorService;
import com.incloud.hcp.service._gproveedor.dto.ProveedorDatosGeneralesDTO;
import com.incloud.hcp.service._gproveedor.dto.reporte.proveedor.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrador on 30/11/2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
public class DescargaServiceImpl implements DescargaService {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ContactoMapper contactoMapper;

    @Autowired
    private CuentaBancariaMapper cuentaBancariaMapper;

    @Autowired
    private LineaComercialMapper lineaComercialMapper;

    @Autowired
    private HomologacionMapper homologacionMapper;


    @Override
    @Transactional(readOnly = true)
    public HSSFWorkbook getProveedores(String selection)  {

        Pattern p = Pattern.compile("[0-1]{6}");
        Matcher m = p.matcher(selection);

        if (!m.matches()) {
            throw new PortalException("La selección es incorrecta");
        }
        String[] opciones = selection.split("");

        HSSFWorkbook book = new HSSFWorkbook();
        if (opciones[0].equals("1")) {
            String fechaCreacionIni = new String();
            String fechaCreacionFin = new String();
            List<ProveedorDatosGeneralesDTO> list = this.proveedorService.getProveedorDatosGenerales(
                    fechaCreacionIni, fechaCreacionFin
            );

            ProveedorReporte rptProveedor = new ProveedorReporte(book);
            rptProveedor.agregar(list);
        }

        if (opciones[1].equals("1")) {
            SucursalReporte rptSucursal = new SucursalReporte(book);
            rptSucursal.agregar(contactoMapper.getListAllSucursalContactoProveedor());
        }

        if (opciones[2].equals("1")) {
            CuentaBancoReporte rptCuentaBancaria = new CuentaBancoReporte(book);
            rptCuentaBancaria.agregar(cuentaBancariaMapper.getListAllCuentasBancariasProveedor());
        }

        if (opciones[3].equals("1")) {
            LineaComercialReporte rptLineaComercial = new LineaComercialReporte(book);
            rptLineaComercial.agregar(lineaComercialMapper.getListAllLineasComercialesProveedor());
        }


        return book;
    }


    @Override
    @Transactional(readOnly = true)
    public HSSFWorkbook getHomologacion() {

        HSSFWorkbook book = new HSSFWorkbook();
        List<HomologacionRespuesta> list = this.homologacionMapper.getListHomologacionExcel();
        HomologacionReporte homologacionReporte = new HomologacionReporte(book);
        homologacionReporte.agregar(list);

        return book;
    }




}
