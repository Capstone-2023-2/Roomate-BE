package org.example.springbootdeveloper.dorm_auth.controller;

import jakarta.mail.MessagingException;
import org.example.springbootdeveloper.dorm_auth.domain.DormAuthEntity;
import org.example.springbootdeveloper.dorm_auth.repository.DormAuthRepository;
import org.example.springbootdeveloper.email.dto.EmailAuthRequestDto;
import org.example.springbootdeveloper.dorm_auth.service.FileSendService;
import org.example.springbootdeveloper.dorm_auth.domain.FileEntity;
import org.example.springbootdeveloper.dorm_auth.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class FileController {
    private final FileRepository fileRepository;
    private final FileSendService fileSendService;
    private final DormAuthRepository dormAuthRepository;
    String filepath = "/Users/baesumin/Desktop/capston_backend/mate_backend/src/main/resources/static/images";

    /**
     *  이미지 업로드
     * @return Map<String, Object>
     */
    @PostMapping("/auth/dorm/image")
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
                .userId(principal.getName())
                .build();

        return fileRepository.save(file);
    }

    @PostMapping("/auth/dorm/mail")
    public ResponseEntity<String> mailConfirm(Principal principal) throws MessagingException, UnsupportedEncodingException {
        String userId = principal.getName();
        Optional<FileEntity> fileEntityOptional = fileRepository.findByUserId(userId);
        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            String filename = fileEntity.getFilename();
        }


        final DormAuthEntity dormAuth = DormAuthEntity.builder()
                .userId(principal.getName())
                .status(true)
                .build();

        dormAuthRepository.save(dormAuth);

        fileSendService.sendDormEmail(userId);



        return ResponseEntity.ok("Email send successfully");
    }
/*
    @PutMapping("/updateDormAuthStatus")
    public ResponseEntity<String> updateDormAuthStatus(Principal principal) {
        String userId = principal.getName();

        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.save(User.builder()
                    .dorm_auth_status(true)
                            .build()).getId();
        }
        return ResponseEntity.ok("Email send successfully");
    }

 */

}

