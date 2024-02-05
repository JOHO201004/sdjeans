package com.sdjeans.sdjeans_app.C_app.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class LongPollingService {
    
    private final DeferredResult<String> deferredResult = new DeferredResult<>();
    
    public DeferredResult<String> getDeferredResult() {
        return deferredResult;
    }

    public void notifyClient(String data) {
        deferredResult.setResult(data);
    }
}
