package kr.co.tabling.works.security.oauth2;

import kr.co.tabling.works.exception.OAuth2AuthenticationProcessingException;
import kr.co.tabling.works.persist.entity.User;
import kr.co.tabling.works.persist.repository.UserRepository;
import kr.co.tabling.works.security.oauth2.model.AuthenticationProvider;
import kr.co.tabling.works.security.oauth2.user.OAuth2UserInfo;
import kr.co.tabling.works.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        return processOAuth2User(request, oAuth2User);


    }

    private OAuth2User processOAuth2User(OAuth2UserRequest request, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(request.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthenticationProvider.valueOf(request.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(request, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest request, OAuth2UserInfo info) {
        User user = new User();
        user.setProvider(AuthenticationProvider.valueOf(request.getClientRegistration().getRegistrationId()));
        user.setProviderId(info.getId());
        user.setName(info.getName());
        user.setEmail(info.getEmail());
        user.setImageUrl(info.getImageUrl());
        return userRepository.save(user);
    }

    private User updateExistingUser(User existing, OAuth2UserInfo info) {
        existing.setName(info.getName());
        existing.setImageUrl(info.getImageUrl());
        return userRepository.save(existing);
    }
}
