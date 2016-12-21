package com.github.smdmts.example.streaming.kafka

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala._  // important implicit conversion.
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka._
import org.apache.flink.streaming.util.serialization.SimpleStringSchema


/**
 * StreamingJob
 */
object ReadStreamingJob {
  /** Main program method */
  def main(args: Array[String]) : Unit = {
    // get the execution environment
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val parameter = ParameterTool.fromArgs(args)
    val consumer = new FlinkKafkaConsumer09(parameter.getRequired("topic") , new SimpleStringSchema , parameter.getProperties)
    val stream = env.addSource(consumer)
    stream.map { v =>
      s"kafka and Flinks says:$v"
    }.rebalance.print()
    env.execute("StreamingJob")
  }

}
