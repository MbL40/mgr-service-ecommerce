package com.store.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter @Builder
 class ErrorMessage {
    private String code;
    private List<Map<String, String>> messages;
}
