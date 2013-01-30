-- GameInit BEGIN
function gameInit(Application)
    idCardset1 = Application:createCardSet(600, 100, "deck1", 255, 255, 255)
    Application:createCard("A", "hearts", 0, 0, idCardset1, 0, true)
    idCardset2 = Application:createCardSet(700, 100, "deck2", 255, 255, 255)
    idCardset3 = Application:createCardSet(800, 100, "deck3", 255, 255, 255)
    idCardset4 = Application:createCardSet(900, 100, "deck4", 255, 255, 255)

    idCardset5 = Application:createCardSet(300, 100, "draw1", 0, 0, 0)
        Application:createCard("A", "diamonds", 0, 0, idCardset5, 0, true)
        Application:createCard("K", "spades", 0, 0, idCardset5, 1, false)
        Application:createCard("K", "diamonds", 0, 0, idCardset5, 2, false)
        Application:createCard("4", "spades", 0, 0, idCardset5, 3, false)
        Application:createCard("Q", "clubs", 0, 0, idCardset5, 4, false)
        Application:createCard("5", "hearts", 0, 0, idCardset5, 5, false)
        Application:createCard("Q", "spades", 0, 0, idCardset5, 6, false)
        Application:createCard("K", "clubs", 0, 0, idCardset5, 7, false)
        Application:createCard("J", "diamonds", 0, 0, idCardset5, 8, false)
        Application:createCard("K", "hearts", 0, 0, idCardset5, 9, false)
        Application:createCard("9", "diamonds", 0, 0, idCardset5, 10, false)
        Application:createCard("9", "spades", 0, 0, idCardset5, 11, false)
        Application:createCard("10", "diamonds", 0, 0, idCardset5, 12, false)
        Application:createCard("8", "spades", 0, 0, idCardset5, 13, false)
        Application:createCard("7", "clubs", 0, 0, idCardset5, 14, false)
        Application:createCard("3", "spades", 0, 0, idCardset5, 15, false)
       	Application:createCard("5", "spades", 0, 0, idCardset5, 16, false)
       	Application:createCard("10", "spades", 0, 0, idCardset5, 17, false)
        Application:createCard("5", "clubs", 0, 0, idCardset5, 18, false)
        Application:createCard("8", "diamonds", 0, 0, idCardset5, 19, false)
        Application:createCard("Q", "hearts", 0, 0, idCardset5, 20, false)
        Application:createCard("Q", "diamonds", 0, 0, idCardset5, 21, false)
        Application:createCard("7", "spades", 0, 0, idCardset5, 22, false)

    idCardset6 = Application:createCardSet(300, 400, "help1", 150, 150, 255)

        Application:createCard("2", "hearts", 0, 0, idCardset6, 0, true)

    idCardset7 = Application:createCardSet(400, 400, "help2", 150, 150, 255)

        Application:createCard("8", "clubs", 0, 0, idCardset7, 0, true)
        Application:createCard("J", "spades", 0, 0, idCardset7, 1, false)

    idCardset8 = Application:createCardSet(500, 400, "help3", 150, 150, 255)


        Application:createCard("3", "hearts", 0, 0, idCardset8, 0, true)
        Application:createCard("J", "clubs", 0, 0, idCardset8, 1, false)
        Application:createCard("2", "diamonds", 0, 0, idCardset8, 2, false)

    idCardset9 = Application:createCardSet(600, 400, "help4", 150, 150, 255)

        Application:createCard("6", "clubs", 0, 0, idCardset9, 0, true)
        Application:createCard("6", "hearts", 0, 0, idCardset9, 1, false)
        Application:createCard("4", "hearts", 0, 0, idCardset9, 2, false)
        Application:createCard("3", "diamonds", 0, 0, idCardset9, 3, false)

    idCardset10 = Application:createCardSet(700, 400, "help5", 150, 150, 255)

        Application:createCard("J", "hearts", 0, 0, idCardset10, 0, true)
        Application:createCard("4", "clubs", 0, 0, idCardset10, 1, false)
        Application:createCard("6", "spades", 0, 0, idCardset10, 2, false)
        Application:createCard("9", "hearts", 0, 0, idCardset10, 3, false)
        Application:createCard("A", "clubs", 0, 0, idCardset10, 4, false)

    idCardset11 = Application:createCardSet(800, 400, "help6", 150, 150, 255)

        Application:createCard("A", "spades", 0, 0, idCardset11, 0, true)
        Application:createCard("3", "clubs", 0, 0, idCardset11, 1, false)
        Application:createCard("7", "hearts", 0, 0, idCardset11, 2, false)
        Application:createCard("7", "diamonds", 0, 0, idCardset11, 3, false)
        Application:createCard("10", "clubs", 0, 0, idCardset11, 4, false)
        Application:createCard("6", "diamonds", 0, 0, idCardset11, 5, false)

    idCardset12 = Application:createCardSet(900, 400, "help7", 150, 150, 255)

        Application:createCard("8", "hearts", 0, 0, idCardset12, 0, true)
        Application:createCard("5", "diamonds", 0, 0, idCardset12, 1, false)
        Application:createCard("2", "clubs", 0, 0, idCardset12, 2, false)
        Application:createCard("10", "hearts", 0, 0, idCardset12, 3, false)
        Application:createCard("9", "clubs", 0, 0, idCardset12, 4, false)
        Application:createCard("4", "diamonds", 0, 0, idCardset12, 5, false)
    	Application:createCard("2", "spades", 0, 0, idCardset12, 6, false)
end
-- GameInit END

-- GameRules BEGIN

function deck1Add(tester)
	if tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit() ~= "clubs" then
		tester:setB(false)
	end
end

function deck1Remove(tester)
    tester:setB(false)
end

function deck2Add(tester)
	if tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit() ~= "hearts" then
		tester:setB(false)
	end
end

function deck2Remove(tester)
    tester:setB(false)
end

function deck3Add(tester)
	if tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit() ~= "diamonds" then
		tester:setB(false)
	end
end

function deck3Remove(tester)
    tester:setB(false)
end

function deck4Add(tester)
	if tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit() ~= "spades" then
		tester:setB(false)
	end
end

function deck4Remove(tester)
    tester:setB(false)
end

function draw1Add(tester)
	tester:setB(false)
end

function draw1Remove(tester)
    if tester:getOldCardSet():getCard(tester:getOldPosition()):getVisible() ~= true then
    		tester:setB(false)
    end
end

function compare(a, b, x, y)
   if a == "hearts" then
            if b == "hearts" or b == "diamonds" then
                return false
            end
   end
   if a == "diamonds" then
            if b == "hearts" or b == "diamonds" then
                return false
            end
   end
   if a == "spades" then
            if b == "spades" or b == "clubs" then
                return false
            end
   end
   if a == "clubs" then
            if b == "spades" or b == "clubs" then
                return false
            end
   end

    if x == "A" then x = "1" end
    if y == "A" then y = "1" end
    if x == "K" then x = "13" end
    if y == "K" then y = "13" end
    if x == "Q" then x = "12" end
    if y == "Q" then y = "12" end
    if x == "J" then x = "11" end
    if y == "J" then y = "11" end
    if y < x then return false end

    return true
end

function help1Add(tester)
   a=tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit()
   b=tester:getNewCardSet():getCard(0):getSuit()
   x=tester:getOldCardSet():getCard(tester:getOldPosition()):getValue()
   y=tester:getNewCardSet():getCard(0):getValue()
   if compare(a,b,x,y) == false then
       tester:setB(false)
   end
   if tester:getNewPosition() > 0 then tester:setNewPosition(0) end
end

function help1Remove(tester)
   if tester:getOldCardSet():getCard(tester:getOldPosition()):getVisible() ~= true then
       tester:setB(false)
   end
end

function help2Add(tester)
   a=tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit()
   b=tester:getNewCardSet():getCard(0):getSuit()
   x=tester:getOldCardSet():getCard(tester:getOldPosition()):getValue()
   y=tester:getNewCardSet():getCard(0):getValue()
   if compare(a,b,x,y) == false then
        tester:setB(false)
   end
   if tester:getNewPosition() > 0 then tester:setNewPosition(0) end
end

function help2Remove(tester)
   if tester:getOldCardSet():getCard(tester:getOldPosition()):getVisible() ~= true then
       tester:setB(false)
   end
end

function help3Add(tester)
    a=tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit()
    b=tester:getNewCardSet():getCard(0):getSuit()
    x=tester:getOldCardSet():getCard(tester:getOldPosition()):getValue()
    y=tester:getNewCardSet():getCard(0):getValue()
	if compare(a,b,x,y) == false then
		tester:setB(false)
	end
	if tester:getNewPosition() > 0 then tester:setNewPosition(0) end
end

function help3Remove(tester)
    if tester:getOldCardSet():getCard(tester:getOldPosition()):getVisible() ~= true then
        tester:setB(false)
    end
end

function help4Add(tester)
    a=tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit()
    b=tester:getNewCardSet():getCard(0):getSuit()
    x=tester:getOldCardSet():getCard(tester:getOldPosition()):getValue()
    y=tester:getNewCardSet():getCard(0):getValue()
	if compare(a,b,x,y) == false then
		tester:setB(false)
	end
	if tester:getNewPosition() > 0 then tester:setNewPosition(0) end
end

function help4Remove(tester)
    if tester:getOldCardSet():getCard(tester:getOldPosition()):getVisible() ~= true then
        tester:setB(false)
    end
end

function help5Add(tester)
    a=tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit()
    b=tester:getNewCardSet():getCard(0):getSuit()
    x=tester:getOldCardSet():getCard(tester:getOldPosition()):getValue()
    y=tester:getNewCardSet():getCard(0):getValue()
	if compare(a,b,x,y) == false then
		tester:setB(false)
	end
	if tester:getNewPosition() > 0 then tester:setNewPosition(0) end
end

function help5Remove(tester)
    if tester:getOldCardSet():getCard(tester:getOldPosition()):getVisible() ~= true then
        tester:setB(false)
    end
end

function help6Add(tester)
    a=tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit()
    b=tester:getNewCardSet():getCard(0):getSuit()
    x=tester:getOldCardSet():getCard(tester:getOldPosition()):getValue()
    y=tester:getNewCardSet():getCard(0):getValue()
	if compare(a,b,x,y) == false then
		tester:setB(false)
	end
	if tester:getNewPosition() > 0 then tester:setNewPosition(0) end
end

function help6Remove(tester)
    if tester:getOldCardSet():getCard(tester:getOldPosition()):getVisible() ~= true then
        tester:setB(false)
    end
end

function help7Add(tester)
    a=tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit()
    b=tester:getNewCardSet():getCard(0):getSuit()
    x=tester:getOldCardSet():getCard(tester:getOldPosition()):getValue()
    y=tester:getNewCardSet():getCard(0):getValue()
	if compare(a,b,x,y) == false then
		tester:setB(false)
	end
	if tester:getNewPosition() > 0 then tester:setNewPosition(0) end
end

function help7Remove(tester)
    if tester:getOldCardSet():getCard(tester:getOldPosition()):getVisible() ~= true then
        tester:setB(false)
    end
end

function afterMove(tester)
    if tester:getOldPosition() > 0 then
            for i=tester:getOldPosition()-1, 0 do
                tester:getOldCardSet():getCard(i):moveTo(tester:getNewCardSet(), 0)
            end
    end
    if tester:getOldCardSet():getCardCount() > 0 then
        tester:getOldCardSet():getCard(0):setVisible(true)
    end
end

-- GameRules END

-- GameWinConds BEGIN
function gameWinConds(Application)
	size = Application:getCardSet(0):getCardCount()
	for i=0,size -1 do
		if Application:getCardSet(0):getCard(i):getSuit() ~= "clubs" then
			return
		end
    end

	size = Application:getCardSet(1):getCardCount()
	for i=0,size -1 do
		if Application:getCardSet(1):getCard(i):getSuit() ~= "hearts" then
			return
		end
    end

	size = Application:getCardSet(2):getCardCount()
	for i=0,size -1 do
		if Application:getCardSet(2):getCard(i):getSuit() ~= "diamonds" then
			return
		end
    end

	size = Application:getCardSet(3):getCardCount()
	for i=0,size -1 do
		if Application:getCardSet(3):getCard(i):getSuit() ~= "spades" then
			return
		end
    end

	Application:setWin(true)

end
-- GameWinConds END
