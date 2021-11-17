package com.vscodeissue.second;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import io.swagger.v3.oas.models.Operation;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyCustomizer implements OperationCustomizer {
    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        log.info("this method is called but vscode does not show the call hierarchy");
        return operation;
    }

}
