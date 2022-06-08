package xyz.xeonds.mirai.phiutil

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.whileSelectMessages
import net.mamoe.mirai.utils.info

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.xeonds.mirai.phiutil",
        name = "Phi-Util",
        version = "0.1.0"
    ) {
        author("xeonds@stu.xidian.edu.cn")
        info(
            """
            Pgr辅助工具
            算rks发送 rks计算
            查定数发送 定数查询
            获取定数表发送 定数表
        """.trimIndent()
        )
        // author 和 info 可以删除.
    }
) {
    @OptIn(ExperimentalSerializationApi::class)
    override fun onEnable() {
        logger.info { "Phi-Util 已加载" }

        val eventChannel = GlobalEventChannel.parentScope(this)
        eventChannel.subscribeAlways<GroupMessageEvent> {
            when {
                message.contentToString() == "rks计算" -> {
                    group.sendMessage("私聊了")
                    sender.sendMessage("没做完呢，先去这算吧\nhttp://www.jiujiuer.xyz/pages/pgr")
                }
                message.contentToString() == "定数查询" -> {
                    val id = sender.id
                    subject.sendMessage("输入关键字（回复取消退出查询）")
                    whileSelectMessages {
                        default {
                            if (this.sender.id == id && it!="取消") {
                                when (val res = Util().getRank(it)) {
                                    "[]" -> group.sendMessage("没找到")
                                    else -> {
                                        val data = Json.decodeFromString<List<Song>>(res)
                                        val level = listOf("EZ", "HD", "IN", "AT")
                                        val msg = java.lang.StringBuilder()

                                        msg.append("找到了${data.size}首")
                                        for (row in data) {
                                            msg.append("\n${row.name} ")
                                            for ((k, v) in row.rank.withIndex()) msg.append(" ${level[k]}:$v")
                                        }
                                        group.sendMessage(msg.toString())
                                    }
                                }
                            }
                            false
                        }
                    }
                }
            }
            return@subscribeAlways
        }
    }

    override fun onDisable() {
        logger.info { "Phi-Util 已卸载" }
    }
}

