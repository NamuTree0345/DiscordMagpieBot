import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import kotlin.math.roundToInt

//이게 레디여야되는거 아닌가요


var commands: ArrayList<CommandRef> = ArrayList<CommandRef>()

class ReadyListener : ListenerAdapter() {



    override fun onReady(event: ReadyEvent) {
        println("Initalizing...")

        commands.add(CommandRef("도움말", "현재 보고 있는 이 페이지입니다!"))
        commands.add(CommandRef("핑", "까치의 나이?를 보여줍니다."))
        commands.add(CommandRef("깎", "깎?!"))
        commands.add(CommandRef("한강수온", "까치가 마시고 온 한강물의 온도를 알려줍니다!"))
        commands.add(CommandRef("일해라 <맨션> <맨션>...", "맨션한사람에게 일하라고 합니다. ~~놀고있는 게으름뱅이에게 최고입니다. 쪼아주고요 ㅋㅋㅋㅋㅋㅋ~~ (역할은 맨션 불가능합니다.)"))
        commands.add(CommandRef("그룹일해라 <역할맨션> <역할맨션>...", "일해라와 같습니다. 단 역할만 맨션합니다."))

        println("Command is initalized")

        println("Ready as " + event.jda.selfUser.asTag)
    }

}
// 음... 감사합니닼ㅋㅋㅋ
//역시 약빨은자란 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ

//이게 메시지고
class MessageListener(val api: JDA, val prefix1: String="깍!", val prefix2: String="깎!") : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val ch: MessageChannel = event.channel
        val msg: Message = event.message
        val cont: String = msg.contentRaw

        when(cont) { // 임베드로 보내도 되나요 sp

            prefix1 + "도움말" -> {
                val helpEmbed: EmbedBuilder = EmbedBuilder()

                helpEmbed.setDescription("이 봇의 도움말입니다.")
                helpEmbed.setTitle("도움말")
                helpEmbed.setAuthor(msg.member?.user?.asTag + "님의 명령", null, msg.member?.user?.avatarUrl)
                helpEmbed.setFooter("까치봇은 Kotlin으로 나무, 껠쓰, 그리고 윈트초코가 만들었습니다.", "https://t1.daumcdn.net/cfile/tistory/9910524E5D307A6E1A")
                for(cmd in commands) {
                    helpEmbed.addField("깎(또는 깍)!" + cmd.name, cmd.desc, false)
                }
                ch.sendMessage(helpEmbed.build()).queue()
            }
            prefix2 + "도움말" -> {
                val helpEmbed: EmbedBuilder = EmbedBuilder()

                helpEmbed.setDescription("이 봇의 도움말입니다.")
                helpEmbed.setTitle("도움말")
                helpEmbed.setFooter("까치봇은 Kotlin으로 나무, 껠쓰, 그리고 윈트초코가 만들었습니다.", "https://t1.daumcdn.net/cfile/tistory/9910524E5D307A6E1A")
                helpEmbed.setAuthor(msg.member?.user?.asTag + "님의 명령", null, msg.member?.user?.avatarUrl)
                for(cmd in commands) {
                    helpEmbed.addField("깎(또는 깍)!" + cmd.name, cmd.desc, false)
                }
                ch.sendMessage(helpEmbed.build()).queue()
            }

            prefix1 + "핑" -> ch.sendMessage(EmbedBuilder().addField("게이트웨이 핑", api.gatewayPing.toString() + "ms", false).build()).queue() // sendMessage(ch, "게이트웨이 핑은 " + api.gatewayPing.toString() + "ms이다! 깍!")
            prefix2 + "핑" -> ch.sendMessage(EmbedBuilder().addField("게이트웨이 핑", api.gatewayPing.toString(), false).build()).queue()

            prefix1 + "깎" -> sendMessage(ch, "깎깎깎!!")
            prefix2 + "깎" -> sendMessage(ch, "깎깎깎!!")

            prefix1 + "한강수온" -> {
                sendMessage(ch, "기억했던걸 떠올리는 중이다. 좀 오래걸릴수 있다. 깎.")
                if(HangangCrawler().crawl() == "NONE") {
                    sendMessage(ch, "불러 오는데 오류가 났다. 깎!!!")
                } else {
                    sendMessage(ch, HangangCrawler().crawl() + "였다! 깎!")

                }
            }
            prefix1 + "한강수온" -> {
                sendMessage(ch, "기억했던걸 떠올리는 중이다. 좀 오래걸릴수 있다. 깎.")
                if(HangangCrawler().crawl() == "NONE") {
                    sendMessage(ch, "불러 오는데 오류가 났다. 깎!!!")
                } else {
                    sendMessage(ch, HangangCrawler().crawl() + "였다! 깎!")

                }
            }

            // 매개변수가 있는 명령어
            else -> {
                if(cont.startsWith("깍!일해라 ") || cont.startsWith("깎!일해라 ")) {
                    if(msg.mentionsEveryone()) {
                        msg.delete()
                        sendMessage(ch, "모두를 일하라고 하는건 안된다! 깎!")
                        return
                    }
                    var newMsg: String = ""
                    for(member in msg.mentionedMembers) {
                        newMsg = newMsg + "<@" + member?.id + "> "
                    }
                    var ppx: String = ""
                    if((Math.random() * 2).roundToInt() == 0) {
                        ppx = "일해라 까아악!"
                    } else {
                        ppx = "~~(겁나 쪼은다)~~ 일해라 까깎ㄲ까깎ㄲㄸㄲ까어까ㅏㄲ까ㅏ까까까ㅏㄲ!"
                    }

                    sendMessage(ch, newMsg + ppx)
                }
                if(cont.startsWith("깍!그룹일해라 ") || cont.startsWith("깎!그룹일해라 ")) {
                    if(msg.mentionsEveryone()) {
                        msg.delete()
                        sendMessage(ch, "모두를 일하라고 하는건 안된다! 깎!")
                        return
                    }
                    var newMsg: String = ""
                    for(role in msg.mentionedRoles) {
                        newMsg = newMsg + "<@&" + role?.id + "> "
                    }
                    var ppx: String = ""
                    if((Math.random() * 2).roundToInt() == 0) {
                        ppx = "일해라 까아악!"
                    } else {
                        ppx = "~~(겁나 쪼은다)~~ 일해라 까깎ㄲ까깎ㄲㄸㄲ까어까ㅏㄲ까ㅏ까까까ㅏㄲ!"
                    }

                    sendMessage(ch, newMsg + ppx)
                }
            }
        }
   }

}