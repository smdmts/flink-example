package com.github.smdmts.example.streaming.kafka

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala._  // important implicit conversion.
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer09
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

/**
 * WriteStreamingJob.
 */
object WriteStreamingJob {
  /** Main program method */
  def main(args: Array[String]) : Unit = {
    // get the execution environment
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val parameter = ParameterTool.fromArgs(args)
    val stream = env.addSource(new SimpleStringGenerator())
    stream.addSink(new FlinkKafkaProducer09[String](parameter.getRequired("topic") , new SimpleStringSchema , parameter.getProperties))
    env.execute("StreamingJob")
  }

  class SimpleStringGenerator extends SourceFunction[String] {
    var running = true
    var counter = 0L
    override def cancel(): Unit = {
      running = false
    }

    override def run(ctx: SourceContext[String]): Unit = {
      while (running) {
        counter = counter + 1
        ctx.collect("element-" + counter)
        Thread.sleep(1000)

      }
      ctx.close()
    }
  }
}
