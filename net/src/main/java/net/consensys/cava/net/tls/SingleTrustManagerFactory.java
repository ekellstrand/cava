/*
 * Copyright 2018 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package net.consensys.cava.net.tls;

import java.security.KeyStore;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;

import io.netty.handler.ssl.util.SimpleTrustManagerFactory;

final class SingleTrustManagerFactory extends SimpleTrustManagerFactory {

  private final TrustManager[] trustManagers;

  SingleTrustManagerFactory(TrustManager trustManager) {
    this.trustManagers = new TrustManager[] {trustManager};
  }

  @Override
  protected void engineInit(KeyStore keyStore) {}

  @Override
  protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {}

  @Override
  protected TrustManager[] engineGetTrustManagers() {
    return trustManagers;
  }
}
