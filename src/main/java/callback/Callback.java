package callback;


public interface Callback<T> {

    void onSuccess(T object);
    void onFail(String errorMessage);
}
