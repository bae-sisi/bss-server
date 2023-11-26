package com.example.bssBack.service;

import com.example.bssBack.entity.Professor;
import com.example.bssBack.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    public List<Professor> GetAllProfINFO(){
        return professorRepository.findAll();
    }

    public Long SaveProfINFO(Professor professor) throws Exception {
        if(!professor.getName().isBlank()){
            return professorRepository.save(professor).getPid();
        }else{
            throw new Exception("Professor Name is Empty");
        }
    }

    public Professor FindProfINFO(String name){
        return professorRepository.findProfessorByName(name);
    }


    public void saveimg(Long id, MultipartFile img) throws Exception {

        String name = professorRepository.findProfessorNameByID(id);

        String profileImage = "man1";
        String directoryPath = System.getProperty("user.dir") +
                "/src/main/resources/profile/";

        if(!new File(directoryPath).exists())
            new File((directoryPath)).mkdir();

        if(img != null){
            try{
                String filePath = directoryPath + name + ".jpg";
                if(new File(filePath).exists())
                    new File(filePath).delete();

                img.transferTo(new File(filePath));
            }catch (Exception e){
                throw new Exception("이미지 등록 실패/ 기본 이미지로 대채됩니다");
            }
        }

        String filePath = directoryPath + profileImage + ".jpg";

        if(new File(filePath).exists())
            new File(filePath).delete();

        String originPath = System.getProperty("user.dir") +
                String.format("/src/main/resources/profile/default/%s.jpg", profileImage);
        File origin = new File(originPath);

        String destPath = System.getProperty("user.dir") +
                String.format("/src/main/resources/profile/%s.jpg", name);
        File dest = new File(destPath);
        FileCopyUtils.copy(origin, dest);
    }

}
