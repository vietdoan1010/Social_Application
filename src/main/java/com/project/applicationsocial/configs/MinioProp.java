package com.project.applicationsocial.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProp {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String filHost;
}
