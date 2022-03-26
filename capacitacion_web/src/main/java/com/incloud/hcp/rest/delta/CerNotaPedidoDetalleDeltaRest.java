/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 *
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/rest/EntitydeltaResource.java.e.vm
 */
package com.incloud.hcp.rest.delta;

import com.incloud.hcp.domain.CerNotaPedidoDetalle;
import com.incloud.hcp.repository.CerNotaPedidoDetalleRepository;
import com.incloud.hcp.rest.CerNotaPedidoDetalleRest;
import com.incloud.hcp.service.dto.CerNotaPedidoDetalleDto;
import com.incloud.hcp.service.dto.CertificadoNotaPedidoDetalleCantidadEntregaPrecioDto;
import com.incloud.hcp.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/cerNotaPedidoDetalle")
public class CerNotaPedidoDetalleDeltaRest extends CerNotaPedidoDetalleRest {

    @Autowired
    protected CerNotaPedidoDetalleRepository cerNotaPedidoDetalleRepository;


    @ApiOperation(value = "", produces = "application/json")
    @GetMapping(value = "/_findByDetalle/{cerNotaPedidoId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CerNotaPedidoDetalleDto>> findByDetalle(@PathVariable Integer cerNotaPedidoId) throws URISyntaxException {
        log.debug("Ingresando _findByDetalle: " + cerNotaPedidoId);
        try {
            return Optional.ofNullable(this.cerNotaPedidoDetalleDeltaService.findByDetalle(cerNotaPedidoId))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }


    @ApiOperation(value = "Genera Excel XLSX de registros por Nota de Pedido", produces = "application/vnd.ms-excel")
    @GetMapping(value = "/_downloadExcelporFiltro/{cerNotaPedidoId}", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> downloadCompleteExcelSXLSX(
            @PathVariable Integer cerNotaPedidoId,
            HttpServletResponse response) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
        String nombreArchivo = this.setObtenerNombreArchivoExcel();
        String excelFileName = nombreArchivo + "_" + formatter.format(LocalDateTime.now()) + ".xlsx";

        try {
            SXSSFWorkbook book = this.cerNotaPedidoDetalleDeltaService.downloadExcelporCerNotaPedidoId(cerNotaPedidoId);
            ByteArrayOutputStream outByteStream;
            byte[] outArray;
            outByteStream = new ByteArrayOutputStream();
            book.write(outByteStream);
            outArray = outByteStream.toByteArray();
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=" + excelFileName);
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);


            book.dispose();
            book.close();

            outStream.flush();

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            e.printStackTrace();
            throw new RuntimeException(error);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }





    @ApiOperation(value = "Actualiza Cantidad de Entrega y Precio", produces = "application/json")
    @RequestMapping(value = "/actualizaCantidadEntregaPrecio", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<CerNotaPedidoDetalle> actualizaCantidadEntregaPrecio(@RequestBody CertificadoNotaPedidoDetalleCantidadEntregaPrecioDto certificadoNotaPedidoDetalleCantidadEntregaPrecio) throws Exception {
        return ResponseEntity.ok().body(this.cerNotaPedidoDetalleDeltaService.
                actualizaCantidadEntregaPrecio(certificadoNotaPedidoDetalleCantidadEntregaPrecio));
    }

}