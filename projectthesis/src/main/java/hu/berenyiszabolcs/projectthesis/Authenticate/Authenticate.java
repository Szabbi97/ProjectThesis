package hu.berenyiszabolcs.projectthesis.Authenticate;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.common.hash.Hashing;

import org.json.JSONException;
import org.json.JSONObject;

import hu.berenyiszabolcs.projectthesis.R;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <h1>Authenticate</h1>
 * The Authenticate class implements methods that
 * connected to authenticating a user.
 *
 * @author  Berényi Szabolcs
 * @version 1.0
 * @since   2018-11-29
 */
public class Authenticate implements IAuthenticate {
    /**
     * This method is used to make a JSON object for server communication
     * from two String values.
     * @param emailValue This is the e-mail adress of the user
     * @param passValue  This is the password of the user
     * @return JSONObject This returns a JSON formatted object with the username, also the password hashed with SHA256.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject LoginByJSON(String emailValue, String passValue) throws JSONException {
        JSONObject login_json;
        if ("".equals(emailValue) || "".equals(passValue))
        {
            String message = Resources.getSystem().getString(R.string.parameter_null_or_empty);
            throw new JSONException(message);
        }


        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailValue).matches() ){
            String message = Resources.getSystem().getString(R.string.email_format_not_valid);
            throw new JSONException(message);
        }
        else {
            passValue = Hashing.sha256().hashString(passValue, UTF_8).toString();
        }

        try {
            login_json = new JSONObject();
            login_json.put(Resources.getSystem().getString(R.string.email), emailValue);
            login_json.put(Resources.getSystem().getString(R.string.password), passValue);
            return login_json;
        } catch (JSONException e) {
            e.printStackTrace();
            throw  e;
        }
    }

}
