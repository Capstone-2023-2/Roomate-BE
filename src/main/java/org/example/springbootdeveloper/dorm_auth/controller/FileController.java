package org.example.springbootdeveloper.dorm_auth.controller;

import org.example.springbootdeveloper.dorm_auth.domain.FileEntity;
import org.example.springbootdeveloper.dorm_auth.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class FileController {
    private final FileRepository fileRepository;
    String filepath = "/Users/baesumin/Desktop/capston_backend/mate_backend/src/main/resources/static/images";

    /**
     *  이미지 업로드
     * @return Map<String, Object>
     */
    @PostMapping("/auth/image")
    public FileEntity uploadImage(HttpServletRequest request,
                                  @RequestParam(value="file", required = false) MultipartFile[] files, Principal principal) {

        String FileNames = "";

        String originFileName = files[0].getOriginalFilename();
        long fileSize = files[0].getSize();
        String safeFile = System.currentTimeMillis() + originFileName;

        File f1 = new File(filepath + safeFile);
        try {
            files[0].transferTo(f1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final FileEntity file = FileEntity.builder()
                .filename(safeFile)
                .user_id(principal.getName())
                .build();

        return fileRepository.save(file);
    }

}

