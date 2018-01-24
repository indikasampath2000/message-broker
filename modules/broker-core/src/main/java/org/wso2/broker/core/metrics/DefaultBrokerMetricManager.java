/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.wso2.broker.core.metrics;

import org.wso2.broker.core.Broker;
import org.wso2.carbon.metrics.core.Counter;
import org.wso2.carbon.metrics.core.Level;
import org.wso2.carbon.metrics.core.Meter;
import org.wso2.carbon.metrics.core.MetricService;

/**
 * Default implementation of {@link BrokerMetricManager}
 */
public class DefaultBrokerMetricManager implements BrokerMetricManager {
    private final Meter totalPublishedCounter;
    private final Counter totalEnqueueCounter;

    public DefaultBrokerMetricManager(MetricService metrics) {
        totalPublishedCounter = metrics.meter(MetricService.name(Broker.class, "node", "totalPublished"), Level.INFO);
        totalEnqueueCounter = metrics.counter(MetricService.name(Broker.class, "node", "totalInMemoryMessages"),
                                              Level.INFO);
    }

    @Override
    public void markPublish() {
        totalPublishedCounter.mark();
    }

    @Override
    public void addInMemoryMessage() {
        totalEnqueueCounter.inc();
    }

    @Override
    public void removeInMemoryMessage() {
        totalEnqueueCounter.dec();
    }

}
