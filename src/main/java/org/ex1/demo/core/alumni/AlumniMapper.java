package org.ex1.demo.core.alumni;

import java.util.List;
import org.ex1.demo.controller.alumni.AlumniPostDTO;
import org.ex1.demo.core.address.AddressMapper;
import org.ex1.demo.core.degree.DegreeMapper;
import org.ex1.demo.core.education.EducationMapper;
import org.ex1.demo.persistance.alumni.AlumniEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
    uses = {DegreeMapper.class, EducationMapper.class, AddressMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AlumniMapper {

    @Named(value = "mapDtoToEntity")
    @Mapping(source = "addresses", target = "addresses", qualifiedByName = {"AddressMapper", "mapDtoListToEntityList"})
    @Mapping(source = "education", target = "education", qualifiedByName = {"EducationMapper", "mapDtoToEntity"})
    AlumniEntity mapDtoToEntity(AlumniPostDTO alumniPostDTO);

    @Named(value = "mapEntityToDto")
    @Mapping(source = "addresses", target = "addresses", qualifiedByName = {"AddressMapper", "mapEntityListToDtoList"})
    @Mapping(source = "education", target = "education", qualifiedByName = {"EducationMapper", "mapEntityToDto"})
    AlumniPostDTO mapEntityToDto(AlumniEntity alumniEntity);

    @IterableMapping(qualifiedByName = "mapEntityToDto")
    List<AlumniPostDTO> mapEntityListToDtoList(List<AlumniEntity> alumniEntities);

    @IterableMapping(qualifiedByName = "mapDtoToEntity")
    List<AlumniEntity> mapDtoListToEntityList(List<AlumniPostDTO> alumniPostDTOList);
}
