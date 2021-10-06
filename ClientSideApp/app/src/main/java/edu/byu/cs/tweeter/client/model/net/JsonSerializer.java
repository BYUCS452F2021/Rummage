package edu.byu.cs.tweeter.client.model.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * Contains the business logic to serialize and deserialize an object
 */
public class JsonSerializer {

    /*
     * Serializes an object into Json
     * @param requestInfo the object to be serialized
     */
    public static String serialize(Object requestInfo) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gsonObject = builder.create();
        return gsonObject.toJson(requestInfo);
//        return (new Gson()).toJson(requestInfo);
    }

    /*
     * Deserializes a Json object into into an object of that type
     * @param value
     * @param returnType
     */
    public static <T> T deserialize(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }
}
