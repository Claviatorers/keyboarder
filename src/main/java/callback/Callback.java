package callback;

/**
 * Created by Александр on 15.12.2018 in 17:26.
 */
public interface Callback<T> {

    void onSuccess(T object);
    void onFail(String errorMessage);
}
