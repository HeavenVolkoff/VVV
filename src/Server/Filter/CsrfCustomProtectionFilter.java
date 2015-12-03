package Server.Filter;


import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.Priority;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Priority(Priorities.AUTHENTICATION) // should be one of the first post-matching filters to get executed
public class CsrfCustomProtectionFilter implements ContainerRequestFilter {

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
        String CsrfKey = rc.getHeaders().getFirst(HEADER_NAME);

        try {
            if(METHODS_TO_IGNORE.contains(rc.getMethod()) || CsrfKey == null || !BCrypt.checkpw("CSRF_KEY_VVV_SYSTEM_176907663", CsrfKey)){
                throw new BadRequestException();
            }
        }catch (Exception e){
            throw new BadRequestException();
        }


    }
}
