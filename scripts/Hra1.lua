-- GameInit BEGIN
function gameInit(Application)
	idCardset1 = Application:createCardSet(100, 100, "deck1")

	Application:createCard("2", "hearts", 0, 0, idCardset1, 0)
	Application:createCard("3", "hearts", 0, 0, idCardset1, 1)
	Application:createCard("4", "hearts", 0, 0, idCardset1, 2)
	Application:createCard("5", "hearts", 0, 0, idCardset1, 3)
	Application:createCard("6", "hearts", 0, 0, idCardset1, 4)
	Application:createCard("7", "hearts", 0, 0, idCardset1, 5)
	Application:createCard("8", "hearts", 0, 0, idCardset1, 6)
	Application:createCard("9", "hearts", 0, 0, idCardset1, 7)
	Application:createCard("10", "hearts", 0, 0, idCardset1, 8)
	Application:createCard("J", "hearts", 0, 0, idCardset1, 9)
	Application:createCard("Q", "hearts", 0, 0, idCardset1, 10)
	Application:createCard("K", "hearts", 0, 0, idCardset1, 11)
	Application:createCard("A", "hearts", 0, 0, idCardset1, 12)

	idCardset2 = Application:createCardSet(200, 200, "deck2")

	Application:createCard("2", "clubs", 0, 0, idCardset2, 0)
	Application:createCard("3", "clubs", 0, 0, idCardset2, 1)
	Application:createCard("4", "clubs", 0, 0, idCardset2, 2)
	Application:createCard("5", "clubs", 0, 0, idCardset2, 3)
	Application:createCard("6", "clubs", 0, 0, idCardset2, 4)
	Application:createCard("7", "clubs", 0, 0, idCardset2, 5)
	Application:createCard("8", "clubs", 0, 0, idCardset2, 6)
	Application:createCard("9", "clubs", 0, 0, idCardset2, 7)
	Application:createCard("10", "clubs", 0, 0, idCardset2, 8)
	Application:createCard("J", "clubs", 0, 0, idCardset2, 9)
	Application:createCard("Q", "clubs", 0, 0, idCardset2, 10)
	Application:createCard("K", "clubs", 0, 0, idCardset2, 11)
	Application:createCard("A", "clubs", 0, 0, idCardset2, 12)

	idCardset3 = Application:createCardSet(300, 300, "deck3")

	Application:createCard("2", "diamonds", 0, 0, idCardset3, 0)
	Application:createCard("3", "diamonds", 0, 0, idCardset3, 1)
	Application:createCard("4", "diamonds", 0, 0, idCardset3, 2)
	Application:createCard("5", "diamonds", 0, 0, idCardset3, 3)
	Application:createCard("6", "diamonds", 0, 0, idCardset3, 4)
	Application:createCard("7", "diamonds", 0, 0, idCardset3, 5)
	Application:createCard("8", "diamonds", 0, 0, idCardset3, 6)
	Application:createCard("9", "diamonds", 0, 0, idCardset3, 7)
	Application:createCard("10", "diamonds", 0, 0, idCardset3, 8)
	Application:createCard("J", "diamonds", 0, 0, idCardset3, 9)
	Application:createCard("Q", "diamonds", 0, 0, idCardset3, 10)
	Application:createCard("K", "diamonds", 0, 0, idCardset3, 11)
	Application:createCard("A", "diamonds", 0, 0, idCardset3, 12)

	idCardset4 = Application:createCardSet(400, 400, "deck4")

	Application:createCard("2", "spades", 0, 0, idCardset4, 0)
	Application:createCard("3", "spades", 0, 0, idCardset4, 1)
	Application:createCard("4", "spades", 0, 0, idCardset4, 2)
	Application:createCard("5", "spades", 0, 0, idCardset4, 3)
	Application:createCard("6", "spades", 0, 0, idCardset4, 4)
	Application:createCard("7", "spades", 0, 0, idCardset4, 5)
	Application:createCard("8", "spades", 0, 0, idCardset4, 6)
	Application:createCard("9", "spades", 0, 0, idCardset4, 7)
	Application:createCard("10", "spades", 0, 0, idCardset4, 8)
	Application:createCard("J", "spades", 0, 0, idCardset4, 9)
	Application:createCard("Q", "spades", 0, 0, idCardset4, 10)
	Application:createCard("K", "spades", 0, 0, idCardset4, 11)
	Application:createCard("A", "spades", 0, 0, idCardset4, 12)
end
-- GameInit END

-- GameRules BEGIN

function deck1Add(tester)
	if tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit() ~= "clubs" then
		tester:setB(false)
	end
end

function deck1Remove(tester)

end

function deck2Add(tester)
	if tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit() ~= "hearts" then
		tester:setB(false)
	end
end

function deck2Remove(tester)

end

function deck3Add(tester)
	if tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit() ~= "diamonds" then
		tester:setB(false)
	end
end

function deck3Remove(tester)

end

function deck4Add(tester)
	if tester:getOldCardSet():getCard(tester:getOldPosition()):getSuit() ~= "spades" then
		tester:setB(false)
	end
end

function deck4Remove(tester)

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
