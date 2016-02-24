import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@Singleton
@Startup
public class DbLogger {
    @Resource(name = "myDb")
    private DataSource ds;

    @PostConstruct
    private void log() {
        try (Connection connection = ds.getConnection()) {
            System.out.println(">>> " + connection.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}