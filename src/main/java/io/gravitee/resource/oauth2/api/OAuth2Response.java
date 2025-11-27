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

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class OAuth2Response {

    private final boolean success;
    private final String payload;
    private final Throwable throwable;

    public OAuth2Response(final boolean success, final String payload) {
        this.success = success;
        this.payload = payload;
        this.throwable = null;
    }

    public OAuth2Response(final Throwable throwable) {
        this.success = false;
        this.throwable = throwable;
        this.payload = throwable.getMessage();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getPayload() {
        return payload;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
