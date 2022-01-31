package com.example.demo.security;

import com.example.demo.security.priviledges.UserPriviledges;
import com.example.demo.user.User;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserPriviledgesTest {

    @Test
    public void noPriviledgesTest() {
        UserPriviledges userPriviledges = UserPriviledges.getNoPriviledges();
        assertFalse(userPriviledges.isHasAdminRights());
        assertFalse(userPriviledges.isHasClientRights());
        assertFalse(userPriviledges.isHasEmployeeRights());
        assertFalse(userPriviledges.isBlocked());
    }

    @Test
    public void adminRightsTest() {
        UserPriviledges userPriviledges = UserPriviledges.getAdminPriviledges();
        assertTrue(userPriviledges.isHasAdminRights());
        assertTrue(userPriviledges.isHasClientRights());
        assertTrue(userPriviledges.isHasEmployeeRights());
        assertFalse(userPriviledges.isBlocked());
    }

    @Test
    public void employeeRightsTest() {
        UserPriviledges userPriviledges = UserPriviledges.getEmployeePriviledges();
        assertFalse(userPriviledges.isHasAdminRights());
        assertFalse(userPriviledges.isHasClientRights());
        assertTrue(userPriviledges.isHasEmployeeRights());
        assertFalse(userPriviledges.isBlocked());
    }

    @Test
    public void clientRightsTest() {
        UserPriviledges userPriviledges = UserPriviledges.getClientPriviledges();
        assertFalse(userPriviledges.isHasAdminRights());
        assertTrue(userPriviledges.isHasClientRights());
        assertFalse(userPriviledges.isHasEmployeeRights());
        assertFalse(userPriviledges.isBlocked());
    }

    @Test
    public void setAndGetAdminRightsTest() {
        UserPriviledges userPriviledges = UserPriviledges.getNoPriviledges();
        userPriviledges.setHasAdminRights(true);
        assertTrue(userPriviledges.isHasAdminRights());
    }

    @Test
    public void setAndGetClientRightsTest() {
        UserPriviledges userPriviledges = UserPriviledges.getNoPriviledges();
        userPriviledges.setHasClientRights(true);
        assertTrue(userPriviledges.isHasClientRights());
    }

    @Test
    public void setAndGetEmployeeRightsTest() {
        UserPriviledges userPriviledges = UserPriviledges.getNoPriviledges();
        userPriviledges.setHasEmployeeRights(true);
        assertTrue(userPriviledges.isHasEmployeeRights());
    }

    @Test
    public void setAndGetIsBlocked() {
        UserPriviledges userPriviledges = UserPriviledges.getNoPriviledges();
        userPriviledges.setBlocked(true);
        assertTrue(userPriviledges.isBlocked());
    }
}
