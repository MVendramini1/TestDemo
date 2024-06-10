package org.ex1.demo.core.degree;

import org.ex1.demo.controller.degree.DegreeDTO;
import org.ex1.demo.persistance.degree.DegreeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Named(value = "DegreeMapper")
@Mapper(componentModel = "spring")
public interface DegreeMapper {

    @Named(value = "mapDtoToEntity")
    DegreeEntity mapDtoToEntity(DegreeDTO degreeDTO);

    @Named(value = "mapEntityToDto")
    DegreeDTO mapEntityToDto(DegreeEntity degreeEntity);

}
