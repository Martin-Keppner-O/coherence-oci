/*
 * Copyright (c) 2022, Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * https://oss.oracle.com/licenses/upl.
 */

package com.oracle.coherence.oci.secret.util;

import com.oracle.coherence.oci.secret.AbstractSecretsTest;

import com.oracle.coherence.oci.secret.testing.SecretsClientStub;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SecretsFetcherTest
        extends AbstractSecretsTest
    {
    @Test
    public void shouldFetchSecret()
        {
        SecretsClientStub client = new SecretsClientStub(s_clientKeyCertPair.m_fileKeyPEMNoPass);
        client.addSecret("foo", "foo-secret");

        SecretsFetcher fetcher = new SecretsFetcher(client);
        InputStream    in      = fetcher.get("foo");

        assertThat(in, is(notNullValue()));
        }

    @Test
    public void shouldFailToFetchSecret()
        {
        SecretsClientStub client  = new SecretsClientStub(s_clientKeyCertPair.m_fileKeyPEMNoPass);
        SecretsFetcher    fetcher = new SecretsFetcher(client);

        assertThrows(IllegalArgumentException.class, () -> fetcher.get("foo"));
        }

    // ----- data members ---------------------------------------------------

    private static SecretsClientStub s_client;
    }