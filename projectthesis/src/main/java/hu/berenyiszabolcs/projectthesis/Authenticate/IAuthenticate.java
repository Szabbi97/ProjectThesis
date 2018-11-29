package hu.berenyiszabolcs.projectthesis.Authenticate;

import org.json.JSONException;
import org.json.JSONObject;

public interface IAuthenticate {
    /**
     * This method is used to make a JSON object for server communication
     * from two String values.
     * @param emailValue This is the e-mail adress of the user
     * @param passValue  This is the password of the user
     * @return JSONObject This returns a JSON formatted object with the username, also the password hashed with SHA256.
     */
    JSONObject LoginByJSON(String emailValue, String passValue) throws JSONException;
}
