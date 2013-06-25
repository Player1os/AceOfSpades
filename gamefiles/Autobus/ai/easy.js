function onTurn (#TESTER#) {
    var done;
    var stredove =  #TESTER#.getDecks(null, #IDSTREDOVA#);
    var autobus = #TESTER#.getDecks(#PLAYER#,#IDAUTOBUS#);
    var ruka = #TESTER#.getDecks(#PLAYER#,#IDRUKA#);
    do {
        done = true;
        for (var i = 0; i < stredove.length(); i++) {
            var c = autobus[0].getCard(autobus.length()-1);
            if (#TESTER.moveCard(c.getID(), stredove[i].getID(), stredove[i].length())) done = false;
            for (var j = 0; j < ruka[0].length(); j++) {
                c = ruka[0].getCard(j);
                if (#TESTER.moveCard(c.getID(), stredove[i].getID(), stredove[i].length())) done = false;
            }
        }
    } while (!done);
    while (ruka.length() > #TESTER#.getPlayer().#GET NUMBER OF CARDS#) {
        #TESTER#.moveCard(ruka[0].getCard(0).getID, autobus[0].getID(), 0);
    }
}