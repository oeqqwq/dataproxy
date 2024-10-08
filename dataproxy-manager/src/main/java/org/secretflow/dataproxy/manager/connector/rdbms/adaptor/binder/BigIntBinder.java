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

import org.apache.arrow.vector.BigIntVector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * A column binder for 8-bit integers.
 */
public class BigIntBinder extends BaseColumnBinder<BigIntVector> {
    public BigIntBinder(BigIntVector vector) {
        this(vector, Types.BIGINT);
    }

    public BigIntBinder(BigIntVector vector, int jdbcType) {
        super(vector, jdbcType);
    }

    @Override
    public void bind(PreparedStatement statement, int parameterIndex, int rowIndex) throws SQLException {
        final long value = vector.getDataBuffer().getLong((long) rowIndex * BigIntVector.TYPE_WIDTH);
        statement.setLong(parameterIndex, value);
    }
}
