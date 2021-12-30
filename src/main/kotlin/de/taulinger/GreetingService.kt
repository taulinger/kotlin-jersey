package de.taulinger

class GreetingService : IGreetingService{
    override fun getGreetings(name: String?) : String = if (name != null) "Hello $name!" else "don't know you"
}