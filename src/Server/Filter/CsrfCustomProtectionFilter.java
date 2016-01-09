package Server.Filter;


import Server.Application;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Priority(Priorities.AUTHENTICATION) // should be one of the first post-matching filters to get executed
public class CsrfCustomProtectionFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest webRequest;

    /**
     * Name of the header this filter will attach to the request.
     */
    public static final String HEADER_NAME = "X-Requested-By";

    private static final Set<String> METHODS_TO_IGNORE;
    static {
        HashSet<String> mti = new HashSet<>();
        mti.add("GET");
        mti.add("OPTIONS");
        mti.add("HEAD");
        METHODS_TO_IGNORE = Collections.unmodifiableSet(mti);
    }

    @Override
    public void filter(ContainerRequestContext rc) throws IOException {
        HttpSession session = webRequest.getSession(true);
        String csrfKey = rc.getHeaders().getFirst(HEADER_NAME);

        try {
            if(METHODS_TO_IGNORE.contains(rc.getMethod())){
                session.setAttribute("CSRFToken", BCrypt.hashpw(Application.CSRF_KEY, BCrypt.gensalt(10)));
                return;

            }else if(csrfKey != null && BCrypt.checkpw(Application.CSRF_KEY, csrfKey)){
                String csrfToken = (String) session.getAttribute("CSRFToken");

                if(csrfToken != null && csrfToken.equals(csrfKey)){
                    return;
                }
            }

        }catch (Exception e){
            throw new BadRequestException();
        }

        throw new BadRequestException();
    }
}
