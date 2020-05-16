import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.MessageChannel

class BotMain() {
    fun start() {
        val api = JDABuilder("비^^").build()
        api.addEventListener(ReadyListener(), MessageListener(api))
        api.presence.activity = Activity.playing("클스 스탭분들 주시")
    }
}

fun main() {
    BotMain().start()
}

fun sendMessage(ch: MessageChannel, s: String) {
    ch.sendMessage(s).queue()
}

fun getArg(cmd: String) : ArrayList<String> {
    val spd: List<String> = cmd.split(" ")
    val sld: List<String> = spd.slice(IntRange(1, 1))

    val mtn: ArrayList<String> = ArrayList<String>()

    for(str in sld) {
        mtn.add(str)
    }

    return mtn
}
