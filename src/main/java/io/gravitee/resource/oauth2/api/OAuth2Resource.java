/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
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

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public abstract class OAuth2Resource<C extends ResourceConfiguration> extends AbstractConfigurableResource<C> {
    String DEFAULT_SCOPE_SEPARATOR = " ";
    String DEFAULT_USER_CLAIM = "sub";

    public abstract void introspect(String accessToken, Handler<OAuth2Response> responseHandler);

    public abstract void userInfo(String accessToken, Handler<UserInfoResponse> responseHandler);

    public String getScopeSeparator(){
        return DEFAULT_SCOPE_SEPARATOR;
    }

    public String getUserClaim(){
        return DEFAULT_USER_CLAIM;
    }
}
