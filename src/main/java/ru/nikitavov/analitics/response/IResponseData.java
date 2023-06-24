package ru.nikitavov.analitics.response;

import java.io.Serializable;

public interface IResponseData<T> extends Serializable {
    T getResult();
}
