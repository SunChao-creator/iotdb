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

package org.apache.iotdb.confignode.consensus.response.function;

import org.apache.iotdb.common.rpc.thrift.TSStatus;
import org.apache.iotdb.commons.udf.UDFInformation;
import org.apache.iotdb.confignode.rpc.thrift.TGetUDFTableResp;
import org.apache.iotdb.consensus.common.DataSet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class FunctionTableResp implements DataSet {

  private TSStatus status;

  private List<UDFInformation> allUDFInformation;

  public FunctionTableResp() {}

  public FunctionTableResp(TSStatus status, List<UDFInformation> allUDFInformation) {
    this.status = status;
    this.allUDFInformation = allUDFInformation;
  }

  public TSStatus getStatus() {
    return status;
  }

  public void setStatus(TSStatus status) {
    this.status = status;
  }

  public List<UDFInformation> getAllUDFInformation() {
    return allUDFInformation;
  }

  public void setAllUDFInformation(List<UDFInformation> allUDFInformation) {
    this.allUDFInformation = allUDFInformation;
  }

  public TGetUDFTableResp convertToThriftResponse() throws IOException {
    List<ByteBuffer> udfInformationByteBuffers = new ArrayList<>();

    for (UDFInformation udfInformation : allUDFInformation) {
      udfInformationByteBuffers.add(udfInformation.serialize());
    }

    return new TGetUDFTableResp(status, udfInformationByteBuffers);
  }
}
