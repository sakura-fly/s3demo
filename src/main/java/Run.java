import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.junit.Test;

public class Run {
    String accessKey = "JQUDUKZ3P2K6N2NAFH68";
    String secretKey = "aaoiD4rdtErqiHNLcN0cjys45CAcvD8DLhItcTLk";
    @Test
    public void run() {

    }

    public void createConnection() {


        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 conn = new AmazonS3Client(credentials);
        conn.setEndpoint("objects.dreamhost.com");
    }

}
