import com.work.chatapp.Server;
import org.testng.annotations.Test;

public class ServerTest {
    @Test
    public void serverTest() {
        Server server = new Server();
        System.out.println(server.test);
    }
}
