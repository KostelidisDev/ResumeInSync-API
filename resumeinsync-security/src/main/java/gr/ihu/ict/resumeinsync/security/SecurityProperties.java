package gr.ihu.ict.resumeinsync.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "resume-in-sync-security")
@Data
public class SecurityProperties {

    private String clientId;
    private String clientSecret;

    private String resourceId;

    private Integer accessTokenDuration;
    private Integer refreshTokenDuration;

    private String signingKey;
    private String verifierKey;
}