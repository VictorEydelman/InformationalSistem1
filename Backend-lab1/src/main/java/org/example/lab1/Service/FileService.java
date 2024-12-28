package org.example.lab1.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.*;
import java.time.Instant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.lab1.entities.LabWork;
import org.example.lab1.entities.User;
import org.example.lab1.entities.DTO.FileforSaveDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FileService {
    private final MinioClient minioClient;
    @SneakyThrows
    public String createUserBucket(User user) {
        String bucket = "u" + user.getUsername().toLowerCase();
        if(!minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucket).build())){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
        return bucket ;
    }

    @SneakyThrows
    @Transactional
    public String uploadUserFile(MultipartFile file, User user) {
        String bucket = createUserBucket(user);
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        String extension = "";
        String fileName = file.getOriginalFilename();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = fileName.substring(dotIndex);
            fileName=fileName.substring(0, dotIndex);
        }
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(fileName + "_" + timestamp + extension)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build());
        return fileName + "_" + timestamp + extension;

    }
    @SneakyThrows
    public byte[] readFile(String filename,User user) {
        String bucket = createUserBucket(user);
        var object = minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(filename)
                .build());
        return object.readAllBytes();
    }
}
