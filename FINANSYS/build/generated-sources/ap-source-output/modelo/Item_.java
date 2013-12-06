package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Produto;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-05T23:17:45")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Long> id;
    public static volatile SingularAttribute<Item, Integer> quantidade;
    public static volatile SingularAttribute<Item, Produto> produto;

}