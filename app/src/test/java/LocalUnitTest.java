import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import com.example.myapplication.ClassListMachines;
import com.example.myapplication.ClassListResults;
import com.example.myapplication.MainActivity;
import com.example.myapplication.SearchById;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class LocalUnitTest {

    @Test
    public void connectToDatabase() {
        final String DB_URL = "jdbc:postgresql://10.0.2.2:5432/Toolbox";
        final String USER = "postgres";
        final String PASS = "postgresql";

        /* Get the Connection object. */
        try {

            /* Register jdbc driver class. */
            Class.forName("org.postgresql.Driver");

            /* Get the Connection object. */
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT 1");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
            assertFalse(rs.next());
        } catch (Exception e) {
            System.out.print(e);
            fail();
        }

    }

}
