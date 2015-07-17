package com.prateekj;

import java.util.List;

public interface AsyncTaskResultHandler<E extends Object>{
    public void handleResult(List<E> e);
}
