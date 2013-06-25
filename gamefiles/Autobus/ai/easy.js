function onTurn (gameManager) {
    var done;
    var hrac = gameManager.getActivePlayer();
    var stredove =  gameManager.getDecks("middle", null);
    var autobus = gameManager.getDecks("autobus", hrac.getPlayerID());
    var ruka = gameManager.getDecks("hand", hrac.getPlayerID());
    do {
        done = true;
        for (var i = 0; i < stredove.size(); i++) {
            var c = autobus[0].getCard(autobus.getCardCount()-1);
            if (gameManager.moveCard(c.getCardID(), stredove[i].getDeckID(), stredove[i].getCardCount())) done = false;
            for (var j = 0; j < ruka[0].getCardCount(); j++) {
                c = ruka[0].getCard(j);
                if (gameManager.moveCard(c.getCardID(), stredove[i].getDeckID(), stredove[i].getCardCount())) done = false;
            }
        }
    } while (!done);
    while (ruka[0].getCardCount() > gameManager.getPlayerVar(hrac.getPlayerID(), "handsize")) {
        var tmp = ruka[0].getCard(0);
        gameManager.moveCard(tmp.getCardID(), autobus[0].getDeckID(), 0);
    }
}