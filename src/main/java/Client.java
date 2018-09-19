import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.junit.Test;

import java.util.Collection;

public class Client {


    String username = "bw0a01:bw0a01";
    String password = "bw0a01@bobdz.com";

    // String authUrl  = "http://192.168.1.124:7480/auth/1.0";
    String authUrl  = "http://he.bobdz.com:8000/auth/1.0";

    @Test
    public void testConnection(){

        AccountConfig config = new AccountConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setAuthUrl(authUrl);
        config.setAuthenticationMethod(AuthenticationMethod.BASIC);
        Account account = new AccountFactory(config).createAccount();
        // Container container = account.getContainer("gqy");
        // container.create();


        Collection<Container> containers = account.list();
        for (Container currentContainer : containers) {
            System.out.println(currentContainer.getName());
        }

        Container container = account.getContainer("gqy1");

        Collection<StoredObject> objects = container.list();
        for (StoredObject currentObject : objects) {
            System.out.println(currentObject.getName());
        }
    }


}
