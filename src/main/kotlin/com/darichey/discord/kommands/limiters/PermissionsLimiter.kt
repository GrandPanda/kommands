package com.darichey.discord.kommands.limiters

import com.darichey.discord.kommands.CommandFunction
import com.darichey.discord.kommands.Limiter
import sx.blah.discord.handle.obj.Permissions
import java.util.*

/**
 * A [Limiter] limiting the use of a command to certain [Permissions].
 * Will pass a command executed by a user with **ANY** of the given permissions. (Not with all)
 */
class PermissionsLimiter(permissions: EnumSet<Permissions>, onFail: CommandFunction = {}):
		Limiter({ author.getPermissionsForGuild(guild).any { permissions.contains(it) } }, onFail)