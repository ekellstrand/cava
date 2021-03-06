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
package net.consensys.cava.rlp;

import static java.util.Objects.requireNonNull;
import static net.consensys.cava.rlp.RLP.encodeByteArray;
import static net.consensys.cava.rlp.RLP.encodeLength;
import static net.consensys.cava.rlp.RLP.encodeNumber;

import net.consensys.cava.bytes.Bytes;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.function.Consumer;

final class ByteBufferRLPWriter implements RLPWriter {

  private ByteBuffer buffer;

  ByteBufferRLPWriter(ByteBuffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public void writeRLP(Bytes value) {
    buffer.put(value.toArrayUnsafe());
  }

  @Override
  public void writeValue(Bytes value) {
    encodeByteArray(value.toArrayUnsafe(), buffer::put);
  }

  @Override
  public void writeByteArray(byte[] value) {
    encodeByteArray(value, buffer::put);
  }

  @Override
  public void writeByte(byte value) {
    encodeByteArray(new byte[] {value}, buffer::put);
  }

  @Override
  public void writeLong(long value) {
    buffer.put(encodeNumber(value));
  }

  @Override
  public void writeList(Consumer<RLPWriter> fn) {
    requireNonNull(fn);
    AccumulatingRLPWriter listWriter = new AccumulatingRLPWriter();
    fn.accept(listWriter);
    writeEncodedValuesAsList(listWriter.values());
  }

  private void writeEncodedValuesAsList(Deque<byte[]> values) {
    int totalSize = 0;
    for (byte[] value : values) {
      try {
        totalSize = Math.addExact(totalSize, value.length);
      } catch (ArithmeticException e) {
        throw new BufferOverflowException();
      }
    }
    buffer.put(encodeLength(totalSize, 0xc0));
    values.forEach(bytes -> buffer.put(bytes));
  }
}
