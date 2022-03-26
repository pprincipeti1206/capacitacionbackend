package com.incloud.hcp.service._gproveedor.facade.impl;


import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp._security.SystemLoggedUser;
import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.domain.AppParametria;
import com.incloud.hcp.domain._gproveedor.domain.*;
import com.incloud.hcp.enums._gproveedor.EstadoPreRegistroEnum;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.mapper._gproveedor.ParametroMapper;
import com.incloud.hcp.repository._gproveedor.LineaComercialRepository;
import com.incloud.hcp.repository._gproveedor.PreRegistroLineaComercialRepository;
import com.incloud.hcp.repository._gproveedor.PreRegistroProveedorRepository;
import com.incloud.hcp.repository._gproveedor.PreguntaInformacionRepository;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.service._gproveedor.PreRegistroProveedorService;
import com.incloud.hcp.service._gproveedor.dto.LineaComercialDto;
import com.incloud.hcp.service._gproveedor.dto.PreRegistroProveedorDto;
import com.incloud.hcp.service._gproveedor.facade.PreRegistroFacade;
import com.incloud.hcp.service._gproveedor.notificacion.ProveedorPotencialAprobadoNotificacion;
import com.incloud.hcp.service._gproveedor.notificacion.ProveedorPotencialRechazadoNotificacion;
import com.incloud.hcp.service.notificacion.RegistroSolicitudProveedorAprobadoNotificacion;
import com.incloud.hcp.service.notificacion.RegistroSolicitudProveedorNotificacion;
import com.incloud.hcp.service.notificacion.RegistroSolicitudProveedorRechazoNotificacion;
import com.incloud.hcp.utils._gproveedor.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PreRegistroFacadeImpl implements PreRegistroFacade {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private PreRegistroProveedorService preRegistroProveedorService;
    private PreRegistroProveedorRepository preRegistroProveedorRepository;
    private PreRegistroLineaComercialRepository preRegistroLineaComercialRepository;
    private LineaComercialRepository lineaComercialRepository;
    private PreguntaInformacionRepository preguntaInformacionRepository;

    private Populater<PreRegistroProveedor, PreRegistroProveedorDto> preRegistroPopulate;

    private ProveedorPotencialAprobadoNotificacion proveedorPotencialAprobadoNotificacion;
    private ProveedorPotencialRechazadoNotificacion proveedorPotencialRechazadoNotificacion;
    private ParametroMapper parametroMapper;
    private SystemLoggedUser systemLoggedUser;
    @Autowired
    AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    RegistroSolicitudProveedorNotificacion registroSolicitudProveedorNotificacion;

    @Autowired
    RegistroSolicitudProveedorRechazoNotificacion registroSolicitudProveedorRechazoNotificacion;
    @Autowired
    RegistroSolicitudProveedorAprobadoNotificacion registroSolicitudProveedorAprobadoNotificacion;

    @Autowired
    public void setPreRegistroProveedorService(
            PreRegistroProveedorService preRegistroProveedorService,
            PreRegistroProveedorRepository preRegistroProveedorRepository,
            PreRegistroLineaComercialRepository preRegistroLineaComercialRepository,
            LineaComercialRepository lineaComercialRepository,
            PreguntaInformacionRepository preguntaInformacionRepository,
            ProveedorPotencialAprobadoNotificacion proveedorPotencialAprobadoNotificacion,
            ProveedorPotencialRechazadoNotificacion proveedorPotencialRechazadoNotificacion,
            ParametroMapper parametroMapper,
            SystemLoggedUser systemLoggedUser) {
        this.preRegistroProveedorService = preRegistroProveedorService;
        this.preRegistroProveedorRepository = preRegistroProveedorRepository;
        this.preRegistroLineaComercialRepository = preRegistroLineaComercialRepository;
        this.lineaComercialRepository = lineaComercialRepository;
        this.preguntaInformacionRepository = preguntaInformacionRepository;
        this.proveedorPotencialAprobadoNotificacion = proveedorPotencialAprobadoNotificacion;
        this.proveedorPotencialRechazadoNotificacion = proveedorPotencialRechazadoNotificacion;
        this.parametroMapper = parametroMapper;
        this.systemLoggedUser = systemLoggedUser;
    }

    @Autowired
    @Qualifier(value = "preRegistroPopulate")
    public void setPreRegistroPopulate(Populater preRegistroPopulate) {
        this.preRegistroPopulate = preRegistroPopulate;
        //preRegistroProveedorRepository.
    }

    @Override
    public PreRegistroProveedorDto save(PreRegistroProveedorDto preRegistroProveedorDto) throws Exception {
        Optional<PreRegistroProveedor> opt = Optional.ofNullable(preRegistroProveedorDto).map(preRegistroPopulate::toEntity);
        if (opt.isPresent()) {
                PreRegistroProveedor preRegistro = opt.get();
                preRegistro.setEstado(EstadoPreRegistroEnum.PENDIENTE.getCodigo());
                this.preRegistroProveedorService.guardar(preRegistro);

                /* Agregando detalle de Lineas Comerciales */
                List<LineaComercial> lineaComercialList = preRegistroProveedorDto.getLineaComercialList();

                this.preRegistroLineaComercialRepository.deleteByIdRegistro(preRegistro);
                for (LineaComercial beanLinea : lineaComercialList) {
                    PreRegistroLineaComercial beanDetalle = new PreRegistroLineaComercial();
                    beanDetalle.setIdRegistro(preRegistro);
                    Optional.ofNullable(this.lineaComercialRepository.getLineaComercialById(beanLinea.getIdLinea())).ifPresent(beanDetalle::setIdLineaComercial);
                    Optional.ofNullable(this.lineaComercialRepository.getLineaComercialById(beanLinea.getIdFamilia())).ifPresent(beanDetalle::setIdFamilia);
                    this.preRegistroLineaComercialRepository.save(beanDetalle);
                }
                AppParametria paramDest =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_1", "1");
                if(paramDest != null) {
                    registroSolicitudProveedorNotificacion.enviar(preRegistro, paramDest.getValue1(), paramDest.getValue2());
                }
                return preRegistroPopulate.toDto(preRegistro);
        }
        throw new PortalException("Error al guardar el pre-registro ");
    }

    @Override
    public PreRegistroProveedorDto updateSearchSunat(PreRegistroProveedorDto dto) {
        logger.error("updateSearchSunat a01 dto " + dto.toString());
        PreRegistroProveedor preRegistroProveedor = this.preRegistroPopulate.toEntity(dto);
        logger.error("updateSearchSunat a02 preRegistroProveedor " + preRegistroProveedor.toString());
        preRegistroProveedor.setFechaInicioActiSunat(dto.getFechaInicioActiSunat());
        preRegistroProveedor.setCodigoSistemaEmisionElect(dto.getCodigoSistemaEmisionElect());
        preRegistroProveedor.setCodigoComprobantePago(dto.getCodigoComprobantePago());
        preRegistroProveedor.setCodigoPadron(dto.getCodigoPadron());
        logger.error("updateSearchSunat a03 preRegistroProveedor " + preRegistroProveedor.toString());
        return this.preRegistroPopulate.toDto(
                this.preRegistroProveedorService.updateSearchSunat(preRegistroProveedor));
    }

    @Override
    public PreRegistroProveedorDto getPreRegistroByIdHcp(String idHcp) {
        Optional<PreRegistroProveedor> oPreRegistro = Optional.ofNullable(this.preRegistroProveedorService)
                .map(service -> service.getPreRegistroProveedorByIdHcp(idHcp));

        if (!oPreRegistro.isPresent()) {
            return null;
            //throw new PortalException("No existe un Pre Registro con ID Hcp " + idHcp);
        }
        PreRegistroProveedor preRegistroProveedor = oPreRegistro.get();
        PreRegistroProveedorDto beanRetornoDTO = oPreRegistro.map(this.preRegistroPopulate::toDto).get();
        beanRetornoDTO.setCodigoSistemaEmisionElect(preRegistroProveedor.getCodigoSistemaEmisionElect());
        beanRetornoDTO.setCodigoComprobantePago(preRegistroProveedor.getCodigoComprobantePago());
        beanRetornoDTO.setCodigoPadron(preRegistroProveedor.getCodigoPadron());
        beanRetornoDTO.setFechaInicioActiSunat(preRegistroProveedor.getFechaInicioActiSunat());

        /* Obteniendo detalle de Lineas Comerciales */
        List<PreRegistroLineaComercial> preRegistroLineaComercialList = this.preRegistroLineaComercialRepository.
                findByIdRegistro(oPreRegistro.get());
        List<LineaComercial> lineaComercialList = new ArrayList<>();
        List<LineaComercialDto> lineaComercialListDTO = new ArrayList<>();
        for (PreRegistroLineaComercial bean: preRegistroLineaComercialList) {
            LineaComercial beanLinea = new LineaComercial();
            LineaComercialDto beanLineaDTO = new LineaComercialDto();

            beanLinea.setIdPadre(bean.getIdLineaComercial().getIdLineaComercial());
            beanLinea.setIdLineaComercial(bean.getIdFamilia().getIdLineaComercial());
            beanLinea.setIdLinea(bean.getIdLineaComercial().getIdLineaComercial());
            beanLinea.setIdFamilia(bean.getIdFamilia().getIdLineaComercial());
            beanLinea.setDescripcionPadre(bean.getIdLineaComercial().getDescripcion());
            beanLinea.setDescripcion(bean.getIdFamilia().getDescripcion());
            lineaComercialList.add(beanLinea);


            beanLineaDTO.setIdLinea(bean.getIdLineaComercial().getIdLineaComercial());
            beanLineaDTO.setLinea(bean.getIdLineaComercial().getDescripcion());
            beanLineaDTO.setIdFamilia(bean.getIdFamilia().getIdLineaComercial());
            beanLineaDTO.setFamilia(bean.getIdFamilia().getDescripcion());
            lineaComercialListDTO.add(beanLineaDTO);
        }
        beanRetornoDTO.setLineaComercialList(lineaComercialList);
        beanRetornoDTO.setLineasComercial(lineaComercialListDTO);


        /**
         * Pregunta Informacion
         */
        List<PreguntaInformacion> preguntaInformacionList =
                this.preguntaInformacionRepository.findAll(Sort.by("orden"));
        List<ProveedorPreguntaInformacion> proveedorPreguntaInformacionList = new ArrayList<ProveedorPreguntaInformacion>();
        for (PreguntaInformacion bean : preguntaInformacionList) {


            ProveedorPreguntaInformacion proveedorPreguntaInformacion = new ProveedorPreguntaInformacion();
            proveedorPreguntaInformacion.setRespuestaSiNo(false);

            if (Optional.ofNullable(proveedorPreguntaInformacion.getRespuesta()).isPresent()) {
                if (proveedorPreguntaInformacion.getRespuesta().equals(Constant.S)) {
                    proveedorPreguntaInformacion.setRespuestaSiNo(true);
                }
            }

            proveedorPreguntaInformacion.setIdPreguntaInformacion(bean);
            proveedorPreguntaInformacionList.add(proveedorPreguntaInformacion);
        }
        beanRetornoDTO.setPreguntaInformacion(proveedorPreguntaInformacionList);
        logger.error("toDto 06 Pregunta " + proveedorPreguntaInformacionList.toString());
        return beanRetornoDTO;
    }

    @Override
    public PreRegistroProveedorDto getPreRegistroById(Integer id) {
        Optional<PreRegistroProveedor> oPreRegistro = Optional.ofNullable(this.preRegistroProveedorService)
                .map(service -> service.getPreRegistroProveedorById(id));

        if (!oPreRegistro.isPresent()) {
            throw new PortalException("No existe un preregistro con el id " + id);
        }

        PreRegistroProveedorDto beanRetornoDTO = oPreRegistro.map(this.preRegistroPopulate::toDto).get();
        if (oPreRegistro.isPresent()) {
            PreRegistroProveedor preRegistroProveedor = oPreRegistro.get();
            beanRetornoDTO.setActivo(Optional.ofNullable(preRegistroProveedor.getActivo()).map(a -> a.equals("1")).orElse(false));
            beanRetornoDTO.setHabido(Optional.ofNullable(preRegistroProveedor.getHabido()).map(a -> a.equals("1")).orElse(false));
            beanRetornoDTO.setUbigeo(Optional.ofNullable(preRegistroProveedor.getUbigeo()).orElse(""));
        }

        /* Obteniendo detalle de Lineas Comerciales */
        List<PreRegistroLineaComercial> preRegistroLineaComercialList = this.preRegistroLineaComercialRepository.
                findByIdRegistro(oPreRegistro.get());
        List<LineaComercial> lineaComercialList = new ArrayList<>();
        for (PreRegistroLineaComercial bean: preRegistroLineaComercialList) {
            LineaComercial beanLinea = new LineaComercial();
            beanLinea.setIdPadre(bean.getIdLineaComercial().getIdLineaComercial());
            beanLinea.setIdLineaComercial(bean.getIdFamilia().getIdLineaComercial());
            beanLinea.setIdLinea(bean.getIdLineaComercial().getIdLineaComercial());
            beanLinea.setIdFamilia(bean.getIdFamilia().getIdLineaComercial());
            beanLinea.setDescripcionPadre(bean.getIdLineaComercial().getDescripcion());
            beanLinea.setDescripcion(bean.getIdFamilia().getDescripcion());
            lineaComercialList.add(beanLinea);
        }
        beanRetornoDTO.setLineaComercialList(lineaComercialList);
        return beanRetornoDTO;
    }

    @Override
    public List<PreRegistroProveedorDto> getListSolicitudPendiente() {
        List<PreRegistroProveedor> list = this.preRegistroProveedorService.getListPreRegistroPendiente();
        return Optional.ofNullable(list)
                .filter(l -> !l.isEmpty())
                .map(l -> {
                    List<PreRegistroProveedorDto> listDto = new ArrayList<>();
                    l.stream().map(preRegistroPopulate::toDto).forEach(listDto::add);
                    return listDto;
                }).orElse(new ArrayList<>());
    }


//    @Override
//    public List<PreRegistroProveedorDto> getListSolicitudPendiente(String idHcp) {
//        List<PreRegistroProveedor> preRegistroProveedorList =
//                this.preRegistroProveedorService.getListPreRegistroPendiente(idHcp);
//
//        List<PreRegistroProveedorDto> listDto = new ArrayList<PreRegistroProveedorDto>();
//        for(PreRegistroProveedor bean: preRegistroProveedorList) {
//            PreRegistroProveedorDto preRegistroProveedorDto = new PreRegistroProveedorDto();
//            BeanUtils.copyProperties(bean, preRegistroProveedorDto);
//            listDto.add(preRegistroProveedorDto);
//        }
//        return listDto;
//
//
//    }

    @Override
    public PreRegistroProveedorDto aprobarSolicitud(Integer idPreRegistro) {
        return this.preRegistroPopulate.toDto(this.preRegistroProveedorService.aprobarSolicitud(idPreRegistro));
    }

    @Override
    public PreRegistroProveedorDto reprobarSolicitud(Integer idPreRegistro) {
        return this.preRegistroPopulate.toDto(this.preRegistroProveedorService.reprobarSolicitud(idPreRegistro));
    }

    @Override
    public PreRegistroProveedorDto aprobarSolicitud02(Integer idPreRegistro) throws Exception {
        UserSession userSession = this.systemLoggedUser.getUserSession();
        if (!Optional.ofNullable(userSession).isPresent()) {
            throw new Exception("No se encontro los datos del Usuario Logueado en el IDP");
        }
        PreRegistroProveedor preRegistroProveedor = this.preRegistroProveedorService.aprobarSolicitud(idPreRegistro);
        /*this.proveedorPotencialAprobadoNotificacion.enviarComprador(
                preRegistroProveedor,
                userSession);*/
        this.registroSolicitudProveedorAprobadoNotificacion.enviar(preRegistroProveedor);
        return this.preRegistroPopulate.toDto(preRegistroProveedor);
    }

    @Override
    public PreRegistroProveedorDto reprobarSolicitud02(Integer idPreRegistro, String motivoRechazo) throws Exception  {
        UserSession userSession = this.systemLoggedUser.getUserSession();
        if (!Optional.ofNullable(userSession).isPresent()) {
            throw new Exception("No se encontro los datos del Usuario Logueado en el IDP");
        }
        PreRegistroProveedor preRegistroProveedor = this.preRegistroProveedorService.reprobarSolicitud(idPreRegistro);
        /*this.proveedorPotencialRechazadoNotificacion.enviarComprador(
                preRegistroProveedor,
                userSession);*/
        this.registroSolicitudProveedorRechazoNotificacion.enviar(preRegistroProveedor, motivoRechazo);
        return this.preRegistroPopulate.toDto(preRegistroProveedor);
    }

}