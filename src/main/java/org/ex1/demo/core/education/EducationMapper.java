package org.ex1.demo.core.education;

import org.ex1.demo.controller.education.EducationDTO;
import org.ex1.demo.persistance.education.EducationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Named(value = "EducationMapper")
@Mapper(componentModel = "spring")
public interface EducationMapper {

    @Named(value = "mapDtoToEntity")
    EducationEntity mapDtoToEntity(EducationDTO educationDTO);

    @Named(value = "mapEntityToDto")
    EducationDTO mapEntityToDto(EducationEntity educationEntity);

}
