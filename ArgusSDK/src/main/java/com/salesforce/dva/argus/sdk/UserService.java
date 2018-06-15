/*
 * Copyright (c) 2016, Salesforce.com, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Salesforce.com nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.salesforce.dva.argus.sdk;

import com.salesforce.dva.argus.sdk.ArgusHttpClient.ArgusResponse;
import com.salesforce.dva.argus.sdk.ArgusService.EndpointService;
import com.salesforce.dva.argus.sdk.entity.PrincipalUser;
import com.salesforce.dva.argus.sdk.exceptions.TokenExpiredException;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Provides methods to manipulate users.
 *
 * @author  Tom Valine (tvaline@salesforce.com)
 */
public class UserService extends EndpointService {

    //~ Static fields/initializers *******************************************************************************************************************

    private static final String RESOURCE = "/users";

    //~ Constructors *********************************************************************************************************************************

    /**
     * Creates a new UserService object.
     *
     * @param  client  The HTTP client for use by the service.
     */
    UserService(ArgusHttpClient client) {
        super(client);
    }

    //~ Methods **************************************************************************************************************************************

    /**
     * Returns the information for the specified user.
     *
     * @param   id  The ID of the user to retrieve information for.
     *
     * @return  The information for the specified user.
     *
     * @throws  IOException  If the server cannot be reached.
     * @throws  TokenExpiredException   If the token sent along with the request has expired
     */
    public PrincipalUser getUser(BigInteger id) throws IOException, TokenExpiredException {
        String requestUrl = RESOURCE + "/id/" + id.toString();
        ArgusResponse response = getClient().executeHttpRequest(ArgusHttpClient.RequestType.GET, requestUrl, null);

        assertValidResponse(response, requestUrl);
        return fromJson(response.getResult(), PrincipalUser.class);
    }

    /**
     * Returns the information for the specified user.
     *
     * @param   username  The username to retrieve information for.
     *
     * @return  The information for the specified user.
     *
     * @throws  IOException  If the server cannot be reached.
     * @throws  TokenExpiredException   If the token sent along with the request has expired
     */
    public PrincipalUser getUser(String username) throws IOException, TokenExpiredException {
        String requestUrl = RESOURCE + "/username/" + username;
        ArgusResponse response = getClient().executeHttpRequest(ArgusHttpClient.RequestType.GET, requestUrl, null);

        assertValidResponse(response, requestUrl);
        return fromJson(response.getResult(), PrincipalUser.class);
    }
}
/* Copyright (c) 2016, Salesforce.com, Inc.  All rights reserved. */
