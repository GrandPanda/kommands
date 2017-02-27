package com.darichey.discord.kommands

class CommandRegistry(val commands: MutableMap<String, Command> = mutableMapOf(),
					  vararg val defaultPermChecks: PermissionChecker = arrayOf()) : MutableMap<String, Command> by commands {

	fun call(name: String, ctx: CommandContext) {
		if (defaultPermChecks.all { it(ctx) }) {
			val command = get(name)
			command?.let {
				if (it.checkPerms.all { it(ctx) }) it.onCalled(ctx)
			}
		}
	}
}