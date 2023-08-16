/*
 * Copyright (c) 2021, Seqera Labs.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as
 * defined by the Mozilla Public License, v. 2.0.
 */

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.seqera.tower.cli.credentials.providers;

import io.seqera.tower.cli.BaseCmdTest;
import io.seqera.tower.cli.commands.enums.OutputType;
import io.seqera.tower.cli.responses.CredentialsAdded;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;

import static io.seqera.tower.cli.commands.AbstractApiCmd.USER_WORKSPACE_NAME;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

class AzureProviderTest extends BaseCmdTest {

    @ParameterizedTest
    @EnumSource(OutputType.class)
    void testAdd(OutputType format, MockServerClient mock) {

        mock.when(
                request()
                        .withMethod("POST")
                        .withPath("/credentials")
                        .withBody(json("{\"credentials\":{\"keys\":{\"batchName\":\"batchName\",\"batchKey\":\"batchKey\",\"storageName\":\"storageName\",\"storageKey\":\"storageKey\"},\"name\":\"azure\",\"provider\":\"azure\"}}")),
                exactly(1)
        ).respond(
                response().withStatusCode(200).withBody("{\"credentialsId\":\"1cz5A8cuBkB5iJliCwJCFU\"}").withContentType(MediaType.APPLICATION_JSON)
        );

        ExecOut out = exec(format, mock, "credentials", "add", "azure", "--name=azure", "--batch-key=batchKey", "--batch-name=batchName", "--storage-key=storageKey", "--storage-name=storageName");
        assertOutput(format, out, new CredentialsAdded("AZURE", "1cz5A8cuBkB5iJliCwJCFU", "azure", USER_WORKSPACE_NAME));
    }

}
