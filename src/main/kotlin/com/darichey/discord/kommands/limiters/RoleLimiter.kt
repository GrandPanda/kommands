package com.darichey.discord.kommands.limiters

import com.darichey.discord.kommands.CommandFunction
import com.darichey.discord.kommands.Limiter
import sx.blah.discord.handle.obj.IRole

/**
 * A [Limiter] limiting the use of a command to certain [IRole]s.
 * Will pass a command executed by a user with **ANY** of the given roles. (Not with all)
 */
class RoleLimiter(vararg roles: String, onFail: CommandFunction = {}) :
		Limiter({ roles.any { role ->
			author.getRolesForGuild(guild).any { it.id == role }
		} }, onFail) {

	constructor(vararg roles: IRole, onFail: CommandFunction = {}):
			this(*roles.map(IRole::getID).toTypedArray(), onFail = onFail)
}