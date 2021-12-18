package de.sollder1.webchess.backend.api;

import de.sollder1.webchess.backend.game.WebChessException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class WebChessExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable e) {
        e.printStackTrace();
        return Response.serverError().entity(e.getMessage()).build();
    }
}
