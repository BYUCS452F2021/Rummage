package edu.byu.cs.tweeter.server.dao;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

//import java.util.List;

//import edu.byu.cs.tweeter.shared.model.domain.FollowCount;
//import edu.byu.cs.tweeter.shared.model.domain.User;
//import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
/*

class UserDaoTest {

    private UserDAO userDaoToTest;

    @BeforeEach
    void setUp() {
        userDaoToTest = new UserDAO();
    }

    @Test
    void testCreateUser_whenValidRequest_thenExpectedObjects() throws Exception {
        // specialized setup
        userDaoToTest.deleteUser("@testCreateUser");

        User expectedUser = new User("Creating", "User", "@testCreateUser", "www.fakeurl.com");
        FollowCount expectedCount = new FollowCount();
        expectedCount.setNumFollowing(0);
        expectedCount.setNumFollowers(0);

        SignUpRequest validRequest = new SignUpRequest("@testCreateUser",
                        "badPassword",
                        "Creating",
                        "User",
                        "www.fakeurl.com");
        List<Object> actual = userDaoToTest.createUser(validRequest);
        User actualUser = (User) actual.get(0);
        FollowCount actualCount = (FollowCount) actual.get(1);
        Assertions.assertEquals(expectedUser, actualUser);
        Assertions.assertEquals(expectedCount, actualCount);
    }

    @Test
    void testCreateUser_whenAlreadyExists_thenThrows() {
        SignUpRequest duplicateAliasRequest = new SignUpRequest("@testDuplicateCreate",
                "badPassword2",
                "A",
                "B",
                "www.fakeurl2.com");
        Assertions.assertThrows(Exception.class, () -> userDaoToTest.createUser(duplicateAliasRequest));
    }

    @Test
    void testReadUser_whenValidUserAlias_thenExpectedUserObject() throws Exception {
        User expectedUser = new User("Donald", "Duck",
                "@testUser", "www.example3.com");
        FollowCount expectedCount = new FollowCount("@testUser");
        expectedCount.setNumFollowers(17);
        expectedCount.setNumFollowing(42);

        String validUserAlias = "@testUser";
        List<Object> actual = userDaoToTest.readUser(validUserAlias);
        User actualUser = (User) actual.get(0);
        FollowCount actualCount = (FollowCount) actual.get(1);
        Assertions.assertEquals(expectedUser, actualUser);
        Assertions.assertEquals(expectedCount, actualCount);
    }

    @Test
    void testReadUser_whenUserAliasNotExist_thenThrow() {
        String badUserAlias = "@asldkfjoiealskdjfalsdkjfie";
        Assertions.assertThrows(Exception.class, () -> userDaoToTest.readUser(badUserAlias));
    }

    @Test
    void testUpdateUserCount_whenValidAlias_thenCountsChange() throws Exception {
        // setup
        userDaoToTest.deleteUser("@testCountChange");
        userDaoToTest.createUser(new SignUpRequest("@testCountChange",
                "anotherBadPassword",
                "first",
                "last",
                "imageUrl.com")
        );

        FollowCount expectedNewCount = new FollowCount();
        expectedNewCount.setNumFollowers(101);
        expectedNewCount.setNumFollowing(99);

        // do test
        FollowCount actual = userDaoToTest.updateUserCounts("@testCountChange", 101, 99);
        Assertions.assertEquals(expectedNewCount, actual);
    }

}
*/
