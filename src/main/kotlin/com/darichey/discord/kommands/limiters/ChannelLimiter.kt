package com.darichey.discord.kommands.limiters

import com.darichey.discord.kommands.CommandFunction
import com.darichey.discord.kommands.Limiter
import sx.blah.discord.handle.obj.IChannel

class ChannelLimiter(vararg channels: String, onFail: CommandFunction = {}) :
		Limiter({ channels.contains(channel.id) }, onFail) {

	constructor(vararg channels: IChannel, onFail: CommandFunction = {}):
			this(*channels.map(IChannel::getID).toTypedArray(), onFail = onFail)
}