package edu.byu.cs.tweeter.server.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;

class FollowDAOTest {

    private FollowDAO followDAOToTest;

    @BeforeEach
    void setUp() {
        followDAOToTest = new FollowDAO();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createRecord_whenNew_thenReturnsRecordStrings() {
        // setup
        followDAOToTest.deleteRecord("@testCreateFollower", "@testCreateFollowee");

        // do test
        List<String> expected = new ArrayList<>();
        expected.add("@testCreateFollower");
        expected.add("@testCreateFollowee");
        List<String> actual = followDAOToTest.createRecord("@testCreateFollower", "@testCreateFollowee");
        Assertions.assertEquals(expected, actual);
    }

    @Nested
    class PagedFolloweesTest {

        @Test
        void getPagedFolloweeAliases_whenValidLittleEntries_thenGetsSmallerPage() {
            List<String> expected = new ArrayList<>();
            expected.add("@testFollowee1");
            expected.add("@testFollowee2");
            expected.add("@testFollowee3");
            expected.add(null);

            List<String> actual = followDAOToTest.getPagedFolloweeAliases("@testGetSmallPage", null);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        void getPagedFolloweeAliases_whenValidManyEntries_thenStopsAtTen() {
            List<String> actual = followDAOToTest.getPagedFolloweeAliases("@testUser", null);
            // 10 results plus 1 extra for lastRetrieved
            Assertions.assertEquals(10 + 1, actual.size());
        }

        @Test
        void getPagedFolloweeAliases_whenUseLastRetrieved_thenStartNewPage() {
            List<String> expected = new ArrayList<>();
            expected.add("@testFollowee91");
            expected.add(null);

            List<String> actual = followDAOToTest.getPagedFolloweeAliases("@testUser", "@testFollowee90");
            Assertions.assertEquals(expected, actual);
        }

        @Test
        void getPagedFolloweeAliases_whenInvalid_thenListEmpty() {
            List<String> expected = new ArrayList<>();
            expected.add(null);

            List<String> actual = followDAOToTest.getPagedFolloweeAliases("@alsdkfjlasdjf", null);
            Assertions.assertEquals(expected, actual);
        }

    }

    @Nested
    class PagedFollowersTest {

        @Test
        void getPagedFolloweeAliases_whenValidLittleEntries_thenGetsSmallerPage() {
            List<String> actual = followDAOToTest.getPagedFollowerAliases("@testGetSmallPage", null);
            Assertions.assertTrue(actual.contains("@testFollower1"));
            Assertions.assertTrue(actual.contains("@testFollower2"));
            Assertions.assertTrue(actual.contains("@testFollower3"));
            Assertions.assertTrue(actual.contains(null));
        }

        @Test
        void getPagedFollowerAliases_whenValidManyEntries_thenStopsAtTen() {
            List<String> actual = followDAOToTest.getPagedFollowerAliases("@testFullPage", null);
            // 10 results plus 1 extra for lastRetrieved
            Assertions.assertEquals(10 + 1, actual.size());
        }

        @Test
        void getPagedFollowerAliases_whenUseLastRetrieved_thenStartNewPage() {
            List<String> expected = new ArrayList<>();
            expected.add("@testFollower01");
            expected.add(null);

            List<String> actual = followDAOToTest.getPagedFollowerAliases("@testFullPage", "@testFollower02");
            Assertions.assertEquals(expected, actual);
        }

        @Test
        void getPagedFollowerAliases_whenInvalid_thenListEmpty() {
            List<String> expected = new ArrayList<>();
            expected.add(null);

            List<String> actual = followDAOToTest.getPagedFollowerAliases("@alsdkfjlasdjf", null);
            Assertions.assertEquals(expected, actual);
        }

    }

    @Test
    void deleteRecord_whenExists_thenGone() {
        //setup
        String followerAlias = "@testDeleteFollower";
        String followeeAlias = "@testDeleteFollowee";
        followDAOToTest.createRecord(followerAlias, followeeAlias);
        Assertions.assertEquals(2, followDAOToTest.getPagedFolloweeAliases(followerAlias, null).size());

        // do test
        followDAOToTest.deleteRecord(followerAlias, followeeAlias);
        Assertions.assertEquals(1, followDAOToTest.getPagedFolloweeAliases(followerAlias, null).size());
    }

    @Test
    void deleteRecord_whenNotExists_thenDoNothing() {
        //setup
        String followerAlias = "@testFakeDeleteFollower";
        String followeeAlias = "@testFakeDeleteFollowee";
        Assertions.assertEquals(1, followDAOToTest.getPagedFolloweeAliases(followerAlias, null).size());

        // do test
        followDAOToTest.deleteRecord(followerAlias, followeeAlias);
        Assertions.assertEquals(1, followDAOToTest.getPagedFolloweeAliases(followerAlias, null).size());
    }

    @Test
    void changeRelationship_whenRequestToFollow_thenAddsToDb() {
        //setup
        AuthToken tempAuth = new AuthToken();
        RelationshipChangeRequest request = new RelationshipChangeRequest("@testChangeRelationshipCurr",
                "@testChangeRelationshipOther",
                true);
        request.setAuthToken(tempAuth);
        RelationshipChangeResponse expected = new RelationshipChangeResponse(tempAuth,true);

        RelationshipChangeResponse actual = followDAOToTest.changeRelationship(request);
        Assertions.assertEquals(expected.getIsFollowRelationship(), actual.getIsFollowRelationship());
        Assertions.assertEquals(expected.getAuthToken(), actual.getAuthToken());
        Assertions.assertEquals(2, followDAOToTest.getPagedFolloweeAliases("@testChangeRelationshipCurr", null).size());
    }

    @Test
    void changeRelationship_whenRequestToUnfollow_thenDeletesFromDb() {
        //setup
        AuthToken tempAuth = new AuthToken();
        RelationshipChangeRequest request = new RelationshipChangeRequest("@testChangeRelationshipCurr",
                "@testChangeRelationshipOther",
                false);
        request.setAuthToken(tempAuth);
        RelationshipChangeResponse expected = new RelationshipChangeResponse(tempAuth,false);

        RelationshipChangeResponse actual = followDAOToTest.changeRelationship(request);
        Assertions.assertEquals(expected.getIsFollowRelationship(), actual.getIsFollowRelationship());
        Assertions.assertEquals(expected.getAuthToken(), actual.getAuthToken());
        Assertions.assertEquals(1, followDAOToTest.getPagedFolloweeAliases("@testChangeRelationshipCurr", null).size());
    }

}
