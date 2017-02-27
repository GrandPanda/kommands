package com.darichey.discord.kommands

import sx.blah.discord.api.events.IListener
import sx.blah.discord.handle.impl.events.MessageReceivedEvent

class CommandListener(val prefix: String, val registry: CommandRegistry) : IListener<MessageReceivedEvent> {
	override fun handle(e: MessageReceivedEvent) {
		if (e.message.content.startsWith(prefix)) {
			val name = e.message.content.split(prefix, " ")[1]
			registry.call(name, CommandContext(e))
		}
	}
}