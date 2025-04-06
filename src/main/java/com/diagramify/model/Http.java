package com.diagramify.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class Http {
    private List<String> inbound;
    private List<String> outbound;
}