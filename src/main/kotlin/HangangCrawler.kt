import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class HangangCrawler() {

    var doc: Document? = null

    fun crawl() : String{
        try{
            doc = Jsoup.connect("https://hangang.winsub.kr/").get()
        } catch(e: IOException) {
            e.printStackTrace()
        }

        var element: Elements? = doc?.select("div.col-lg-8.mx-auto")

        var ie1: Elements? = element?.select("center")

        if(ie1 == null) {
            println("ie1 is null!!!")
        } else {
            var ie2: Elements? = element?.select("h1")
            val text: String? = ie2?.get(0)?.text()
            if(text == null) {
                println("text is null")
            } else {
                println(text)
                return text
            }
        }
        return "NONE"
    }

    fun crawlTime() : String {
        try{
            doc = Jsoup.connect("https://hangang.winsub.kr/").get()
        } catch(e: IOException) {
            e.printStackTrace()
        }

        var element: Elements? = doc?.select("div.col-lg-8.mx-auto")

        var ie1: Elements? = element?.select("center")

        if(ie1 == null) {
            println("ie1 is null!!!")
        } else {
            var ie2: Elements? = element?.select("h1")
            val text: String? = ie2?.get(0)?.text()
            if(text == null) {
                println("text is null")
            } else {
                println(text)
                return text
            }
        }
        return "NONE"
    }

}