package com.darichey.discord.kommands

import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.handle.impl.events.MessageReceivedEvent
import sx.blah.discord.handle.obj.IChannel
import sx.blah.discord.handle.obj.IGuild
import sx.blah.discord.handle.obj.IMessage
import sx.blah.discord.handle.obj.IUser

/**
 * A function to be invoked with a [CommandContext].
 */
typealias CommandFunction = CommandContext.() -> Unit

/**
 * A function that returns whether or not a command should be executed given a [CommandContext].
 */
typealias PermissionChecker = CommandContext.() -> Boolean

/**
 * Contains information about the context in which a command was executed.
 */
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

/**
 * Holds a function to be executed given that the limiters pass.
 * @param onCalled Function to be executed when the command is called.
 * @param limiters Limiters used to determine if the command should be executed.
 */
class Command(
		val onCalled: CommandFunction,
		vararg val limiters: Limiter = arrayOf()
)

/**
 * Used to limit the execution of a command depending on the result of the given [PermissionChecker].
 * @param check Function that checks if the command should be executed.
 * @param onFail Function that is executed if [check] returns false.
 */
open class Limiter(
		val check: PermissionChecker,
		val onFail: CommandFunction = {}
)