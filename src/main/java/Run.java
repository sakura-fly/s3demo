import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

public class Run {
    String accessKey = "JQUDUKZ3P2K6N2NAFH68";
    String secretKey = "aaoiD4rdtErqiHNLcN0cjys45CAcvD8DLhItcTLk";
    // String BUCKET_NAME = "my-first-s3-bucket1";
    String BUCKET_NAME = "gqy";
    String fileName = "hello.txt";

    @Test
    public void run() {
        AmazonS3 c = createConnection();
        getUrl(c,BUCKET_NAME,fileName);

        // downObject(c, BUCKET_NAME);

        // createObject(c, BUCKET_NAME);
        // listBucketContent(c, BUCKET_NAME);
        // createBucket("gqy", c);
        // List<Bucket> l = listBuckets(c);
        // for (Bucket b : l) {
        //     System.out.println(b);
        // }
    }

    // 创建连接
    public AmazonS3 createConnection() {


        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3 conn = AmazonS3ClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "http://192.168.1.124:7480", ""
                        )
                )
                .build();
        return conn;
    }

    // 桶列表
    public List<Bucket> listBuckets(AmazonS3 conn) {
        return conn.listBuckets();

    }

    // 创建桶
    public void createBucket(String name, AmazonS3 conn) {
        conn.createBucket(name);
    }


    // 桶内容列表
    public void listBucketContent(AmazonS3 conn, String name) {
        ObjectListing objects = conn.listObjects(name);
        do {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                // System.out.println(objectSummary);
                System.out.println(objectSummary);
            }
            objects = conn.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());
    }

    // 创建对象
    public void createObject(AmazonS3 conn, String bucketName) {
        ByteArrayInputStream input = new ByteArrayInputStream("Hello World!".getBytes());
        conn.putObject(bucketName, "hello.txt", input, new ObjectMetadata());
    }

    // 下载对象
    public void downObject(AmazonS3 conn, String bucketName) {
        conn.getObject(
                new GetObjectRequest(bucketName, "hello.txt"),
                new File("G:\\test\\s3\\hello.txt")
        );
    }
    // 获取下载链接
    public String getUrl(AmazonS3 conn, String bucketName,String fileName){
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName);
        System.out.println(conn.generatePresignedUrl(request));
        return conn.generatePresignedUrl(request).toString();
    }
}
