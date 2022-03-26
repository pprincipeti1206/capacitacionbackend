package com.incloud.hcp.service._gproveedor.facade;


import com.incloud.hcp.service._gproveedor.dto.PreRegistroProveedorDto;

import java.util.List;


public interface PreRegistroFacade {
    PreRegistroProveedorDto save(PreRegistroProveedorDto preRegistro) throws Exception;

    PreRegistroProveedorDto updateSearchSunat(PreRegistroProveedorDto dto);

    PreRegistroProveedorDto getPreRegistroByIdHcp(String idHcp);

    PreRegistroProveedorDto getPreRegistroById(Integer id);

    List<PreRegistroProveedorDto> getListSolicitudPendiente();

//    List<PreRegistroProveedorDto> getListSolicitudPendiente(String idHcp);

    PreRegistroProveedorDto aprobarSolicitud(Integer idPreRegistro);

    PreRegistroProveedorDto reprobarSolicitud(Integer idPreRegistro);

    PreRegistroProveedorDto aprobarSolicitud02(Integer idPreRegistro) throws Exception;

    PreRegistroProveedorDto reprobarSolicitud02(Integer idPreRegistro, String motivoRechazo) throws Exception;
}
