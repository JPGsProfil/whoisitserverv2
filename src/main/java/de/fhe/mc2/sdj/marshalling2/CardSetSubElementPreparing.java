package de.fhe.mc2.sdj.marshalling2;

/**
 * Created by Jean on 09.02.2016.
 */
/*
public class CardSetSubElementPreparing
{

    public static CardSet CardSetInitializeSubElements(CardSet _cardSet)
    {
        _cardSet.setId(null);
        if(_cardSet.getCards() != null)
        {
            // cascade all cards (only possible if one or more
            for(int cardIndex = 0; cardIndex < _cardSet.getCards().size(); cardIndex ++)
            {
                // for cascade save: each Card obj needs Cardset-obj for foreign key
                _cardSet.getCards().get(cardIndex).setCardSet(_cardSet);
                //  set card id primary key null
                _cardSet.getCards().get(cardIndex).setId(null);
                if(_cardSet.getCards().get(cardIndex).getAttributeList() != null)
                {
                    //cascading only possible if there are some
                    List<Attribute> attributes = _cardSet.getCards().get(cardIndex).getAttributeList();
                    for (int attributeIndex = 0; attributeIndex < attributes.size(); attributeIndex ++)
                    {
                        // for cascade save: each Attribute obj needs Card-obj for foreign key
                        attributes.get(attributeIndex).setCard(_cardSet.getCards().get(cardIndex));
                        // set attribute id primary key null (if android makes a mistake, otherwise causes update instead of insert
                        attributes.get(attributeIndex).setId(null);
                        if(attributes.get(attributeIndex).getValue() != null)
                        {
                            // for cascade save: Value needs Attribute-obj for foreign key
                            attributes.get(attributeIndex).getValue().setAttribute(attributes.get(attributeIndex));
                            // set id value primary key null (if android makes a mistake, otherwise causes update instead of insert
                            attributes.get(attributeIndex).getValue().setId(null);
                        }
                    }
                }
            }
        }
    }


    public static Card InitializeSubElementsAttribute(Card _card)
    {
        if(_attribute.getValue() != null)
        {
            // for cascade save: Value needs Attribute-obj for foreign key
            _attribute.getValue().setAttribute(_attribute);
            // set id value primary key null (if android makes a mistake, otherwise causes update instead of insert
            _attribute.getValue().setId(null);
        }
        return _attribute;
    }


    public static Attribute InitializeSubElementsAttribute(Attribute _attribute)
    {
        if(_attribute.getValue() != null)
        {
            // for cascade save: Value needs Attribute-obj for foreign key
            _attribute.getValue().setAttribute(_attribute);
            // set id value primary key null (if android makes a mistake, otherwise causes update instead of insert
            _attribute.getValue().setId(null);
        }
        return _attribute;
    }
}*/
