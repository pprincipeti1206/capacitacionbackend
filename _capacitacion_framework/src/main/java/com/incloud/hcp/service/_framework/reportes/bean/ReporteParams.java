package com.incloud.hcp.service._framework.reportes.bean;

import com.incloud.hcp._security.UserSession;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.sf.jasperreports.engine.JasperReport;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ToString
@EqualsAndHashCode
public class ReporteParams {

	private String nombreReporte;
	private String tipoReporteJasper;
	private JasperReport jasperReport;
	private InputStream inputStreamReporte;

	private Map<String, Object> parameterMap;
	private List<?> listaDataReporte;

	private Date fechaActual;
	private UserSession userSession;



}
