package com.example.demo.security.priviledges;

public class UserPriviledges {
    boolean hasAdminRights = false;
    boolean hasEmployeeRights = false;
    boolean hasClientRights = false;
    boolean isBlocked = false;

    public UserPriviledges() {

    }

    public boolean isHasAdminRights() {
        return hasAdminRights;
    }

    public void setHasAdminRights(boolean hasAdminRights) {
        this.hasAdminRights = hasAdminRights;
    }

    public boolean isHasEmployeeRights() {
        return hasEmployeeRights;
    }

    public void setHasEmployeeRights(boolean hasEmployeeRights) {
        this.hasEmployeeRights = hasEmployeeRights;
    }

    public boolean isHasClientRights() {
        return hasClientRights;
    }

    public void setHasClientRights(boolean hasClientRights) {
        this.hasClientRights = hasClientRights;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public static UserPriviledges getAdminPriviledges() {
        UserPriviledges priviledges = new UserPriviledges();
        priviledges.setHasAdminRights(true);
        priviledges.setHasClientRights(true);
        priviledges.setHasEmployeeRights(true);
        return priviledges;
    }

    public static UserPriviledges getEmployeePriviledges() {
        UserPriviledges priviledges = new UserPriviledges();
        priviledges.setHasEmployeeRights(true);
        return priviledges;
    }

    public static UserPriviledges getClientPriviledges() {
        UserPriviledges priviledges = new UserPriviledges();
        priviledges.setHasClientRights(true);
        return priviledges;
    }

    public static UserPriviledges getNoPriviledges() {
        UserPriviledges priviledges = new UserPriviledges();
        return priviledges;
    }
}
