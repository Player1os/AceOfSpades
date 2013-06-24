function gameInit(#TESTER#) {

}

function turnStart(#TESTER#) {
    var ruka = #TESTER#.getDecks(#TESTER#.getPlayer(), #IDRUKA#);
    var kopa = #TESTER#.getDeck(#IDKOPA#);
    #TESTER#.getPlayer().#SET NUMBER OF CARDS# = ruka.length;
    #TESTER#.moveCardjs(kopa.id, ruka.id, kopa.length()-1, ruka.length());
    if (kopa.length() < 1) {
        #TESTER#.deleteDeck(#IDKOPA#);
        var zbytok = #TESTER#.getDecks(null, #DONE#)
        #TESTER#.addDeck(0, 0, #IDKOPA#, null, zbytok);
    }
    kopa.shuffle();
}

function add(#TESTER#, #IDDECK#, #IDCARD#, #POSITION#) {
    var deck = #TESTER#.getDeck(#IDDECK#);
    var card = #TETSER#.getCard(#IDCARD#);
    var t = deck.getType();
    if (deck.getPlayer() == #TESTER#.getPlayer()) {
        switch (t) {
            case #IDSTREDOVA#:
                if (deck.getCard(deck.length()-1).getValue() < card.getValue()) {
                        return deck.getCardCount();
                }
                break;
            case #IDAUTOBUS#:
                return 0;
                break;
            case #IDSKLADOVA#:
                if (deck.length() > 0) {
                    if (deck.getCard(0).getValue() == card.getValue()) {
                        return deck.getCardCount();
                    } else {
                        return -1;
                    }
                } else {
                    return 0;
                }
                break;
            case "empty":
                #TESTER#.addDeck(null, #IDSTREDOVA#, #IDCARD#);
            break;
        }
    }
    return -1;
}

function remove(#TESTER#, #IDDECK#, #IDCARD#, #POSITION#) {
    var deck = #TESTER#.getDeck(#IDDECK#);
    var card = #TETSER#.getCard(#IDCARD#);
    var t = deck.getType();
    if (deck.getPlayer() == #TESTER#.getPlayer()) {
        switch (t) {
            case #IDAUTOBUS#:
                if (#POSITION# == deck.getCardCount()-1) {
                    return #POSITION#;
                }
                break;
            case #IDSKLADOVA#:
                return #POSITION#;
                break;
            case #IDRUKA#:
                return #POSITION#;
                break;
        }
    }
   return -1;
}

function afterMove(#TESTER#) {
    checkAutobus(#TESTER#);
    var stredove = #TESTER#.getDecks(#TESTER#.getPlayer(), #IDSTREDOVA#);
    var i = 0;
    while (i < stredove.length()) {
        if (stredove[i].length() > 0) {
            var tmp = stredove[i].getCard(stredove[i].getCardCount()-1).value;
            if (nasielMatch(tmp, #TESTER#, stredove[i])) {
                i = -1;
            }
        }
        i++;
    }
    for (var i = 0; i < stredove.length(); i++) {
        if (stredove[i].length() == 13) stredove[i].setClass(#DONE#);
    }
}

function checkAutobus(#TESTER#) {
    var autobus = #TESTER#.getDecks(#TESTER#.getPlayer(), #IDAUTOBUS#);
    if (autobus.length() > 0) if (!autobus.getCard(autobus.length()-1).visible) autobus.getCard(autobus.length()-1).setVisible(#PLAYER#);
}

function nasielMatch(tmp, #TESTER#, #DECK#) {
    var autobus = #TESTER#.getDecks(#TESTER#.getPlayer(), #IDAUTOBUS#);
    var skladove = #TESTER#.getDecks(#TESTER#.getPlayer(), #IDSKLADOVA#);

    if (autobus.getCard(autobus.length()-1).value == tmp+1) {
        #TESTER#.moveCardjs(autobus.id, #DECK#.id, autobus.length()-1, #DECK#.length());
        checkAutobus(#TESTER#);
        return true;
    }
    for (var i = 0; i < skladove.length(); i++) {
        if (skladove[i].getCard(skladove[i].length()-1).value == tmp+1) {
            #TESTER#.moveCardjs(skladove[i].id, #DECK#.id, skladove[i].length()-1, #DECK#.length());
            return true;
        }
    }
    return false;
}

function turnEnd(#TESTER#) {
    var ruka = #TESTER#.getDecks(#TESTER#.getPlayer(), #IDRUKA#);
    if (#TESTER#.getPlayer().#GET NUMBER OF CARDS# < ruka.length()) return false;
    return true;
}

function winCondition(#TESTER#) {
    var decky = #TESTER#.getDecks(#TESTER#.getPlayer(), null);
    for (var i = 0; i < decky.length(); i++) {
        if (decky[i].length() != 0) {
            return false;
        }
    }
    return true;
}