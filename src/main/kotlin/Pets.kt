abstract class Pet(var name: String)

class Cat(name: String): Pet(name)

class Dog(name: String): Pet(name)

class Fish(name: String): Pet(name)

interface Retailer<out T>{
    fun sell(): T
}

class Vet<in T: Pet>{
    fun treat(t: T){
        println("Treating Pet ${t.name}")
    }
}


class CatRetailer : Retailer<Cat>{
    override fun sell(): Cat{
        println("Sell cat")
        return  Cat("")

    }
}

class DogRetailer : Retailer<Dog>{
    override fun sell(): Dog {
        println("Sell dog")
        return Dog("")
    }
}
class FishRetailer : Retailer<Fish> {
    override fun sell(): Fish {
        println("Sell fish")
        return Fish("")
    }
}



class Contest<T: Pet>(var vet: Vet<T>){
    val scores: MutableMap<T, Int> = mutableMapOf()

    fun addScore(t: T, score: Int = 0){
        if (score >= 0) {
            scores[t] = score   // scores.put(t, score) im Buch
        }
    }

    fun getWinners() : MutableSet<T> {
        val highScore = scores.values.max()
        val winners : MutableSet<T> = mutableSetOf()

        for ((t, score) in scores){
            if (score == highScore) winners.add(t)
        }
        return winners
    }
    //More todo
}

/*
class PetOwner<T: Pet>(t: T) {
    val pets = mutableListOf(t)
    fun add(t: T) {
        pets.add(t)
    }

    fun remove(t: T) {
        pets.remove(t)
    }
}
*/

fun main (args : Array<String>) {
    val catRetailer1 = CatRetailer()
    val catRetailer2: CatRetailer = CatRetailer()

    val catVet = Vet<Cat>()
    val dogVet = Vet<Dog>()
    val fishVet = Vet<Fish>()
    val petVet = Vet<Pet>()



    val catFuzz = Cat("Fuzz Lightyear")
    val catKatsu = Cat("Katsu")
    val fishFinny = Fish("Finny McGraw")



    val catContest = Contest<Cat>(petVet)
    catContest.addScore(catFuzz, 50)
    catContest.addScore(catKatsu, 45)

    val topCat = catContest.getWinners().first()
    println("Cat contest winner is ${topCat.name}")


    val petContest = Contest<Pet>(petVet)
    petContest.addScore(fishFinny, 56)
    petContest.addScore(catFuzz, 50)

    val topPet = petContest.getWinners().first()
    println("Pet contest winner is ${topPet.name}")


    val dogRetailer : Retailer<Dog> = DogRetailer()
    val catRetailer : Retailer<Cat> = CatRetailer()
    val fishRetailer :Retailer<Fish> = FishRetailer()

    val petRetailer : Retailer<Pet> = CatRetailer()
    petRetailer.sell()

}

