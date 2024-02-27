class HelloWorld extends StreamApplication { //StreamApplication provides built-in methods to process streaming data.

    val zookeeper = ImmutableList.of("localhost:2181")
    val server = ImmutableList.of(localhost:9092)
    val streamConfig = ImmutableMap.of("replication.factor", "1")
    val inputTopicName = config.getString("streams-plaintext-input")
    val outputTopicName = config.getString("streams-wordcount-output")
    val serdeForKV = KVSerde.of(new StringSerde(), new StringSerde())

    val ksd = new KafkaSystemDescriptor(systemName)
      .withConsumerZkConnect(zookeeper)
      .withProducerBootstrapServers(server)
      .withDefaultStreamConfigs(streamConfig)

    val kid = ksd.getInputDescriptor(inputTopicName, serdeForKV)

    val kod = ksd.getOutputDescriptor(outputTopicName, serdeForKV)

    appDescriptor.withDefaultSystem(ksd)

    val inputStream = appDescriptor.getInputStream(kid)

    val outputStream = appDescriptor.getOutputStream(kod)

// we are only filtering out those messages from input topic which contains "hi".
    inputStream.filter(kv => kv.value.contains("hi")).sendTo(outputStream)

  }
