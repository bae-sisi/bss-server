package com.example.bssBack.controller;


import com.example.bssBack.entity.Professor;
import com.example.bssBack.service.ProfessorService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfessorController {


    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }


    @GetMapping("/get/all/profInfo")
    public List<Professor> getAllProfessor(){
        return professorService.GetAllProfINFO();
    }

    @PostMapping("/save/prof/Info")
    public Long saveProf(@RequestParam("name") String name,
                                   @RequestParam("IsOfficier") Boolean Isofficier,
                                   @RequestParam("LabINTRO") String LabINTRO,
                                   @RequestParam("EduBackGround") String EduBackGround,
                                   @RequestParam("office") String office,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("major") String major,
                                   @RequestParam("LabLink") String LabLink,
                                   @RequestParam("email") String email) throws Exception {


            Professor professor = new Professor(name, Isofficier, LabINTRO, EduBackGround, office, phone, major, LabLink, email);

            System.out.println(professor);

            return professorService.SaveProfINFO(professor);
    }

    @PostMapping("/save/{id}/image")
    public ResponseEntity saveProfImg(@PathVariable("id") Long id,
                            @RequestParam(value = "img", required = false)MultipartFile img) throws Exception{
        try {

            professorService.saveimg(id, img);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "강의 등록에 성공하였습니다.");
            return new ResponseEntity(result, HttpStatus.ACCEPTED);

        }catch (Error e){

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "개설 교과목 등록에 실패하였습니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/prof/{name}/profileImg", produces = MediaType.ALL_VALUE)
    @ResponseBody
    public FileSystemResource getProfileImg(@PathVariable("name") String name) throws Exception {
        String path = System.getProperty("user.dir") +
                String.format("/src/main/resources/profile/%s.jpg", name);

        return (new File(path).exists()) ? new FileSystemResource(path) : null;
    }




}
