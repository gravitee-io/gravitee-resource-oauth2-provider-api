/*
 * Copyright © 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.resource.oauth2.api;

import io.gravitee.gateway.api.handler.Handler;
import io.gravitee.resource.api.AbstractConfigurableResource;
import io.gravitee.resource.api.ResourceConfiguration;
import io.gravitee.resource.oauth2.api.openid.UserInfoResponse;
import io.gravitee.resource.oauth2.api.tokenexchange.TokenExchangeRequest;
import io.gravitee.resource.oauth2.api.tokenexchange.TokenExchangeResponse;
import java.util.List;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public abstract class OAuth2Resource<C extends ResourceConfiguration> extends AbstractConfigurableResource<C> {

    String DEFAULT_SCOPE_SEPARATOR = " ";
    String DEFAULT_USER_CLAIM = "sub";

    public abstract void introspect(String accessToken, Handler<OAuth2Response> responseHandler);

    public abstract void userInfo(String accessToken, Handler<UserInfoResponse> responseHandler);

    /**
     * Performs an OAuth 2.0 Token Exchange as defined by RFC 8693.
     * Override this method in a resource implementation to support token exchange.
     */
    public void tokenExchange(TokenExchangeRequest tokenExchangeRequest, Handler<TokenExchangeResponse> responseHandler) {
        responseHandler.handle(
            new TokenExchangeResponse(new UnsupportedOperationException("Token exchange is not supported by this OAuth2 resource"))
        );
    }

    public String getScopeSeparator() {
        return DEFAULT_SCOPE_SEPARATOR;
    }

    public String getUserClaim() {
        return DEFAULT_USER_CLAIM;
    }

    /**
     * This method should return an {@link OAuth2ResourceMetadata} object following the
     * <a href="https://datatracker.ietf.org/doc/html/rfc9728">RFC 9728</a> specification.
     */
    public OAuth2ResourceMetadata getProtectedResourceMetadata(String protectedResourceUri, List<String> scopesSupported) {
        return new OAuth2ResourceMetadata(protectedResourceUri, null, scopesSupported);
    }

    /**
     * This method should return an {@link OAuth2ResourceMetadata} object following the
     * <a href="https://datatracker.ietf.org/doc/html/rfc9728">RFC 9728</a> specification.
     */
    public OAuth2ResourceMetadata getProtectedResourceMetadata(String protectedResourceUri) {
        return getProtectedResourceMetadata(protectedResourceUri, null);
    }
}
