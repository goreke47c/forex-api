package com.forex.dto;

import java.util.List;
import java.util.Map;

public class ForexResponse {
    CodeMessageVo error;
    List<Map<String, String>> currency;

    public ForexResponse(String code, String message) {
        this.error = new CodeMessageVo(code, message);
    }

    public CodeMessageVo getError() {
        return error;
    }

    public void setError(CodeMessageVo error) {
        this.error = error;
    }

    public List<Map<String, String>> getCurrency() {
        return currency;
    }

    public void setCurrency(List<Map<String, String>> currency) {
        this.currency = currency;
    }

}
