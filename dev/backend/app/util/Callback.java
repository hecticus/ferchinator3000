package util;

public interface Callback<T> {
    void onSucess(T data);
    void onError();
}
