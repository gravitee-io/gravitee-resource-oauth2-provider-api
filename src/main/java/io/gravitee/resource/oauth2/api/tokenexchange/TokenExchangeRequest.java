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
package io.gravitee.resource.oauth2.api.tokenexchange;

/**
 * Represents an OAuth 2.0 Token Exchange request as defined by RFC 8693.
 */
public class TokenExchangeRequest {

    public static final String TOKEN_TYPE_ACCESS_TOKEN = "urn:ietf:params:oauth:token-type:access_token";
    public static final String TOKEN_TYPE_REFRESH_TOKEN = "urn:ietf:params:oauth:token-type:refresh_token";
    public static final String TOKEN_TYPE_ID_TOKEN = "urn:ietf:params:oauth:token-type:id_token";
    public static final String TOKEN_TYPE_SAML1 = "urn:ietf:params:oauth:token-type:saml1";
    public static final String TOKEN_TYPE_SAML2 = "urn:ietf:params:oauth:token-type:saml2";
    public static final String TOKEN_TYPE_JWT = "urn:ietf:params:oauth:token-type:jwt";

    private final String subjectToken;
    private final String subjectTokenType;
    private final String resource;
    private final String audience;
    private final String scope;
    private final String requestedTokenType;
    private final String actorToken;
    private final String actorTokenType;

    private TokenExchangeRequest(Builder builder) {
        this.subjectToken = builder.subjectToken;
        this.subjectTokenType = builder.subjectTokenType;
        this.resource = builder.resource;
        this.audience = builder.audience;
        this.scope = builder.scope;
        this.requestedTokenType = builder.requestedTokenType;
        this.actorToken = builder.actorToken;
        this.actorTokenType = builder.actorTokenType;
    }

    public static Builder builder(String subjectToken, String subjectTokenType) {
        return new Builder(subjectToken, subjectTokenType);
    }

    public String getSubjectToken() {
        return subjectToken;
    }

    public String getSubjectTokenType() {
        return subjectTokenType;
    }

    public String getResource() {
        return resource;
    }

    public String getAudience() {
        return audience;
    }

    public String getScope() {
        return scope;
    }

    public String getRequestedTokenType() {
        return requestedTokenType;
    }

    public String getActorToken() {
        return actorToken;
    }

    public String getActorTokenType() {
        return actorTokenType;
    }

    public static class Builder {

        private final String subjectToken;
        private final String subjectTokenType;
        private String resource;
        private String audience;
        private String scope;
        private String requestedTokenType;
        private String actorToken;
        private String actorTokenType;

        private Builder(String subjectToken, String subjectTokenType) {
            this.subjectToken = subjectToken;
            this.subjectTokenType = subjectTokenType;
        }

        public Builder resource(String resource) {
            this.resource = resource;
            return this;
        }

        public Builder audience(String audience) {
            this.audience = audience;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder requestedTokenType(String requestedTokenType) {
            this.requestedTokenType = requestedTokenType;
            return this;
        }

        public Builder actorToken(String actorToken, String actorTokenType) {
            this.actorToken = actorToken;
            this.actorTokenType = actorTokenType;
            return this;
        }

        public TokenExchangeRequest build() {
            return new TokenExchangeRequest(this);
        }
    }
}
