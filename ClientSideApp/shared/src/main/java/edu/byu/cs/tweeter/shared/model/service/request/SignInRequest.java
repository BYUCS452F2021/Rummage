package edu.byu.cs.tweeter.shared.model.service.request;

/**
 * Contains all the information needed to make signIn requests.
 */
public class SignInRequest {

    private String alias;
    private String password;

    public SignInRequest() {}

    /**
     * Creates an instance.
     *
     * @param alias    the alias of the user to be logged in.
     * @param password the password of the user to be logged in.
     */
    public SignInRequest(String alias, String password) {
        this.alias = alias;
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
