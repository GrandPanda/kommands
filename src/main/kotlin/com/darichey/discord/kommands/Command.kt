package com.darichey.discord.kommands

import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.handle.impl.events.MessageReceivedEvent
import sx.blah.discord.handle.obj.IChannel
import sx.blah.discord.handle.obj.IGuild
import sx.blah.discord.handle.obj.IUser

typealias CommandFunction = CommandContext.() -> Unit
typealias PermissionChecker = CommandContext.() -> Boolean

data class CommandContext(val client: IDiscordClient,
						  val name: String,
						  val args: List<String>,
						  val guild: IGuild,
						  val channel: IChannel,
						  val author: IUser) {

	constructor(event: MessageReceivedEvent): this(
			event.client,
			event.message.content.split(" ")[0],
			event.message.content.substring(event.message.content.indexOf(" ") + 1, event.message.content.length).split(" "),
			event.message.guild,
			event.message.channel,
			event.message.author
	)
}

class Command(
		val onCalled: CommandFunction,
		vararg val limiters: PermissionLimiter = arrayOf(),
		val description: String = ""
)

class PermissionLimiter(
		val check: PermissionChecker,
		val onFail: CommandFunction = {}
)