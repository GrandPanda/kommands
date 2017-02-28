package com.darichey.discord.kommands.limiters

import com.darichey.discord.kommands.CommandFunction
import com.darichey.discord.kommands.Limiter
import sx.blah.discord.handle.obj.Permissions
import java.util.*

class PermissionsLimiter(permissions: EnumSet<Permissions>, onFail: CommandFunction = {}):
		Limiter({ author.getPermissionsForGuild(guild).any { permissions.contains(it) } }, onFail)