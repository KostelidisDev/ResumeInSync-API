package gr.ihu.ict.resumeinsync.security.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

@Getter
@Setter
public class OAuth2AuthenticationUser extends OAuth2Authentication {

    private UserDetails userDetails;

    public OAuth2AuthenticationUser(final OAuth2Request storedRequest, final Authentication userAuthentication) {
        super(storedRequest, userAuthentication);
    }
}