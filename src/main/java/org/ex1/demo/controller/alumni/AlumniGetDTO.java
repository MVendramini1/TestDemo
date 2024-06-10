package org.ex1.demo.controller.alumni;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlumniGetDTO {
    Integer totalCount;
    List<AlumniPostDTO> data;
}
