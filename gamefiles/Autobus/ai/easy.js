function onTurn (gameManager) {
    var done;
    var hrac = gameManager.getActivePlayer();
    var stredove =  gameManager.getDecks("middle", null);
    var autobus = gameManager.getDecks("autobus", hrac.getPlayerID());
    var ruka = gameManager.getDecks("hand", hrac.getPlayerID());
    var empty = gameManager.getDecks("empty", null);
    do {
        done = true;
        for (var j = 0; j < ruka.get(0).getCardCount(); j++) {
            c = ruka.get(0).getCard(j);
            if (gameManager.moveCard(c, empty.get(0), empty.get(0).getCardCount())) done = false;
        }
        for (var i = 0; i < stredove.size(); i++) {
            var c = autobus.get(0).getCard(autobus.get(0).getCardCount() - 1);
            
            if (gameManager.moveCard(c, stredove.get(i), stredove.get(i).getCardCount())) done = false;
            for (var j = 0; j < ruka.get(0).getCardCount(); j++) {
                c = ruka.get(0).getCard(j);
                if (gameManager.moveCard(c, stredove.get(i), stredove.get(i).getCardCount())) done = false;
            }
        }
    } while (!done);
    while (ruka.get(0).getCardCount() > gameManager.getPlayerVar(hrac.getPlayerID(), "handsize")) {
        var tmp = ruka.get(0).getCard(0);
        gameManager.moveCard(tmp, autobus.get(0), 0);
    }
    /* Debugging VCS */
}