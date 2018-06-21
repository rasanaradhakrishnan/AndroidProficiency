package proficiency.android.com.data.errors;

/**
 * * This contains all network error codes handled in the application
 */
public class NetworkErrorConstants {

    private NetworkErrorConstants() {
    }

    public static final int INVALID_LOGIN_PARAMETERS    = 101;
    public static final int BAD_REQUEST                 = 400;
    public static final int UNAUTHORIZED_USER           = 401;
    public static final int SESSION_EXPIRED             = 403;
    public static final int HTTP_NOT_FOUND              = 404;
    public static final int HTTP_SERVER_ERROR           = 500;
}
