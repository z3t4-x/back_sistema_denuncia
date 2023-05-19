package com.dev.dto.converters;

import com.dev.domain.Denuncia;
import com.dev.dto.DenunciaDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum DenunciaToEntity implements Function<DenunciaDTO, Denuncia> {

    INSTANCE;

    @Override
    public Denuncia apply(DenunciaDTO dto) {
        Denuncia entity = new Denuncia();

        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);

            if (dto.getFiscalia() != null) {
                entity.setFiscalia(CatalogosValoresToEntity.INSTANCE.apply(dto.getFiscalia()));
            }
            if (dto.getTipoDelito() != null) {
                entity.setTipoDelito(CatalogosValoresToEntity.INSTANCE.apply(dto.getTipoDelito()));
            }
            if (dto.getAuxiliar() != null) {
                entity.setAuxiliar(CatalogosValoresToEntity.INSTANCE.apply(dto.getAuxiliar()));
            }
            if (dto.getEstadoDenuncia() != null) {
                entity.setEstadoDenuncia(CatalogosValoresToEntity.INSTANCE.apply(dto.getEstadoDenuncia()));
            }
            if (dto.getMesaParte() != null) {
                entity.setMesaParte(CatalogosValoresToEntity.INSTANCE.apply(dto.getMesaParte()));
            }
            if (dto.getTipoDocumento() != null) {
                entity.setTipoDocumento(CatalogosValoresToEntity.INSTANCE.apply(dto.getTipoDocumento()));
            }

        }

        return entity;
    }

}
