package ru.nikitavov.analitics.response;

public class ResponseDataElement<T> extends ResponseDataAbstract<T>{
    public ResponseDataElement(T result) {
        super(result);
    }
}
