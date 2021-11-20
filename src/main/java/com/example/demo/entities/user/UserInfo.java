package com.example.demo.entities.user;

import com.example.demo.security.priviledges.UserPriviledges;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfo {
    private Optional<UserPriviledges> userPriviledges = Optional.ofNullable(null);

    public UserPriviledges getUserPriviledges() {
        return userPriviledges.orElseGet(UserPriviledges::getNoPriviledges);
    }

    public void setUserPriviledges(UserPriviledges userPriviledges) {
        this.userPriviledges = Optional.of(userPriviledges);
    }
}
