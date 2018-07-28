import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.StringUtils;
import org.junit.Test;

import java.util.List;

public class Run {
    String accessKey = "JQUDUKZ3P2K6N2NAFH68";
    String secretKey = "aaoiD4rdtErqiHNLcN0cjys45CAcvD8DLhItcTLk";
    String BUCKET_NAME = "my-first-s3-bucket1";
    // String BUCKET_NAME = "gqy";

    @Test
    public void run() {
        AmazonS3 c = createConnection();
        // listBucketContent(c, BUCKET_NAME);
        // createBucket("gqy", c);
        List<Bucket> l = listBuckets(c);
        for (Bucket b : l) {
            System.out.println(b);
        }
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
                System.out.println(objectSummary.getKey() + "\t" +
                        objectSummary.getSize() + "\t" +
                        StringUtils.fromDate(objectSummary.getLastModified()));
            }
            objects = conn.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());
    }

}
