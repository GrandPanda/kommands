package com.darichey.discord.kommands

class CommandRegistry(val commands: MutableMap<String, Command> = mutableMapOf(),
					  vararg val defaultLimiters: PermissionLimiter = arrayOf(),
					  val onUnknown: CommandFunction = {}) : MutableMap<String, Command> by commands {

	fun call(name: String, ctx: CommandContext) {
		val cmd: Command? = get(name)
		if (cmd != null) {
			defaultLimiters.forEach {
				if (!it.check(ctx)) {
					it.onFail(ctx)
					return@call
				}
			}

			cmd.limiters.forEach {
				if (!it.check(ctx)) {
					it.onFail(ctx)
					return@call
				}
			}

			cmd.onCalled(ctx)
		} else {
			onUnknown(ctx)
		}
	}
}