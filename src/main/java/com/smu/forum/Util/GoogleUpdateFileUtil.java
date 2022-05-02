package com.smu.forum.Util;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GoogleUpdateFileUtil {

    private final static String bucketName = "forum-project";
    private static Storage storage = null;
    private static String imgUrl = null;

    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    @SuppressWarnings("deprecation")
    public static String uploadFile(MultipartFile multipartFile, String prefix) throws IOException  {

        DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYYMMddHHmmss");
        DateTime dt = DateTime.now(DateTimeZone.UTC);
        String dtString = dt.toString(dtf);
        final String fileName =
                prefix + dtString +
                multipartFile.getOriginalFilename().substring(
                        multipartFile.getOriginalFilename().indexOf("."),
                        multipartFile.getOriginalFilename().length()
                );

        BlobInfo blobInfo =
            storage.create(
                BlobInfo
                    .newBuilder(bucketName, fileName)
                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                    .setContentType("image/jpeg")
                    .build(),
                multipartFile.getInputStream());

        imgUrl = "https://storage.googleapis.com/" + bucketName + "/" + fileName;
        return imgUrl;
    }

}
