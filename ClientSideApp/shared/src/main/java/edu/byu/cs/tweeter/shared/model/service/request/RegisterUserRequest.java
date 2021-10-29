package edu.byu.cs.tweeter.shared.model.service.request;

/**
 * Contains all the information needed to make signUp requests.
 */
public class RegisterUserRequest extends LoginRequest {

    private String firstName;
    private String lastName;
    private String imageUrl;

    public RegisterUserRequest() {}

    /**
     * Creates an instance.
     *
     * @param alias     the alias of the new user
     * @param password  the password of the new user
     * @param firstName the first name of the new user
     * @param lastName  the last name of the new user
     * @param imageUrl  a url for the new user's profile image
     */
    public RegisterUserRequest(String alias, String password, String firstName, String lastName, String imageUrl) {
        super(alias, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
