package com.project.githering.User.OAuth2.OAuth2UserAttributes;

import com.project.githering.User.OAuth2.Exception.AuthServerNotSupportedException;
import lombok.Getter;

import java.util.Map;


@Getter
public class OAuth2AttributesDispatcher {

    public static GithubOAuth2UserAttributes dispatch(String registrationId, Map<String, Object> attributes)
            throws AuthServerNotSupportedException {

        return switch (registrationId) {
            case "github" -> new GithubOAuth2UserAttributes(attributes);
            default -> throw new AuthServerNotSupportedException();
        };
    }


}
