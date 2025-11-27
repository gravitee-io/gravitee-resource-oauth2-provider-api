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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class OAuth2ResourceMetadataTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test_parsing() throws JsonProcessingException {
        OAuth2ResourceMetadata resourceMetadata = new OAuth2ResourceMetadata(
            "https://example.com",
            List.of("https://auth.server.com"),
            List.of("read", "write")
        );
        Map<String, Object> resourceDataMap = mapper.readValue(mapper.writeValueAsString(resourceMetadata), new TypeReference<>() {});
        assertAll(
            () -> assertThat(resourceDataMap.get("resource")).isEqualTo("https://example.com"),
            () ->
                assertThat(((List<String>) resourceDataMap.get("authorization_servers")))
                    .containsExactlyInAnyOrder("https://auth.server.com"),
            () -> assertThat(((List<String>) resourceDataMap.get("scopes_supported"))).containsExactlyInAnyOrder("read", "write")
        );
    }
}
