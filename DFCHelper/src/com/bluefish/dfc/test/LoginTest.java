package com.bluefish.dfc.test;

public class LoginTest extends Dfc5BaseTest {
    
    public void testLogin() throws Exception {
        // login happens in setUp(), so nothing to do here
        assertNotNull("session is null", session);
    }

}
