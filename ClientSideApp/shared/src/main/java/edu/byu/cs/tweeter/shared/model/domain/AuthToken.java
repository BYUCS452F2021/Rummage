package edu.byu.cs.tweeter.shared.model.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an auth token in the system.
 */
public class AuthToken implements Serializable {
    private String keyId;

    public AuthToken() {}

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(keyId, authToken.keyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyId);
    }
}
