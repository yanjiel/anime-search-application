package AnimeSearch.service;

@FunctionalInterface
public interface ResponseCallback<T> {
    void operationFinished(T results);
}
