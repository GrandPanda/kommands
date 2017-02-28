package com.darichey.discord.kommands

import sx.blah.discord.api.events.IListener
import sx.blah.discord.handle.impl.events.MessageReceivedEvent

/**
 * A message listener that attempts to execute commands in the given [CommandRegistry] if the message begins with the given prefix.
 * @param prefix The prefix to check messages for.
 * @param registry The registry to call commands in.
 */
class CommandListener(val prefix: String, val registry: CommandRegistry) : IListener<MessageReceivedEvent> {
	override fun handle(e: MessageReceivedEvent) {
		if (e.message.content.startsWith(prefix)) {
			val name = e.message.content.split(prefix, " ")[1]
			registry.call(name, CommandContext(e, prefix))
		}
	}
}