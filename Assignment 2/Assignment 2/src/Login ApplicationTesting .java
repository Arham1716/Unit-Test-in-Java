import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginAppTest {

    private LoginApp loginAppInstance;

    @BeforeEach
    void prepare() {
        loginAppInstance = new LoginApp();
    }

    @Test
    void checkSuccessfulAuthentication() throws Exception {
        // Case: Valid email address, user exists in records
        String email = "tomclark@example.com";
        String expectedUser = "Tom Clark";

        String retrievedUser = loginAppInstance.authenticateUser(email);

        assertEquals(expectedUser, retrievedUser, "The returned user should match the one in the records.");
    }

    @Test
    void checkFailedAuthentication() throws Exception {
        // Case: Valid email address, but user not found in records
        String email = "unknown@example.com";

        String retrievedUser = loginAppInstance.authenticateUser(email);

        assertNull(retrievedUser, "The returned value should be null if no user is found.");
    }

    @Test
    void checkInvalidEmailFormat() throws Exception {
        // Case: Email address is improperly formatted
        String email = "not-an-email";

        String retrievedUser = loginAppInstance.authenticateUser(email);

        assertNull(retrievedUser, "The returned value should be null for an invalid email format.");
    }

    @Test
    void checkDatabaseError() throws Exception {
        // Case: Simulated database connectivity issue
        String email = "testuser@example.com";

        String retrievedUser = loginAppInstance.authenticateUser(email);

        assertNull(retrievedUser, "The returned value should be null in case of a database connectivity issue.");
    }

    @Test
    void checkSQLInjectionAttempt() throws Exception {
        // Case: Attempt to perform SQL injection
        String email = "malicious@example.com' OR '1'='1";

        String retrievedUser = loginAppInstance.authenticateUser(email);

        assertNull(retrievedUser, "The returned value should be null for a SQL injection attempt.");
    }
}
