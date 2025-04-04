package com.diagramify;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Diagramify {

    private Application application;
    private List<Jms> jms;
    private List<Database> databases;
    private Http http;
    private Cloud cloud;

    @Data
    @NoArgsConstructor
    public static class Application {
        @NotBlank private String name;
        @NotNull private Integer port;
        private String contextPath;
        private String group;
        private String host;
        private String environment;
    }

    @Data
    @NoArgsConstructor
    public static class Jms {
        @NotBlank private String url;
        private String type;
        private List<String> inboundQueues;
        private List<String> outboundQueues;
    }

    @Data
    @NoArgsConstructor
    public static class Http {
        private List<String> inbound;
        private List<String> outbound;
    }

    @Data
    @NoArgsConstructor
    public static class Cloud {
        private boolean exposeUrl;
        private String accountId;
        private String user;
        private String secret;
    }

    @Data
    @NoArgsConstructor
    public static class Database {
        private String url;
    }
}
