package org.ex1.demo.controller.alumni;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ex1.demo.core.alumni.AlumniService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alumni")
public class AlumniController {

    private final AlumniService alumniService;

    @GetMapping
    public ResponseEntity<AlumniGetDTO> findByName(@RequestParam String name, @RequestParam(required = false) String degree) {
        List<AlumniPostDTO> alumniPostDTOS = alumniService.findByName(name, degree);
        if (alumniPostDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(AlumniGetDTO.builder().data(alumniPostDTOS).totalCount(alumniPostDTOS.size()).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlumniPostDTO> insert(@RequestBody @Valid AlumniPostDTO alumniPostDTO) {
        return new ResponseEntity<>(alumniService.save(alumniPostDTO), HttpStatus.CREATED);
    }


}
