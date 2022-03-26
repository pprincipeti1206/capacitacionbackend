package com.incloud.hcp.utils;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Date Utility Class
 * This is used to convert Strings to Dates and Timestamps
 *
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a> 
 *   to correct time pattern. Minutes should be mm not MM
 * 	(MM is month). 
 * @version $Revision: 1.4 $ $Date: 2012/02/16 17:13:46 $
 */
public class DateUtil {
    //~ Static fields/initializers =============================================

    private static Log log = LogFactory.getLog(DateUtil.class);
    private static String datePattern = "dd/MM/yyyy";
    private static String timePattern = "HH:mm";

    //~ Methods ================================================================

    /**
     * Return default datePattern (dd/MM/yyyy)
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return datePattern;
    }

	private static DateFormat df =
			new SimpleDateFormat(DateUtil.getDatePattern());

	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		} else if (type == Date.class) {
			return convertToDate(type, value);
		} else if (type == String.class) {
			return convertToString(type, value);
		}

		throw new ConversionException("Could not convert " +
				value.getClass().getName() + " to " +
				type.getName());
	}

	protected Object convertToDate(Class type, Object value) {
		if (value instanceof String) {
			try {
				if (StringUtils.isEmpty(value.toString())) {
					return null;
				}

				return df.parse((String) value);
			} catch (Exception pe) {
				throw new ConversionException("Error converting String to Date");
			}
		}

		throw new ConversionException("Could not convert " +
				value.getClass().getName() + " to " +
				type.getName());
	}

	protected Object convertToString(Class type, Object value) {
		if (value instanceof Date) {
			try {
				return df.format(value);
			} catch (Exception e) {
				throw new ConversionException("Error converting Date to String");
			}
		}

		return value.toString();
	}


    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @see SimpleDateFormat
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
      throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                      + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }
    
    /**
     * This method returns the current date time in the format:
     * mask
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getDateTimeNow(String mask, Date theTime) {
        return getDateTime(mask, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     * 
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    
    /**
     * @param aMask
     * @param strDate
     * @param tipoRangoFecha
     * 		Calendar.HOUR
     *      Calendar.DATE
     * 		Calendar.MONTH
     *      Calendar.YEAR
     * @param cantidad
     * @return
     * @throws Exception
     * Ejemplo:  addToDate("dd/mm/yyyy", "01/01/2008" , Calendar.DATE, 30)
     */
    public static Date addToDate(String aMask, String strDate, int tipoRangoFecha, int cantidad) throws Exception {
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(convertStringToDate(aMask, strDate));
    	cal.add(tipoRangoFecha, cantidad);
    	Date fechaRetorno = cal.getTime();
    	return fechaRetorno;		
    }
    
    /**
     * @param dDate
     * @param tipoRangoFecha
     * @param cantidad
     * @return
     * @throws Exception
     * Ejemplo:  addToDate(new Date() , Calendar.DATE, 30)
     */
    public static Date addToDate(Date dDate, int tipoRangoFecha, int cantidad) throws Exception {
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(dDate);
    	cal.add(tipoRangoFecha, cantidad);
    	Date fechaRetorno = cal.getTime();
    	return fechaRetorno;		
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * 
     * @see SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(datePattern, aDate);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aMask the date pattern the string is in
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(String aMask, Date aDate) {
        return getDateTime(aMask, aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     * 
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * 
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate)
      throws ParseException {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + datePattern);
            }

            aDate = convertStringToDate(datePattern, strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate
                      + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(),
                                     pe.getErrorOffset());
                    
        }

        return aDate;
    }
    
    /**
	 * Convierte del formato <i>yyyyMMdd</i> al formato <i>dd/MM/yyyy</i>.
	 * <br />
	 * Ej: 20061231 a 31/12/2006
	 * 
	 * @param fecha
	 *            String con el formato <i>yyyyMMdd</i>
	 * @return String con el formato <i>dd/MM/yyyy</i>
	 */
	public static String convierteFormatoFecha(String fecha) {
		String resultado = "";
		resultado = fecha.substring(6, 8) + "/" + fecha.substring(4, 6) + "/"
				+ fecha.substring(0, 4);
		return resultado;
	}
	
    /**
     * This method converts a Date to a String using the datePattern
     * 
     * @param fecha the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * 
     * @throws ParseException
     */
	public static String formatoString(Date fecha, String formato) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(formato);
			return df.format(fecha);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * se pasa fecha y formato si formato es null por default se obtine dd/MM/yyyy
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static Calendar stringToCalender(String fecha,String formato){
		Calendar cal=null;
		String formatoDefault="dd/MM/yyyy";
		try{
			DateFormat formatter ; 
			Date date ; 
			if(StringUtils.isEmpty(formato)) formato = formatoDefault;
			formatter = new SimpleDateFormat(formato);
			date = (Date)formatter.parse(fecha); 
			cal=Calendar.getInstance();
			cal.setTime(date);
		}catch(Exception e){
			return null;
		}
		return cal;
	}
	
	public static int compareDates(String fechaIncio, String fechaFin, String format){
		
		  int result = 0;
		  
			try {
				String[] sFormat = {format};
				
				Date dFechaInicio = DateUtils.parseDate(fechaIncio, sFormat);
				Date dFechaFin = DateUtils.parseDate(fechaFin, sFormat);
				
				result = dFechaInicio.compareTo(dFechaFin);

			} catch (ParseException e) {}
			
			return result;
		}
	
	/**
	 * 
	 * Adiciona Time a una Fecha Especifica
	 * @param fecha
	 * @param timer
	 * @return
	 */
	public static Date addHoraMinutoDate(Date fecha, Date timer) {
		String sfechaInicio = DateUtil.convertDateToString(fecha);
		String sTimerInicio = DateUtil.getTimeNow(timer);
		String retorno = sfechaInicio + sTimerInicio;
		Date dFecha;
		try{
			dFecha = convertStringToDate("dd/MM/yyyyHH:mm", retorno);
		}catch(Exception e){
			return null;
		}
		return dFecha;
	}
	
	/**
	 * se pasa fecha1 y  fecha2 para comparar
	 * @param fecha1
	 * @param fecha2
	 * @return a Integer object 
	 */
	public static Integer compararFechas(String fecha1, String fecha2){
		Integer valor = 0;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    	Date date1 = sdf.parse(fecha1);
	    	Date date2 = sdf.parse(fecha2);
	    	
	    	if(date1.compareTo(date2)>0){
	    		//System.out.println("Date1 is after Date2");
	    		valor = 1;
	    	}else if(date1.compareTo(date2)<0){
	    		//System.out.println("Date1 is before Date2");
	    		valor = -1;
	    	}else if(date1.compareTo(date2)==0){
	    		//System.out.println("Date1 is equal to Date2");
	    		valor = 0;
	    	}else{
	    		valor = null;
	    	}
		}catch(Exception ex){
			return null;
    	}
		
		return valor;
		
	}
	
	/**
	 * Devuelve diferencias entre dos Fechas 
	 * Si valor = 1 Dias,  2 Meses,  3 años
	 * @param fec1 fecha menor
	 * @param fec2 fecha mayor
	 * @param valor
	 * @return
	 */
	public static int diferenciaFechas(String fec1, String fec2, int valor){ 
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		int retorno=0; 
		Date date1 = null;
		Date date2 = null;
		try { 
			Calendar cal1 = null; 
			date1=df.parse(fec1); 
			cal1=Calendar.getInstance(); 
	
			Calendar cal2 = null; 
			date2=df.parse(fec2); 
			cal2=Calendar.getInstance(); 
	
			// different date might have different offset 
			cal1.setTime(date1); 
			long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET); 
	
			cal2.setTime(date2); 
			long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET); 
	
			// Use integer calculation, truncate the decimals 
			int hr1 = (int)(ldate1/3600000); //60*60*1000 
			int hr2 = (int)(ldate2/3600000); 
	
			int days1 = (int)hr1/24; 
			int days2 = (int)hr2/24; 
	
			int dateDiff = days2 - days1; 
			int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR); 
			int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH); 
	
			if(valor==1) { 
				if (dateDiff<0) dateDiff=dateDiff*(-1); 
				retorno=dateDiff; 
			}else if(valor==2){ 
				if (monthDiff<0) monthDiff=monthDiff*(-1); 
				retorno=monthDiff; 
			}else if(valor==3){ 
				if (yearDiff<0) yearDiff=yearDiff*(-1); 
				retorno=yearDiff; 
			} 
		} 
		catch (ParseException pe) 	{ 
			pe.printStackTrace(); 
		} 
		return retorno; 
	} 
	
}
