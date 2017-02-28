package com.darichey.discord.kommands

import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.handle.impl.events.MessageReceivedEvent
import sx.blah.discord.handle.obj.IChannel
import sx.blah.discord.handle.obj.IGuild
import sx.blah.discord.handle.obj.IMessage
import sx.blah.discord.handle.obj.IUser

typealias CommandFunction = CommandContext.() -> Unit
typealias PermissionChecker = CommandContext.() -> Boolean

data class CommandContext(val client: IDiscordClient,
						  val name: String,
						  val args: List<String>,
						  val message: IMessage,
						  val guild: IGuild,
						  val channel: IChannel,
						  val author: IUser) {

	constructor(event: MessageReceivedEvent, prefix: String): this(
			event.client,
			event.message.content.split(prefix, " ")[1],
			event.message.content.substring(event.message.content.indexOf(" ") + 1, event.message.content.length).split(" "),
			event.message,
			event.message.guild,
			event.message.channel,
			event.message.author
	)
}

class Command(
		val onCalled: CommandFunction,
		vararg val limiters: Limiter = arrayOf(),
		val description: String = ""
)

open class Limiter(
		val check: PermissionChecker,
		val onFail: CommandFunction = {}
)