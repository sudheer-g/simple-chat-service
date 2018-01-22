import com.work.chatapp.Client;
import com.work.chatapp.Server;
import org.testng.annotations.Test;

public class ServerTest {
    @Test
    public void serverTest() {
        Server server = new Server(5000);
    }

    @Test
    public void clientTest() {
        Client client = new Client("127.0.0.1", 5000);
    }
}
