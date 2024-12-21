package kdohyeon.boilerplate.security;

import kdohyeon.boilerplate.user.FetchUserUseCase;
import kdohyeon.boilerplate.user.response.UserResponse;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NetplixUserDetailsService implements UserDetailsService {

    private final FetchUserUseCase fetchUserUseCase;

    @Override
    public NetplixAuthUser loadUserByUsername(String email) throws UsernameNotFoundException {
        UserResponse userByEmail = fetchUserUseCase.findUserByEmail(email);
        return new NetplixAuthUser(
                userByEmail.getUserId(),
                userByEmail.getUsername(),
                userByEmail.getPassword(),
                userByEmail.getEmail(),
                userByEmail.getPhone(),
                List.of(new SimpleGrantedAuthority(StringUtils.isBlank(userByEmail.getRole()) ? "-" : userByEmail.getRole()))
        );
    }
}
