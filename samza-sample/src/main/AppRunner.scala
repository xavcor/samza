  val cmdLine = new CommandLine
  val options = cmdLine.parser.parse("--config-factory=org.apache.samza.config.factories.PropertiesConfigFactory", "--config-path=/home/frank/Desktop/Samza/samza-sample/src/main/config/HelloWorld.properties")
  val conf = cmdLine.loadConfig(options)
  val runner = new LocalApplicationRunner(new HelloWorld, conf)
  runner.run()
  runner.waitForFinish()
