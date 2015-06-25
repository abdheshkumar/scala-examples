package caseclasses

import javax.sound.sampled.{DataLine, TargetDataLine}

/**
 * Created by abdhesh on 6/25/15.
 */
object ScalaEquivalentJavaClass {
  val info = new DataLine.Info(classOf[TargetDataLine], null)
  // java
  //info = new DataLine.Info(TargetDataLine.class, null)
}
