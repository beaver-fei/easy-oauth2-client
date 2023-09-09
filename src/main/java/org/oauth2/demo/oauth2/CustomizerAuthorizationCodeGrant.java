package org.oauth2.demo.oauth2;

import org.dmfs.httpessentials.client.HttpRequestExecutor;
import org.dmfs.httpessentials.exceptions.ProtocolError;
import org.dmfs.httpessentials.exceptions.ProtocolException;
import org.dmfs.oauth2.client.*;
import org.dmfs.oauth2.client.http.requests.AuthorizationCodeTokenRequest;
import org.dmfs.oauth2.client.pkce.S256CodeChallenge;
import org.dmfs.oauth2.client.scope.StringScope;
import org.dmfs.oauth2.client.utils.GrantState;
import org.dmfs.rfc3986.Uri;
import org.dmfs.rfc3986.encoding.Precoded;
import org.dmfs.rfc3986.encoding.XWwwFormUrlEncoded;
import org.dmfs.rfc3986.parameters.ParameterList;
import org.dmfs.rfc3986.parameters.adapters.XwfueParameterList;
import org.dmfs.rfc3986.parameters.parametersets.EmptyParameterList;
import org.dmfs.rfc3986.uris.LazyUri;
import org.dmfs.rfc3986.uris.Text;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class CustomizerAuthorizationCodeGrant implements OAuth2InteractiveGrant {



}
