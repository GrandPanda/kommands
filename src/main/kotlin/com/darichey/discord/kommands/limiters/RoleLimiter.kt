package com.darichey.discord.kommands.limiters

import com.darichey.discord.kommands.CommandFunction
import com.darichey.discord.kommands.Limiter
import sx.blah.discord.handle.obj.IRole

class RoleLimiter(vararg roles: String, onFail: CommandFunction = {}) :
		Limiter({ roles.any { role ->
			author.getRolesForGuild(guild).any { it.id == role }
		} }, onFail) {

	constructor(vararg roles: IRole, onFail: CommandFunction = {}):
			this(*roles.map(IRole::getID).toTypedArray(), onFail = onFail)
}