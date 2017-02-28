package com.darichey.discord.kommands

/**
 * Used to register and handle the calling of [Command]s.
 *
 * @param commands The initial map of commands. Name to Command.
 * @param defaultLimiters Default array of limiters. All commands called through this registry will be limited by these.
 * @param onUnknown Function to be called when an attempt to call an unknown command is made.
 */
class CommandRegistry(val commands: MutableMap<String, Command> = mutableMapOf(),
					  vararg val defaultLimiters: Limiter = arrayOf(),
					  val onUnknown: CommandFunction = {}) : MutableMap<String, Command> by commands {

	/**
	 * Attempts to execute a command.
	 * @param name The name of the command to execute.
	 * @param ctx The [CommandContext] to provide to the command's [CommandFunction]
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