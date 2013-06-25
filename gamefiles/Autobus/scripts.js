function gameInit(gameManager) {
    vyrobKopu();
    var kopa = gameManager.getDeck(0);
    var tmp;
    for (var i = 0; i < gameManager.getPlayerCount(); i++) {
        tmp = gameManager.createDeck("hand");
        tmp.addOwner(i);
        for (var j = 0; j < 5; j++) {
            var card = kopa.getCard(kopa.getCardCount()-1);
            card.setVisible(i);
            gameManager.uncheckedMoveCard(card, tmp, 0);
        }
        tmp = gameManager.createDeck("autobus");
        tmp.addOwner(i);
        for (var j = 0; j < 10; j++) {
            var card = kopa.getCard(kopa.getCardCount()-1);
            if (j == 0) card.setVisible(i);
            gameManager.uncheckedMoveCard(card, tmp, 0);
        }
        tmp = gameManager.createDeck("stack");
        tmp.addOwner(i);
        tmp = gameManager.createDeck("stack");
        tmp.addOwner(i);
        tmp = gameManager.createDeck("stack");
        tmp.addOwner(i);
        tmp = gameManager.createDeck("stack");
        tmp.addOwner(i);
        tmp = gameManager.createDeck("stack");
        tmp.addOwner(i);
    }
    tmp = gameManager.createDeck("empty");
    tmp.setOwnedByAll();
}

function vyrobKopu(gameManager) {
    var d = createDeck("pile");
    d.setOwnedByAll();
    d.addCard(0, gameManager.createCard("A", "hearts"));
    d.addCard(0, gameManager.createCard("2", "hearts"));
    d.addCard(0, gameManager.createCard("3", "hearts"));
    d.addCard(0, gameManager.createCard("4", "hearts"));
    d.addCard(0, gameManager.createCard("5", "hearts"));
    d.addCard(0, gameManager.createCard("6", "hearts"));
    d.addCard(0, gameManager.createCard("7", "hearts"));
    d.addCard(0, gameManager.createCard("8", "hearts"));
    d.addCard(0, gameManager.createCard("9", "hearts"));
    d.addCard(0, gameManager.createCard("10", "hearts"));
    d.addCard(0, gameManager.createCard("J", "hearts"));
    d.addCard(0, gameManager.createCard("Q", "hearts"));
    d.addCard(0, gameManager.createCard("K", "hearts"));

    d.addCard(0, gameManager.createCard("A", "diamonds"));
    d.addCard(0, gameManager.createCard("2", "diamonds"));
    d.addCard(0, gameManager.createCard("3", "diamonds"));
    d.addCard(0, gameManager.createCard("4", "diamonds"));
    d.addCard(0, gameManager.createCard("5", "diamonds"));
    d.addCard(0, gameManager.createCard("6", "diamonds"));
    d.addCard(0, gameManager.createCard("7", "diamonds"));
    d.addCard(0, gameManager.createCard("8", "diamonds"));
    d.addCard(0, gameManager.createCard("9", "diamonds"));
    d.addCard(0, gameManager.createCard("10", "diamonds"));
    d.addCard(0, gameManager.createCard("J", "diamonds"));
    d.addCard(0, gameManager.createCard("Q", "diamonds"));
    d.addCard(0, gameManager.createCard("K", "diamonds"));

    d.addCard(0, gameManager.createCard("A", "spades"));
    d.addCard(0, gameManager.createCard("2", "spades"));
    d.addCard(0, gameManager.createCard("3", "spades"));
    d.addCard(0, gameManager.createCard("4", "spades"));
    d.addCard(0, gameManager.createCard("5", "spades"));
    d.addCard(0, gameManager.createCard("6", "spades"));
    d.addCard(0, gameManager.createCard("7", "spades"));
    d.addCard(0, gameManager.createCard("8", "spades"));
    d.addCard(0, gameManager.createCard("9", "spades"));
    d.addCard(0, gameManager.createCard("10", "spades"));
    d.addCard(0, gameManager.createCard("J", "spades"));
    d.addCard(0, gameManager.createCard("Q", "spades"));
    d.addCard(0, gameManager.createCard("K", "spades"));

    d.addCard(0, gameManager.createCard("A", "clubs"));
    d.addCard(0, gameManager.createCard("2", "clubs"));
    d.addCard(0, gameManager.createCard("3", "clubs"));
    d.addCard(0, gameManager.createCard("4", "clubs"));
    d.addCard(0, gameManager.createCard("5", "clubs"));
    d.addCard(0, gameManager.createCard("6", "clubs"));
    d.addCard(0, gameManager.createCard("7", "clubs"));
    d.addCard(0, gameManager.createCard("8", "clubs"));
    d.addCard(0, gameManager.createCard("9", "clubs"));
    d.addCard(0, gameManager.createCard("10", "clubs"));
    d.addCard(0, gameManager.createCard("J", "clubs"));
    d.addCard(0, gameManager.createCard("Q", "clubs"));
    d.addCard(0, gameManager.createCard("K", "clubs"));
    d.shuffle();
    d.shuffle();
    d.shuffle();
}

function turnStart(gameManager) {
    var hrac = gameManager.getActivePlayer();
    var ruka = gameManager.getDeck(1 + (hrac.getPlayerID() * 7));
    var kopa = gameManager.getDeck(0);
    gameManager.setPlayerVar(hrac.getPlayerID(), "handsize", ruka.getCardCount());
    gameManager.uncheckedMoveCard(kopa.getCard(kopa.getCardCount() - 1));
    if (kopa.getCardCount() < 1) {
        gameManager.mergeDecks(gameManager.getDecks("done", null), kopa);
        kopa.shuffle();
    }
}

function canAdd(gameManager, card, deck, pos) {
    var hrac = gameManager.getActivePlayer();
    var t = deck.getType();
        switch (t) {
            case "middle":
                if (deck.getCard(deck.getCardCount()-1).getNumValue(false) < card.getNumValue(false)) {
                        return deck.getCardCount();
                }
                break;
            case "autobus":
                card.unsetVisible(hrac.getPlayerID());
                return 0;
                break;
            case "stack":
                if (deck.length() > 0) {
                    if (deck.getCard(0).getNumValue(false) == card.getNumValue(false)) {
                        return deck.getCardCount();
                    } else {
                        return -1;
                    }
                } else {
                    return 0;
                }
                break;
            case "empty":
                var d = gameManager.createDeck("empty");
                d.setOwnedByAll();
                return 0;
                break;
        }
    return -1;
}

function canRemove(gameManager, card, deck, pos) {
    var t = deck.getType();
        switch (t) {
            case "autobus":
                if (pos == deck.getCardCount()-1) {
                    return pos;
                }
                break;
            case "stack":
                return pos;
                break;
            case "hand":
                return pos;
                break;
        }
   return -1;
}

function afterMove(gameManager) {
    checkAutobus(gameManager);
    var stredove = gameManager.getDecks("middle", null);
    var i = 0;
    while (i < stredove.size()) {
        if (stredove[i].getCardCount() > 0) {
            var tmp = stredove[i].getCard(stredove[i].getCardCount()-1).getNumValue(false);
            if (nasielMatch(tmp, gameManager, stredove[i])) {
                i = -1;
            }
        }
        i++;
    }
    for (var i = 0; i < stredove.size(); i++) {
        if (stredove[i].getCardCount() == 13) stredove[i].setType("done");
    }
}

function checkAutobus(gameManager) {
    var hrac = gameManager.getActivePlayer();
    var autobus = gameManager.getDeck(2 + (hrac.getPlayerID() * 7));
    if (autobus.length() > 0) if (!autobus.getCard(autobus.length()-1).isVisible(hrac.getPlayerID())) autobus.getCard(autobus.length()-1).setVisible(hrac.getPlayerID());
}

function nasielMatch(tmp, gameManager, deck) {
    var hrac = gameManager.getActivePlayer();
    var autobus = gameManager.getDeck(2 + (hrac.getPlayerID() * 7));
    var skladove = gameManager.getDecks("stack", hrac.getPlayerID());

    if (autobus.getCard(autobus.getCardCount()-1).getNumValue(false) == tmp+1) {
        gameManager.uncheckedMoveCard(autobus.getCard(autobus.getCardCount()-1), deck, deck.getCardCount());
        checkAutobus(gameManager);
        return true;
    }
    for (var i = 0; i < skladove.size(); i++) {
        if (skladove[i].getCard(skladove[i].getCardCount()-1).getNumValue(false) == tmp+1) {
            gameManager.uncheckedMoveCard(skladove[i].getCard(skladove[i].getCardCount()-1), deck, deck.getCardCount());
            return true;
        }
    }
    return false;
}

function canEndTurn(gameManager) {
    var hrac = gameManager.getActivePlayer();
    var ruka = gameManager.getDeck(2 + (hrac.getPlayerID() * 7));
    if (gameManager.getPlayerVar(hrac.getPlayerID(), "handsize") < ruka.getCardCount()) return false;
    return true;
}

function winCondition(gameManager) {
    var hrac = gameManager.getActivePlayer();
    var decky = gameManager.getDecks(null, hrac.getPlayerID());
    for (var i = 0; i < decky.size(); i++) {
        if (decky[i].getCardCount() != 0) {
            return false;
        }
    }
    return true;
}