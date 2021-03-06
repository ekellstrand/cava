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
package net.consensys.cava.ssz;

/**
 * Base type for all SSZ encoding and decoding exceptions.
 */
public class SSZException extends RuntimeException {
  public SSZException(String message) {
    super(message);
  }

  public SSZException(Throwable cause) {
    super(cause);
  }

  public SSZException(String message, Throwable cause) {
    super(message, cause);
  }
}
