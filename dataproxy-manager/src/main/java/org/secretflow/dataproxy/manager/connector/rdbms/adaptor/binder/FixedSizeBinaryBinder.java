/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.secretflow.dataproxy.manager.connector.rdbms.adaptor.binder;

import org.apache.arrow.vector.FixedSizeBinaryVector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A binder for fixed-width binary types.
 */
public class FixedSizeBinaryBinder extends BaseColumnBinder<FixedSizeBinaryVector> {
    /**
     * Create a binder for the given vector using the given JDBC type for null values.
     *
     * @param vector   The vector to draw values from.
     * @param jdbcType The JDBC type code.
     */
    public FixedSizeBinaryBinder(FixedSizeBinaryVector vector, int jdbcType) {
        super(vector, jdbcType);
    }

    @Override
    public void bind(PreparedStatement statement, int parameterIndex, int rowIndex) throws SQLException {
        byte[] binaryData = new byte[vector.getByteWidth()];
        vector.getDataBuffer().getBytes((long) rowIndex * binaryData.length, binaryData, 0, binaryData.length);
        statement.setBytes(parameterIndex, binaryData);
    }
}
