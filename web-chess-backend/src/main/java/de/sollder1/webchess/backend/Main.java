package de.sollder1.webchess.backend;

import de.sollder1.webchess.backend.api.lobby.LobbyResource;
import de.sollder1.webchess.backend.api.player.PlayerResource;
import jakarta.ws.rs.ext.RuntimeDelegate;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {

    public static void main(String[] args) {
        bootApplicationServer();
    }

    private static void bootApplicationServer() {

        ResourceConfig resourceConfig = initRestResources();
        resourceConfig.register(new CorsFilter());
        HttpHandler handler = RuntimeDelegate.getInstance()
                .createEndpoint(resourceConfig, HttpHandler.class);

        HttpServer server = HttpServer.createSimpleServer(null, 8101);
        server.getServerConfiguration().addHttpHandler(handler);

        try {
            server.getServerConfiguration().setName("Schachserver");
            server.start();
            System.out.println("Press any key to stop the server...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static ResourceConfig initRestResources() {
        ResourceConfig resourceConfig = new ResourceConfig();
        //TODO: Registrieren der Endpunkte :
        resourceConfig.register(PlayerResource.class);
        resourceConfig.register(LobbyResource.class);

        return resourceConfig;
    }

}
