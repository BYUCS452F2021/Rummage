package edu.byu.cs.tweeter.server.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import edu.byu.cs.tweeter.shared.model.domain.AuthToken;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthTokenDAOTest {

    private AuthTokenDAO authTokenDAOToTest;
    private String currDateString;
    private String validUserAlias;

    @BeforeEach
    void setUp() {
        authTokenDAOToTest = new AuthTokenDAO();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        currDateString = ZonedDateTime.now().format(formatter);
        validUserAlias = "@testAuthTokenCreate";
    }

    @Test
    void create_whenNew_thenCreates() {
        AuthToken actual = authTokenDAOToTest.create(currDateString, validUserAlias);
        assertNotNull(actual);
        assertNotNull(actual.getKeyId());
    }

    @Test
    void read_whenCreate_thenReadHasSameDate() throws Exception {
        // setup
        AuthToken tempCreated = authTokenDAOToTest.create(currDateString, validUserAlias);

        String actual = authTokenDAOToTest.read(tempCreated);
        Assertions.assertEquals(currDateString, actual);
    }

    @Test
    void read_whenExists_thenReadReturnsExpectedDateString() throws Exception {
        AuthToken inputAuthToken = new AuthToken();
        inputAuthToken.setKeyId("47");

        String expected = "2021-04-13 22:17:52.469-06";
        String actual = authTokenDAOToTest.read(inputAuthToken);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void read_whenNotExists_thenThrows() {
        AuthToken inputAuthToken = new AuthToken();
        inputAuthToken.setKeyId("fakeKey");

        // String actual = authTokenDAOToTest.read(inputAuthToken);
        // Assertions.assertEquals("1", actual);

        Assertions.assertThrows(Exception.class, () -> authTokenDAOToTest.read(inputAuthToken));
    }

    @Test
    void delete_whenExists_thenIsRemoved() throws Exception {
        // setup
        AuthToken createdToken = authTokenDAOToTest.create(currDateString, validUserAlias);
        Assertions.assertNotNull(authTokenDAOToTest.read(createdToken));

        authTokenDAOToTest.delete(createdToken);
        Assertions.assertThrows(Exception.class, () -> authTokenDAOToTest.read(createdToken));
    }

}
