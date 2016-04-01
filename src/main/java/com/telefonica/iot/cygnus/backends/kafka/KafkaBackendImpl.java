/**
 * Copyright 2016 Telefonica Investigación y Desarrollo, S.A.U
 *
 * This file is part of fiware-cygnus (FI-WARE project).
 *
 * fiware-cygnus is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * fiware-cygnus is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with fiware-cygnus. If not, see
 * http://www.gnu.org/licenses/.
 *
 * For those usages not covered by the GNU Affero General Public License please contact with iot_support at tid dot es
 */
package com.telefonica.iot.cygnus.backends.kafka;

import java.util.Properties;
import kafka.admin.AdminUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author pcoello25
 */

public class KafkaBackendImpl implements KafkaBackend {
    
    private KafkaProducer<String, String> kafkaProducer;
    
    /**
     * Constructor
     * @param properties 
     */
    public KafkaBackendImpl(Properties properties) {
        kafkaProducer = new KafkaProducer<String, String>(properties);
    }

    @Override
    public boolean topicExists(ZkClient zookeeperClient, String topic) throws Exception {
        return AdminUtils.topicExists(zookeeperClient, topic);
    }

    @Override
    public void createTopic(ZkClient zookeeperClient, String topic, int partitions, int replicationFactor, Properties props) {
        AdminUtils.createTopic(zookeeperClient, topic, partitions, replicationFactor, props);
    }

    @Override
    public void send(ProducerRecord<String, String> record) {
        kafkaProducer.send(record);
    }
    
}
