/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.iotdb.db.mpp.transformation.dag.column.binary;

import org.apache.iotdb.db.mpp.transformation.dag.column.ColumnTransformer;
import org.apache.iotdb.tsfile.read.common.type.Type;

public class CompareNonEqualColumnTransformer extends CompareBinaryColumnTransformer {
  public CompareNonEqualColumnTransformer(
      Type returnType, ColumnTransformer leftTransformer, ColumnTransformer rightTransformer) {
    super(returnType, leftTransformer, rightTransformer);
  }

  @Override
  protected final void checkType() {
    if (typeEquals(leftTransformer, rightTransformer)) {
      return;
    }

    // Boolean type and Binary Type can not be compared with other types
    if (!leftTransformer.isReturnTypeNumeric() || !rightTransformer.isReturnTypeNumeric()) {
      throw new UnsupportedOperationException("Unsupported Type");
    }
  }

  @Override
  protected boolean transform(int flag) {
    return flag != 0;
  }
}
