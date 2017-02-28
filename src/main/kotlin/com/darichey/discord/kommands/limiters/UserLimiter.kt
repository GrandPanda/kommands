package com.darichey.discord.kommands.limiters

import com.darichey.discord.kommands.CommandFunction
import com.darichey.discord.kommands.Limiter
import sx.blah.discord.handle.obj.IUser

/**
 * A [Limiter] limiting the use of a command to certain [IUser]s.
 * Will pass a command executed by **ANY** of the given users.
 */
class UserLimiter(vararg users: String, onFail: CommandFunction = {}) :
		Limiter({ users.contains(author.id) }, onFail) {

	constructor(vararg users: IUser, onFail: CommandFunction = {}):
			this(*users.map(IUser::getID).toTypedArray(), onFail = onFail)
}