package edu.byu.cs.tweeter.server.dao;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import edu.byu.cs.tweeter.shared.model.domain.User;

public class DaoUtil {

    // How many follower users to add
    // We recommend you test this with a smaller number first, to make sure it works for you
    private final static int NUM_USERS = 10000;

    // The alias of the user to be followed by each user created
    // This example code does not add the target user, that user must be added separately.
    private final static String FOLLOW_TARGET = "@testMickey";

    public static byte[] hash(String rawPassword) throws InvalidKeySpecException, NoSuchAlgorithmException {

        // yes, we decided to hardcode this
        byte[] salt = new byte[]{82, 35, 6, 48,
                31, 93, 45, 94,
                40, 24, 55, 39,
                78, 74, 2, 29,
        };

        KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        return factory.generateSecret(spec).getEncoded();
    }

    /**
     * @param dbNum the Object returned by a dynamo outcome map
     * @return the converted int
     */
    public static int dynamoDBNumToInt(Object dbNum) {
        return ((BigDecimal) dbNum).intValue();
    }

    public static void fillDatabase() throws InvalidKeySpecException, NoSuchAlgorithmException {

        // Get instance of DAOs by way of the Abstract Factory Pattern
        UserDAO userDAO = getUserDAO();
        FollowDAO followDAO = getFollowDAO();

        List<String> followers = new ArrayList<>();
        List<User> users = new ArrayList<>();

        // Iterate over the number of users you will create
        for (int i = 1; i <= NUM_USERS; i++) {

            String name = "Guy " + i;
            String alias = "guy" + i;

            // Note that in this example, a UserDTO only has a name and an alias.
            // The url for the profile image can be derived from the alias in this example
            User user = new User();
            user.setAlias(alias);
            user.setFirstName(name);
            user.setLastName("Example");
            user.setImageUrl("https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
            users.add(user);

            // Note that in this example, to represent a follows relationship, only the aliases
            // of the two users are needed
            followers.add(alias);
        }

        // Call the DAOs for the database logic
        //if (users.size() > 0) {
        //    userDAO.addUserBatch(users);
        //}
        if (followers.size() > 0) {
            followDAO.addFollowersBatch(followers, FOLLOW_TARGET);
        }
    }

    private static FollowDAO getFollowDAO() { return new FollowDAO(); }
    private static UserDAO getUserDAO() { return new UserDAO(); }
}
