package ru.nikitavov.analitics.response;

import java.util.List;

public class ResponseDataList<R> extends ResponseDataAbstract<List<R>>{
    public ResponseDataList(List<R> result) {
        super(result);
    }
}
