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
 * Represents an OAuth 2.0 Token Exchange response as defined by RFC 8693.
 */
public class TokenExchangeResponse {

    private final boolean success;
    private final String accessToken;
    private final String issuedTokenType;
    private final String tokenType;
    private final Long expiresIn;
    private final String scope;
    private final String refreshToken;
    private final Throwable throwable;

    private TokenExchangeResponse(Builder builder) {
        this.success = true;
        this.accessToken = builder.accessToken;
        this.issuedTokenType = builder.issuedTokenType;
        this.tokenType = builder.tokenType;
        this.expiresIn = builder.expiresIn;
        this.scope = builder.scope;
        this.refreshToken = builder.refreshToken;
        this.throwable = null;
    }

    public TokenExchangeResponse(Throwable throwable) {
        this.success = false;
        this.throwable = throwable;
        this.accessToken = null;
        this.issuedTokenType = null;
        this.tokenType = null;
        this.expiresIn = null;
        this.scope = null;
        this.refreshToken = null;
    }

    public static Builder builder(String accessToken, String issuedTokenType, String tokenType) {
        return new Builder(accessToken, issuedTokenType, tokenType);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getIssuedTokenType() {
        return issuedTokenType;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public static class Builder {

        private final String accessToken;
        private final String issuedTokenType;
        private final String tokenType;
        private Long expiresIn;
        private String scope;
        private String refreshToken;

        private Builder(String accessToken, String issuedTokenType, String tokenType) {
            this.accessToken = accessToken;
            this.issuedTokenType = issuedTokenType;
            this.tokenType = tokenType;
        }

        public Builder expiresIn(Long expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public TokenExchangeResponse build() {
            return new TokenExchangeResponse(this);
        }
    }
}
