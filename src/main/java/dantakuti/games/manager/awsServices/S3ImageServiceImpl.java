package dantakuti.games.manager.awsServices;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author adarshbhattarai on 2020-05-30
 * @project LeagueManager
 */
@Service
public class S3ImageServiceImpl implements S3ImageServices {


    @Autowired
    AmazonS3 s3Client;

    @Value("${app.awsServices.bucketName}")
    private String bucketName;

    @Override
    public String uploadImage(File file, String path, String fileName) {
        
        s3Client.putObject(new PutObjectRequest(bucketName, path+"/"+fileName, file));
        return s3Client.getUrl(bucketName,path+"/"+fileName).toString();
    }



}
