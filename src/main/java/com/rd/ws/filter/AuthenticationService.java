package com.rd.ws.filter;

        import com.rd.ws.services.DataServices;

        import java.io.IOException;
        import java.util.Base64;
        import java.util.StringTokenizer;

public class AuthenticationService {
    public String authenticate(String authCredentials) {

         String username;
        if (null == authCredentials)
            return null;
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(
                    encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final StringTokenizer tokenizer = new StringTokenizer(
                usernameAndPassword, ":");

        username = tokenizer.nextToken();
        System.out.println("Auth: " + username);

        // we have fixed the userid and password as admin
        // call some UserService/LDAP here
        boolean authenticationStatus = DataServices.LogIn(username);
        if(!authenticationStatus){
            System.out.println("Denied: " + username);
            username = null;
        }
        return username;
    }
}
