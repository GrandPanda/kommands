package com.darichey.discord.kommands.limiters

import com.darichey.discord.kommands.CommandFunction
import com.darichey.discord.kommands.Limiter
import sx.blah.discord.handle.obj.IGuild

/**
 * A [Limiter] limiting the use of a command to certain [IGuild]s.
 * Will pass a command executed in **ANY** of the given guilds.
 */
class GuildLimiter(vararg guilds: String, onFail: CommandFunction = {}) :
		Limiter({ guilds.contains(guild.id) }, onFail) {

	constructor(vararg guilds: IGuild, onFail: CommandFunction = {}):
			this(*guilds.map(IGuild::getID).toTypedArray(), onFail = onFail)
}