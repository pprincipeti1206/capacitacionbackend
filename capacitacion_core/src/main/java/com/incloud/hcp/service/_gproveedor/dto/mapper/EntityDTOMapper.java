package com.incloud.hcp.service._gproveedor.dto.mapper;

/**
 * Created by Joel on 02/09/2017.
 */
public interface EntityDTOMapper<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
}
