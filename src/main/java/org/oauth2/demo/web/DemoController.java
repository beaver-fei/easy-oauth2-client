package org.oauth2.demo.web;

import org.dmfs.httpessentials.client.HttpRequestExecutor;
import org.dmfs.httpessentials.httpurlconnection.HttpUrlConnectionExecutor;
import org.dmfs.oauth2.client.*;
import org.dmfs.oauth2.client.grants.AuthorizationCodeGrant;
import org.dmfs.oauth2.client.scope.BasicScope;
import org.dmfs.oauth2.client.scope.EmptyScope;
import org.dmfs.rfc3986.encoding.Precoded;
import org.dmfs.rfc3986.parameters.ParameterType;
import org.dmfs.rfc3986.parameters.parametersets.BasicParameterList;
import org.dmfs.rfc3986.parameters.parametertypes.BasicParameterType;
import org.dmfs.rfc3986.parameters.valuetypes.TextValueType;
import org.dmfs.rfc3986.uris.LazyUri;
import org.dmfs.rfc5545.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
public class DemoController {

    @GetMapping("/demo2")
    public String genLoginUrl2() {
        String authorizationUri = "https://www.tiktok.com/v2/auth/authorize/";
        String clientId = "awov511gw5bec813";
        String redirectUri = "https://dev.example.com/auth/callback/";
        String scope = "user.info.basic,";
        String state = UUID.randomUUID().toString();
        String authorizationLocation = authorizationUri + "?response_type=code"
                + "&client_key=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=" + scope
                + "&state=" + state;
        return authorizationLocation;
    }

    @GetMapping("/demo")
    public String genLoginUrl() {
        // Create HttpRequestExecutor to execute HTTP requests
        // Any other HttpRequestExecutor implementaion will do
        HttpRequestExecutor executor = new HttpUrlConnectionExecutor();

        // Create OAuth2 provider
        OAuth2AuthorizationProvider provider = new BasicOAuth2AuthorizationProvider(
                URI.create("https://www.tiktok.com/v2/auth/authorize/"),
                URI.create("http://example.com/token"),
                new Duration(1,0,3600) /* default expiration time in case the server doesn't return any */);

        // Create OAuth2 client credentials
        OAuth2ClientCredentials credentials = new BasicOAuth2ClientCredentials(
                "client-id", "client-password");

        // Create OAuth2 client
        OAuth2Client client = new BasicOAuth2Client(
                provider,
                credentials,
                new LazyUri(new Precoded("http://localhost")) /* Redirect URL */);

        // Start an interactive Authorization Code Grant
        AuthorizationCodeGrant grant = new AuthorizationCodeGrant(
                client, new BasicScope("user.info.basic"));

        // Get the authorization URL and open it in a WebView
        URI authorizationUrl = grant.authorizationUrl();

        return authorizationUrl.toString();

    }

    @GetMapping("/demo1")
    public String genLoginUrl1(){

        ParameterType<CharSequence> CLIENT_KEY = new BasicParameterType<>("client_key", TextValueType.INSTANCE);


        BasicParameterList parameterList = new BasicParameterList(CLIENT_KEY.parameter("client_key"));


        return new BasicOAuth2AuthorizationRequest("code", new BasicScope("calendar"), "1234",parameterList)
                .withRedirectUri(new LazyUri(new Precoded("https://dev.example.com/auth/callback/")))
                .authorizationUri(
                URI.create("https://www.tiktok.com/v2/auth/authorize/")).toASCIIString();
    }

}
