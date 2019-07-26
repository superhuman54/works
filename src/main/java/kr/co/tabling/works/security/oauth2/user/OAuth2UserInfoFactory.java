package kr.co.tabling.works.security.oauth2.user;

import kr.co.tabling.works.exception.OAuth2AuthenticationProcessingException;
import kr.co.tabling.works.security.oauth2.model.AuthenticationProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        AuthenticationProvider provider = AuthenticationProvider.valueOf(registrationId.toLowerCase());

        switch (provider) {
            case google:
                return new GoogleOAuth2UserInfo(attributes);
            case local:
            default:
                throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }

}
