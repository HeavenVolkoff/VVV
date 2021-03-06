package HTML.Controller;

import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class ViewController {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getHome(@Context HttpServletRequest req, @QueryParam("typeKey") String typeKey) {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("csrfKey", req.getSession(true).getAttribute("CSRFToken"));
        map.put("typeKey", typeKey);

        return new Viewable("/Home/index", map);
    }
}
