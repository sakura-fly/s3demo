import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import org.junit.Test;

import java.util.List;

public class Run {
    String accessKey = "JQUDUKZ3P2K6N2NAFH68";
    String secretKey = "aaoiD4rdtErqiHNLcN0cjys45CAcvD8DLhItcTLk";

    @Test
    public void run() {
        AmazonS3 c = createConnection();
        // createBucket("gqy", c);
        List<Bucket> l = listBuckets(c);
        for (Bucket b : l) {
            System.out.println(b);
        }
    }

    // @Test
    public AmazonS3 createConnection() {


        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        AmazonS3 conn = new AmazonS3Client(credentials);
        conn.setEndpoint("http://192.168.1.124:7480");
        return conn;
    }

    public List<Bucket> listBuckets(AmazonS3 conn) {
        return conn.listBuckets();

    }

    public void createBucket(String name, AmazonS3 conn) {
        conn.createBucket(name);
    }

}
