package pl.bbl.features.authentication.user.handler;

import pl.bbl.network.server.handlers.UserHandler;
import pl.bbl.network.server.hive.UserHive;

public class UserAuthenticationHandler extends UserHandler{
    public UserAuthenticationHandler(UserHive userHive) {
        super(userHive);
    }
}
