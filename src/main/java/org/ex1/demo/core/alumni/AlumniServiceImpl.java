package org.ex1.demo.core.alumni;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.ex1.demo.controller.alumni.AlumniPostDTO;
import org.ex1.demo.controller.degree.DegreeDTO;
import org.ex1.demo.controller.education.EducationDTO;
import org.ex1.demo.persistance.alumni.AlumniRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AlumniServiceImpl implements AlumniService {

    private final AlumniRepository alumniRepository;
    private final AlumniMapper alumniMapper;

    @Override
    public AlumniPostDTO save(AlumniPostDTO alumniPostDTO) {
        return alumniMapper.mapEntityToDto(alumniRepository.save(alumniMapper.mapDtoToEntity(alumniPostDTO)));
    }

    @Override
    public List<AlumniPostDTO> findByName(String name, String degree) {
        if (ObjectUtils.isEmpty(degree)) {
            return alumniMapper.mapEntityListToDtoList(alumniRepository.findByName(name));
        } else {
            if(degree.equalsIgnoreCase("phd")){
                return alumniMapper.mapEntityListToDtoList(alumniRepository.findByName(name)).stream().filter(alumniPostDTO ->
                        ObjectUtils.isEmpty(alumniPostDTO.getEducation().getPhd()))
                    .toList();
            } else if (degree.equalsIgnoreCase("master")){
                return alumniMapper.mapEntityListToDtoList(alumniRepository.findByName(name)).stream().filter(alumniPostDTO ->
                        ObjectUtils.isEmpty(alumniPostDTO.getEducation().getMaster()))
                    .toList();
            }
            else return new ArrayList<>();
        }
    }
}
