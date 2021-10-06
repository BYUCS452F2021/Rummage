package edu.byu.cs.tweeter.shared.model.domain;

import java.util.Objects;

/*
 * Represents a relationship between a user, the number of users they are following,
 * and the number of users who are following them.
 */
public class FollowCount {

    //private final User currUser;
    String userAlias;
    private int numFollowing;
    private int numFollowers;

    public FollowCount() {}

    /*
     * Constructor to create a FollowsCount object
     */
    public FollowCount(String userAlias) { this.userAlias = userAlias; }

    // Getters and Setters


    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getUserAlias() { return userAlias; }

    public int getNumFollowing() { return numFollowing; }

    public int getNumFollowers() { return numFollowers; }

    public void setNumFollowing(int numFollowing) { this.numFollowing = numFollowing; }

    public void setNumFollowers(int numFollowers) { this.numFollowers = numFollowers; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowCount that = (FollowCount) o;
        return getNumFollowing() == that.getNumFollowing() &&
                getNumFollowers() == that.getNumFollowers();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserAlias(), getNumFollowing(), getNumFollowers());
    }
}
