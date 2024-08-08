package frameworks.api;

import org.json.JSONObject;

/**
 *  Here we tried to abstract out the common parts share by
 *  RecipeAPI and Nutrition API.
 *  However, the two apis have different http request for accessing (one is GET, and the other is POST)
 *  Hence, the way how endpoints are created is different, and the parameter for the get response had to be different,
 *  Unless we are going to pass in the unnecessary argument into one of the API.
 *  Therefore, to address the issue posed by the TA, we have created the API Abstract class,
 *  but we did not make our APIs to implement this abstract class.
 */

public abstract class API {
    protected abstract JSONObject getResponse();
    protected abstract String getEndPoint();
}
