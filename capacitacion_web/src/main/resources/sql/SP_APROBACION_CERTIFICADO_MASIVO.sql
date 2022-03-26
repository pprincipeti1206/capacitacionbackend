
CREATE OR REPLACE PROCEDURE SP_APROBACION_CERTIFICADO_MASIVO(
     in ip_id_certificado integer,
     in ip_currentOpe nvarchar(10),
     in ip_code_next_estado nvarchar(10),
     in ip_code_next_estado_historial nvarchar(10),
     in ip_user_login nvarchar(255),
     in ip_indicador_error_previo integer,
     in ip_monto_total_ajustado DECIMAL(19,2) DEFAULT 0,
     in ip_user_nombre_completo nvarchar(255),
     in ip_user_nombre_completo_aux nvarchar(255),
     in ip_texto_mensaje_operacion nvarchar(4000),
     in ip_id_cer_firma integer,
     in ip_indicador_creacion_hes integer,
     in ip_code_certificado_hijo_automatico nvarchar(30),
     in ip_texto_sap nvarchar(4000),
     --out et_mensaje lty_mensaje,
	 out tipo nvarchar(50),
	 out code nvarchar(500),
	 out texto nvarchar(5000))
	 language sqlscript as
	 --Variables
     li_aux_sequence integer;
     li_aux_sequence_adicional integer;
     li_aux_sequence_detalle integer;
     li_id_estado_rechazado integer;
     li_id_estado_next integer;
     li_id_estado_next_historial integer;
     li_id_estado_firmab integer;
     code_tx_object nvarchar(255);---Nombre de objecto que transaaciona
     li_row_count integer;
     i integer;
     ln_det_certificado_cantidad_aprobada DECIMAL(19,2) DEFAULT 0;
     ln_det_pedido_cantidad_entregada DECIMAL(19,2) DEFAULT 0;
     li_pedido_id integer;
     ls_ind_creacion_automatica varchar(1);
     montoUpdateCertificado DECIMAL(19,2) DEFAULT 0;
     cantidadTotal DECIMAL(19,2) DEFAULT 0;
     montoNuevo DECIMAL(19,2) DEFAULT 0;
     aux_total_linea DECIMAL(19,2) DEFAULT 0;
     tipo_aux nvarchar(50) DEFAULT 'N';
     li_row_count_aux integer;
     i_aux integer;
     ls_msg_aux VARCHAR(4000);
     li_flag_texto_sap INTEGER DEFAULT 0;


begin

  DECLARE  var_commit  VARCHAR(100) := 'COMMIT';
  DECLARE  var_rollback VARCHAR(100) := 'ROLLBACK';

  DECLARE ls_txt_operacion nvarchar(2) DEFAULT 'A';
  DECLARE ln_aux_suma_monto DECIMAL(19,2) DEFAULT 0;
  DECLARE lb_flag_aux nvarchar(1) DEFAULT '';
  DECLARE ltd_certificado_detalle cer_certificado_detalle;
  DECLARE ltd_pedido_detalle CER_NOTA_PEDIDO_DETALLE;
  DECLARE ltd_certificado CER_CERTIFICADO;
  DECLARE LTD_MSG SPLIT_TYPE;

  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    EXEC (:var_rollback);
    if( :code_tx_object = 'CER_HISTORIAL') then
      --:et_mensaje.INSERT(('E','','Error al actualizar Historial en Hana'),1);
       tipo = 'E';
       code = '';
       texto = 'Error al actualizar Historial en Hana';
    end if;

    if( :code_tx_object = 'CER_CERTIFICADO_FIRMA') then
      --:et_mensaje.INSERT(('E','','Error al actualizar Certificado Firma'),1);
       tipo = 'E';
       code = '';
       texto = 'Error al actualizar Certificado Firma';
    end if;

    if( :code_tx_object = 'CER_CERTIFICADO') then
      --:et_mensaje.INSERT(('E','','Error al actualizar Cabecera Certificado'),1);
       tipo = 'E';
       code = '';
       texto = 'Error al actualizar Cabecera Certificado';
    end if;

    if( :code_tx_object = 'CER_NOTA_PEDIDO_DETALLE') then
      --:et_mensaje.INSERT(('E','','Error al actualizar Detalle de pedido'),1);
       tipo = 'E';
       code = '';
       texto = 'Error al actualizar Detalle de pedido';
    end if;

    if( :code_tx_object = 'CER_CERTIFICADO_ADICIONAL') then
      --:et_mensaje.INSERT(('E','','Error al actualizar Certificado adicional'),1);
       tipo = 'E';
       code = '';
       texto = 'Error al actualizar Certificado adicional';
    end if;

    if( :code_tx_object = 'CER_CERTIFICADO_ADICIONAL_MONTO') then
      --:et_mensaje.INSERT(('E','','Error al actualizar Montos Certificados'),1);
       tipo = 'E';
       code = '';
       texto = 'Error al actualizar Montos Certificados';
    end if;
    --code_object := 1;
  END;

    --Insertar Historial
    -- Insert Historial con mensaje proveniente de SAP
   -- Estado General rechazado
    SELECT  MTR_ESTADO_ID INTO li_id_estado_rechazado FROM mtr_estado WHERE CODIGO_ESTADO = 'CERE' AND CODIGO_AGRUPADO = 'CE' limit 1;

    --Trasaccionando Historial
    code_tx_object = 'CER_HISTORIAL';
    /*if (:ip_texto_mensaje_sap is not NULL or :ip_texto_mensaje_sap != '' or :ip_texto_mensaje_sap != ' ')  then

      SELECT SEQ_CER_HISTORIAL.NEXTVAL INTO li_aux_sequence FROM DUMMY;

      INSERT INTO CER_HISTORIAL (CER_HISTORIAL_ID,CREATED_BY, CREATED_DATE,
      	 DESCRIPCION, FECHA_HISTORIAL,USUARIO_HISTORAL,CER_CERTIFICADO_ID,MTR_ESTADO_ID) values
      (li_aux_sequence,ip_user_login,(GET_DATE_LOCAL_SECONDS(0)),ip_texto_mensaje_sap,
      (GET_DATE_LOCAL_SECONDS(0)),'SAP',ip_id_certificado,li_id_estado_rechazado);
      tipo_aux = 'S';

    end if;*/
    --Verificar si no hay errores previos a registrar datos en tablas hana
    if (:ip_indicador_error_previo = 0) then
        -- Estado siguiente certificado
      SELECT  MTR_ESTADO_ID INTO li_id_estado_next FROM mtr_estado WHERE CODIGO_ESTADO = ip_code_next_estado AND CODIGO_AGRUPADO = 'CE' limit 1;
        -- Estado siguiente Historial de certificado
      SELECT  MTR_ESTADO_ID INTO li_id_estado_next_historial FROM mtr_estado WHERE CODIGO_ESTADO = ip_code_next_estado_historial AND CODIGO_AGRUPADO = 'CE' limit 1;
      --Mensaje proveniente de SAP
      if (:ip_texto_sap is not NULL and :ip_texto_sap != '' and :ip_texto_sap != ' ') then
          CALL  SPLIT_AT_CHARACTER(ip_texto_sap,LTD_MSG);
          SELECT COUNT(*) INTO li_row_count_aux FROM :LTD_MSG;
          li_flag_texto_sap = 1;
      end if;

      -- Estado de operacion en el historial
      SELECT SEQ_CER_HISTORIAL.NEXTVAL INTO li_aux_sequence FROM DUMMY;

      INSERT INTO CER_HISTORIAL (CER_HISTORIAL_ID,CREATED_BY, CREATED_DATE,
        DESCRIPCION, FECHA_HISTORIAL,USUARIO_HISTORAL,CER_CERTIFICADO_ID,MTR_ESTADO_ID) values
        (li_aux_sequence,ip_user_login,(GET_DATE_LOCAL_SECONDS(0)),ip_texto_mensaje_operacion,
        (GET_DATE_LOCAL_SECONDS(2)),ip_user_nombre_completo_aux,ip_id_certificado,li_id_estado_next_historial);

      IF li_flag_texto_sap = 1 then
          FOR i_aux IN 0 .. :li_row_count_aux-1 DO
           SELECT SEQ_CER_HISTORIAL_DETALLE_SAP.NEXTVAL INTO li_aux_sequence_detalle FROM DUMMY;
           SELECT split into ls_msg_aux FROM :LTD_MSG LIMIT 1 OFFSET :i_aux;
               --ls_msg_aux = '';
           insert into  CER_HISTORIAL_DETALLE_SAP (CER_HISTORIAL_DETALLE_SAP_ID,
            CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,DESCRIPCION,
            POSICION_SAP,CER_HISTORIAL_ID) values (li_aux_sequence_detalle,'',
              (GET_DATE_LOCAL_SECONDS(0)),'',(GET_DATE_LOCAL_SECONDS(0)),ls_msg_aux,'',li_aux_sequence);

          END FOR;
      end if;

      -- Estado actual de certificado en el historial

      IF(:ip_currentOpe = 'RB' or :ip_currentOpe = 'RA') THEN

        SELECT SEQ_CER_HISTORIAL.NEXTVAL INTO li_aux_sequence FROM DUMMY;

        INSERT INTO CER_HISTORIAL (CER_HISTORIAL_ID,CREATED_BY, CREATED_DATE,
      	   DESCRIPCION, FECHA_HISTORIAL,USUARIO_HISTORAL,CER_CERTIFICADO_ID,MTR_ESTADO_ID) values
           (li_aux_sequence,ip_user_login,(GET_DATE_LOCAL_SECONDS(0)),ip_texto_mensaje_operacion,
           (GET_DATE_LOCAL_SECONDS(3)),ip_user_nombre_completo,ip_id_certificado,li_id_estado_next);


      END IF;

      --Insertar datos en la tabla CER_CERTIFICADO_FIRMA
      code_tx_object = 'CER_CERTIFICADO_FIRMA';
      IF(:ip_id_cer_firma IS NOT NULL AND :ip_id_cer_firma != 0) THEN

         IF(:ip_currentOpe = 'AB' OR :ip_currentOpe = 'AA') THEN
           ls_txt_operacion = 'A';
         ELSE
            ls_txt_operacion = 'R';
         END IF;

         SELECT SEQ_CER_CERTIFICADO_FIRMA.NEXTVAL INTO li_aux_sequence FROM DUMMY;

         INSERT INTO CER_CERTIFICADO_FIRMA (CER_CERTIFICADO_FIRMA_ID,CREATED_BY, CREATED_DATE,
      	   DESCRIPCION, FECHA_APROBACION,CER_CERTIFICADO_ID,CER_FIRMA_ID,IND_APROBACION_RECHAZO)values
           (li_aux_sequence,ip_user_login,(GET_DATE_LOCAL_SECONDS(0)),ip_texto_mensaje_operacion,
           (GET_DATE_LOCAL_SECONDS(0)),ip_id_certificado,ip_id_cer_firma,ls_txt_operacion);

      END IF;

      --Actualizar Datos Certificado
	  code_tx_object = 'CER_CERTIFICADO';
	  UPDATE CER_CERTIFICADO SET MTR_ESTADO_ID = li_id_estado_next,
	  FECHA_APROBACION = (GET_DATE_LOCAL_SECONDS(0)), MONTO_TOTAL_ADJUSTADO = ip_monto_total_ajustado
      WHERE CER_CERTIFICADO_ID = ip_id_certificado;

       --Actualizar Detalle de Pedido
      code_tx_object = 'CER_NOTA_PEDIDO_DETALLE';

      --SELECT CER_NOTA_PEDIDO_ID INTO li_pedido_id FROM CER_CERTIFICADO WHERE CER_CERTIFICADO_ID = ip_id_certificado limit 1;
      ltd_certificado = SELECT * FROM CER_CERTIFICADO WHERE CER_CERTIFICADO_ID = ip_id_certificado;
      SELECT CER_NOTA_PEDIDO_ID into li_pedido_id FROM :ltd_certificado LIMIT 1;
      SELECT IND_CREACION_AUTOMATICA into ls_ind_creacion_automatica FROM :ltd_certificado LIMIT 1;

      --SELECT IND_CREACION_AUTOMATICA INTO ls_ind_creacion_automatica FROM CER_CERTIFICADO WHERE CER_CERTIFICADO_ID = ip_id_certificado limit 1;

      ltd_certificado_detalle = SELECT * FROM cer_certificado_detalle WHERE CER_CERTIFICADO_ID = ip_id_certificado;
      ltd_pedido_detalle =  SELECT * FROM CER_NOTA_PEDIDO_DETALLE WHERE CER_NOTA_PEDIDO_ID = li_pedido_id;

      -- declare detalle_certificado_row_count integer;
      SELECT COUNT(*) INTO li_row_count FROM :ltd_certificado_detalle;
         --:FLAG_SUMA_CANTIDAD = 0;
      FOR i IN 0 .. :li_row_count-1 DO

        SELECT cantidad_aprobada into ln_det_certificado_cantidad_aprobada FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i;
        SELECT cantidad_entregada into ln_det_pedido_cantidad_entregada FROM :ltd_pedido_detalle LIMIT 1 OFFSET :i;

        IF(ln_det_certificado_cantidad_aprobada IS NOT NULL AND (SELECT cantidad_total FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i) IS NOT NULL) THEN
             ln_aux_suma_monto = ln_aux_suma_monto + ((SELECT cantidad_total FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i) - ln_det_certificado_cantidad_aprobada);
        END IF;

        IF(ln_det_certificado_cantidad_aprobada IS NOT NULL AND (SELECT precio_unitario FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i) IS NOT NULL) THEN
             montoUpdateCertificado = montoUpdateCertificado +
            						(ln_det_certificado_cantidad_aprobada*(SELECT precio_unitario FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i));
        END IF;


           -- Actualizando cantidad entregada al momento de hacer rechazo B
        IF(ln_det_certificado_cantidad_aprobada IS NOT NULL
             AND  ln_det_pedido_cantidad_entregada IS NOT NULL AND :ip_currentOpe = 'RB') THEN

               ln_det_pedido_cantidad_entregada = ln_det_pedido_cantidad_entregada - ln_det_certificado_cantidad_aprobada;
               UPDATE CER_NOTA_PEDIDO_DETALLE SET CANTIDAD_ENTREGADA = ln_det_pedido_cantidad_entregada
                WHERE CER_NOTA_PEDIDO_DETALLE_ID = (SELECT CER_NOTA_PEDIDO_DETALLE_ID FROM :ltd_pedido_detalle LIMIT 1 OFFSET :i);

        END IF;

      END FOR;

      IF(ln_aux_suma_monto > 0 ) THEN
          lb_flag_aux = 1;
      END IF;
      --Crear certificado adicional
      code_tx_object = 'CER_CERTIFICADO_ADICIONAL';
      IF(ip_indicador_creacion_hes = 1  AND lb_flag_aux = 1 AND ls_ind_creacion_automatica = 'S') THEN

         SELECT SEQ_CER_CERTIFICADO.NEXTVAL INTO li_aux_sequence FROM DUMMY;
         SELECT  MTR_ESTADO_ID INTO li_id_estado_firmab FROM mtr_estado WHERE CODIGO_ESTADO = 'CEFB' AND CODIGO_AGRUPADO = 'CE' limit 1;

         INSERT INTO CER_CERTIFICADO (
                CER_CERTIFICADO_ID,
                CREATED_BY,
                CREATED_DATE,
                MODIFIED_BY,
                MODIFIED_DATE,
                CODIGO_CERTIFICADO,--
                CONCEPTO,
                DESCUENTOH,
                FECHA_APROBACION,--
                FECHA_CONTAB_DOCUMENT,
                FECHA_DESDE,
                FECHA_HASTA,
                GRUPO_COMPRAS,
                HOJA_SERVICIO,
                IND_TIENE_FACTURA,
                LUGAR_PREST_SERV,
                MONTO,
                MONTO_TOTAL,
                MONTO_TOTAL_ADJUSTADO,
                NOTAS_RECHAZO,
                OBSERVACION,
                OCURRENCIA,
                RESPO_EXTERNO,
                SOLICITANTE,
                CER_NOTA_PEDIDO_ID,
                MTR_CLASE_DOCUMENTO_ID,
                MTR_CENTRO_ID,
                MTR_MONEDA_ID,
                MTR_PROVEEDOR_ID,
                MTR_SOCIEDAD_ID,
                MTR_ALMACEN_ID,
                MTR_ESTADO_ID,---
				IND_CREACION_AUTOMATICA,
				FECHA_CONTABILIZACION,
				NRO_GUIA_REMISION

             ) values(li_aux_sequence,--new
           (SELECT CREATED_BY FROM :ltd_certificado LIMIT 1),
           (SELECT CREATED_DATE FROM :ltd_certificado LIMIT 1),
           (SELECT MODIFIED_BY FROM :ltd_certificado LIMIT 1),
           (SELECT MODIFIED_DATE FROM :ltd_certificado LIMIT 1),
           ip_code_certificado_hijo_automatico,--new
           (SELECT CONCEPTO FROM :ltd_certificado LIMIT 1),
           (SELECT DESCUENTOH FROM :ltd_certificado LIMIT 1),
           NULL,--new
           (SELECT FECHA_CONTAB_DOCUMENT FROM :ltd_certificado LIMIT 1),
           (SELECT FECHA_DESDE FROM :ltd_certificado LIMIT 1),
           (SELECT FECHA_HASTA FROM :ltd_certificado LIMIT 1),
           (SELECT GRUPO_COMPRAS FROM :ltd_certificado LIMIT 1),
           (SELECT HOJA_SERVICIO FROM :ltd_certificado LIMIT 1),
           (SELECT IND_TIENE_FACTURA FROM :ltd_certificado LIMIT 1),
           (SELECT LUGAR_PREST_SERV FROM :ltd_certificado LIMIT 1),
           (SELECT MONTO FROM :ltd_certificado LIMIT 1),
           (SELECT MONTO_TOTAL FROM :ltd_certificado LIMIT 1),
           (SELECT MONTO_TOTAL_ADJUSTADO FROM :ltd_certificado LIMIT 1),
           (SELECT NOTAS_RECHAZO FROM :ltd_certificado LIMIT 1),
           (SELECT OBSERVACION FROM :ltd_certificado LIMIT 1),
           (SELECT OCURRENCIA FROM :ltd_certificado LIMIT 1),
           (SELECT RESPO_EXTERNO FROM :ltd_certificado LIMIT 1),
           (SELECT SOLICITANTE FROM :ltd_certificado LIMIT 1),
           (SELECT CER_NOTA_PEDIDO_ID FROM :ltd_certificado LIMIT 1),
           (SELECT MTR_CLASE_DOCUMENTO_ID FROM :ltd_certificado LIMIT 1),
           (SELECT MTR_CENTRO_ID FROM :ltd_certificado LIMIT 1),
           (SELECT MTR_MONEDA_ID FROM :ltd_certificado LIMIT 1),
           (SELECT MTR_PROVEEDOR_ID FROM :ltd_certificado LIMIT 1),
           (SELECT MTR_SOCIEDAD_ID FROM :ltd_certificado LIMIT 1),
           (SELECT MTR_ALMACEN_ID FROM :ltd_certificado LIMIT 1),
           li_id_estado_firmab,--new
           (SELECT IND_CREACION_AUTOMATICA FROM :ltd_certificado LIMIT 1),
           (SELECT FECHA_CONTABILIZACION FROM :ltd_certificado LIMIT 1),
           (SELECT NRO_GUIA_REMISION FROM :ltd_certificado LIMIT 1));
         --Registrar el detalle del cetificado adicional
         FOR i IN 0 .. :li_row_count-1 DO

           SELECT cantidad_aprobada into ln_det_certificado_cantidad_aprobada FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i;
           --SELECT cantidad_entregada into ln_det_pedido_cantidad_entregada FROM :ltd_pedido_detalle LIMIT 1 OFFSET :i;
           IF((SELECT cantidad_total FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i) IS NOT NULL AND ln_det_certificado_cantidad_aprobada IS NOT NULL) THEN

             cantidadTotal = ((SELECT cantidad_total FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i) -  ln_det_certificado_cantidad_aprobada);

             IF (((SELECT precio_unitario FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i)) IS NOT NULL) THEN
               aux_total_linea = (ln_det_certificado_cantidad_aprobada*(SELECT precio_unitario FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i));
               montoNuevo = (montoNuevo + aux_total_linea);
             END IF;

           END IF;
           IF (cantidadTotal > 0) THEN
            SELECT SEQ_CER_CERTIFICADO_DETALLE.NEXTVAL INTO li_aux_sequence_adicional FROM DUMMY;
             insert into CER_CERTIFICADO_DETALLE
              (CER_CERTIFICADO_DETALLE_ID,
              CREATED_BY,
              CREATED_DATE,
              MODIFIED_BY,
              MODIFIED_DATE,
              ASIGN_SERVICIO,
              AUXILIAR,
              CANTIDAD_APROBADA,
              CANTIDAD_PENDIENTE,
              CANTIDAD_TOTAL,
              CTA_CONTABLE,
              DESCUENTO,
              INDICADOR_ITEM_ADICI,
              NIVEL_ESTRUCTURA,
              NUM_ORDEN,
              PRECIO_UNITARIO,
              RANGO,
              SUBPACKNO,
              TOTAL_LINEA,
              TOTAL_LINEA_AJUSTADO,
              CER_CERTIFICADO_ID,
              CER_NOTA_PEDIDO_DETALLE_ID,
              MTR_CUENTA_IMPUTACION_ID,
              MTR_CUENTA_MAYOR_ID,
              FECHA_CREACION_MIGO
              )
             values(li_aux_sequence_adicional,--NEW
             ip_user_login,--NEW
             (GET_DATE_LOCAL_SECONDS(0)),
             (SELECT MODIFIED_BY FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT MODIFIED_DATE FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT ASIGN_SERVICIO FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT AUXILIAR FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             cantidadTotal,--NEW
             (SELECT CANTIDAD_PENDIENTE FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             cantidadTotal,--NEW
             (SELECT CTA_CONTABLE FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT DESCUENTO FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT INDICADOR_ITEM_ADICI FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT NIVEL_ESTRUCTURA FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT NUM_ORDEN FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT PRECIO_UNITARIO FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT RANGO FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT SUBPACKNO FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             aux_total_linea,--NEW
             (SELECT TOTAL_LINEA_AJUSTADO FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             li_aux_sequence,--new
             (SELECT CER_NOTA_PEDIDO_DETALLE_ID FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT MTR_CUENTA_IMPUTACION_ID FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT MTR_CUENTA_MAYOR_ID FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i),
             (SELECT FECHA_CREACION_MIGO FROM :ltd_certificado_detalle LIMIT 1 OFFSET :i));
           END IF;
         END FOR;
         code_tx_object = 'CER_CERTIFICADO_ADICIONAL_MONTO';
         --Actualizar monto total nuevo certificvado
         UPDATE CER_CERTIFICADO SET MONTO_TOTAL = montoNuevo WHERE  CER_CERTIFICADO_ID = li_aux_sequence;

         --Actualizar monto total  certificvado
         UPDATE CER_CERTIFICADO SET MONTO_TOTAL = montoUpdateCertificado WHERE  CER_CERTIFICADO_ID = ip_id_certificado;

         IF(ip_indicador_creacion_hes = 1) THEN --cERRAR NOTA DE PEDIDO

       END IF;

      END IF;
      tipo_aux = 'S';
    end if;
    --ISNUMERIC('ere');
    EXEC (:var_commit);

    /*et_mensaje = SELECT
	         documento_generado as tipo,
	         created_by as code,
	         texto_error as texto
             FROM CER_CERTIFICADO_DETALLE_SAP;*/
    --:et_mensaje.INSERT(('S','','Los registros se actualizaron Correctamente'),1);
    tipo = tipo_aux;
    code = '';
    texto = 'Los registros se actualizaron Correctamente';
end;
