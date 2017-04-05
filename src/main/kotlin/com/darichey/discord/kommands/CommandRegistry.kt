package com.darichey.discord.kommands

/**
 * Used to register and handle the calling of [Command]s.
 *
 * All commands in the [commands] map can be called with [call] and will be tested with all [defaultLimiters] before execution.
 * If an unknown command string is passed to [call], the [onUnknown] function will be executed.
 *
 */
class CommandRegistry(val commands: MutableMap<String, Command> = mutableMapOf(),
					  vararg val defaultLimiters: Limiter = arrayOf(),
					  val onUnknown: CommandFunction = {}) : MutableMap<String, Command> by commands {

	/**
	 * Attempts to execute the command with [name] and provides [ctx] to it.
	 */
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