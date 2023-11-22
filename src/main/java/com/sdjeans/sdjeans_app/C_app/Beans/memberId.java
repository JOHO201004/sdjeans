package com.sdjeans.sdjeans_app.C_app.Beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Scope("session")
@Data
public class memberId {

    private String memberId;
    
}
