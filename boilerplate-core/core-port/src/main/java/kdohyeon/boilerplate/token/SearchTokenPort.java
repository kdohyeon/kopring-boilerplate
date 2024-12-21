package kdohyeon.boilerplate.token;

import java.util.Optional;

public interface SearchTokenPort {
    TokenPortResponse findByUserId(String userId);
}
