package org.ex1.demo.core.alumni;

import java.util.List;
import org.ex1.demo.controller.alumni.AlumniPostDTO;

public interface AlumniService {
    AlumniPostDTO save(AlumniPostDTO alumniPostDTO);
    List<AlumniPostDTO> findByName(String name, String degree);

}
