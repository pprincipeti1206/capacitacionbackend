package com.incloud.hcp.service.delta.impl;

import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.domain.MtrUsuarioFacturacion;
import com.incloud.hcp.service.delta.FacFacturaDeltaCompletableFutureService;
import com.incloud.hcp.service.delta.FacFacturaDeltaService;
import com.incloud.hcp.service.dto.FacFacturaAprobacionFirmanteEntradaDto;
import com.incloud.hcp.service.dto.FacFacturaAprobacionFirmanteSalidaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FacFacturaDeltaCompletableFutureServiceImpl implements FacFacturaDeltaCompletableFutureService {

    @Autowired
    private FacFacturaDeltaService facFacturaDeltaService;

    @Async
    public CompletableFuture<FacFacturaAprobacionFirmanteSalidaDto> aprobarFacturaFirmanteCompleteFuture(
            UserSession userSession,
            FacFacturaAprobacionFirmanteEntradaDto bean,
            MtrUsuarioFacturacion mtrUsuarioFacturacionFirmante) {
        FacFacturaAprobacionFirmanteSalidaDto result = this.facFacturaDeltaService.
                aprobarFacturaFirmanteSinException(userSession, bean, mtrUsuarioFacturacionFirmante);
        return CompletableFuture.completedFuture(result);
    }


    @Async
    public CompletableFuture<FacFacturaAprobacionFirmanteSalidaDto> enviarEmailFacturaAprobacionFirmanteSgteCompleteFuture(
            FacFacturaAprobacionFirmanteSalidaDto bean) {
        FacFacturaAprobacionFirmanteSalidaDto result = this.facFacturaDeltaService.
                enviarEmailFacturaAprobacionFirmanteSgteSinException(bean);
        return CompletableFuture.completedFuture(result);
    }
}
