package org.ex1.demo.core.address;

import java.util.List;
import org.ex1.demo.controller.address.AddressDTO;
import org.ex1.demo.persistance.address.AddressEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Named(value = "AddressMapper")
@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Named(value = "mapDtoToEntity")
    AddressEntity mapDtoToEntity(AddressDTO addressDTO);

    @Named(value = "mapEntityToDto")
    AddressDTO mapEntityToDto(AddressEntity addressEntity);

    @IterableMapping(qualifiedByName = "mapEntityToDto")
    @Named(value = "mapEntityListToDtoList")
    List<AddressDTO> mapEntityListToDtoList(List<AddressEntity> addressEntities);

    @IterableMapping(qualifiedByName = "mapDtoToEntity")
    @Named(value = "mapDtoListToEntityList")
    List<AddressEntity> mapDtoListToEntityList(List<AddressDTO> addressDTOList);

}
