package notebook.front.widgets

import play.api.libs.json._

import notebook.front.widgets.Form
import notebook.Codec
import notebook.JsonCodec._
import notebook.front.SingleConnectedWidget
import tipl.util.ArgumentList
import tipl.util.ArgumentParser
import tipl.spark.SparkGlobal

/**
 * Created by mader on 12/24/14.
 */
class ArgListWidget(val initData: ArgumentParser, val blockName: String)
  extends Form[ArgumentParser] {

  val title = "Update "+blockName+" Settings"
  val paramsCodec: Codec[ArgumentParser, Map[String, String]] = new Codec[ArgumentParser,
    Map[String, String]] {
    def encode(p: ArgumentParser):Map[String,String] = {
      import scala.collection.JavaConversions._
      p.getArgumentList.map(a=>(a.getName(),a.getValueAsString())).toMap
    }
    def decode(m:Map[String,String]): ArgumentParser = {
      SparkGlobal.activeParser(m.map(i => "-"+i._1+"="+i._2).toArray)
    }
  }

  val update:ArgumentParser => ArgumentParser = (p:ArgumentParser) => {
    p
  }

}
