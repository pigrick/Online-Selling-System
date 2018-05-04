package edu.mum.cs490.project.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Erdenebayar on 5/4/2018
 */
public class SaveToFile {

    //Save the uploaded file to this folder
    @Value("${resource.dir}")
    private static String FILE_UPLOAD_PATH;

    public static String createFile(MultipartFile file, String objectType, Integer objectId) {
        try {
            String directoryPath = "";
            String savingPath = "";
            if (FILE_UPLOAD_PATH != null) {
                directoryPath = FILE_UPLOAD_PATH + File.separator + objectType + File.separator + objectId;
                savingPath = File.separator + objectType + File.separator + objectId;
                Path rootPath = Paths.get(directoryPath);
                if (!Files.exists(rootPath)) {
                    Files.createDirectories(rootPath);
                }
            }
            String filePath = File.separator + (objectId + "." + file.getOriginalFilename().split("\\.")[1]);
            String fileFullName = directoryPath + filePath;
            savingPath += filePath;
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileFullName);
            Files.write(path, bytes);
            System.out.println("Successfully created file - " + fileFullName);
            return savingPath;
        } catch (IOException ex) {
            ex.getMessage();
        }
        return null;
    }
}
