# kommands
An easy-to-use Discord4J Command Library for Kotlin

###Quick Start
Here's a super simple snippet that shows the most basic setup required to get started. See below for more in-depth explanations.
```kotlin
val client: IDiscordClient = ... // Obtained from Discord4J

val helloCommand = Command({
    channel.sendMessage("Hello!")
})

val commands = CommandRegistry(mutableMapOf("hello" to helloCommand))

client.dispatcher.registerListener(CommandListener(prefix = ".", commands))
```

###Gradle
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile 'com.github.GrandPanda:kommands:-SNAPSHOT'
}
```

###Further Explanation
In Kommands, a command is simply a function that determines what to do when the command is called and a series of "limiters" that determine under what circumstances the command can execute. The execution function is a receiver function for a `CommandContext` which contains information about the context in which a command was executed (like channel, author, etc).
Here's an example echo command that simply repeats the first argument of the command back to the channel it was executed in:
```kotlin
val echoCommand = Command({
    channel.sendMessage(args[0])
}, UserLimiter("84766711735136256"))
```
We have direct access to the `CommandContext` within the function.

We also passed a `UserLimiter` which is a custom implementation of `Limiter` which limits the usage of the command to a specific set of users. You can find other useful limiters in `kommands.limiters` or you can easily implement your own! A limiter can also be given a function to execute in the event that the check returns false.
```kotlin
UserLimiter("84766711735136256", onFail = { channel.sendMessage("You're not allowed to use this command!" })
```
You can also provide a default set of limiters to a `CommandRegistry` which will be checked for all registered commands to reduce redundancy.

An important thing to keep in mind about limiters is that they are executed in the order they are passed. First a registry's default ones and then command-specific. This means you probably want to pass them in narrowing scope.