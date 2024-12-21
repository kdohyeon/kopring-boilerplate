package kdohyeon.boilerplate.interceptor;

import kdohyeon.boilerplate.authentication.AuthenticationHolder;
import kdohyeon.boilerplate.authentication.RequestedBy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
@RequiredArgsConstructor
public class RequestedByInterceptor implements WebRequestInterceptor {

    public static final String REQUESTED_BY_HEADER = "requested-by";

    private final AuthenticationHolder authenticationHolder;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        String requestedBy = request.getHeader(REQUESTED_BY_HEADER);
        RequestedBy requested = new RequestedBy(requestedBy);
        authenticationHolder.setAuthentication(requested);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        //
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        //
    }
}
