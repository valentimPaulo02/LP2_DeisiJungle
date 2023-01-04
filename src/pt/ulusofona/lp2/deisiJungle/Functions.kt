package pt.ulusofona.lp2.deisiJungle

enum class CommandType {GET, POST}

fun getPlayerInfo(manager: GameManager, name : String) : String{

    if(manager.getPlayerByName(name)==null){
        return "Inexistent player"
    }
    return manager.getPlayerByName(name)
            .kotlin_getPlayerInfo()
}

fun getPlayersBySpecie(manager: GameManager, specieId : Char) : String{

    if(!manager.isSpecieValid(specieId)){
        return ""
    }
    if(manager.getPlayerBySpecieId(specieId).size==0){
        return "";
    }
    return manager.getPlayerBySpecieId(specieId)
            .map { it.name }
            .joinToString (",")
}

fun getMostTraveled(manager: GameManager) : String{
    val total = manager.getPlayers()
            .map { it.distanceWalked }
            .sum()

    return manager.getPlayers()
            .sortedWith { i, j -> j.distanceWalked-i.distanceWalked }
            .map { it.kotlin_getMostTraveled() }
            .joinToString("\n") + "\n" +"Total:"+total
}
