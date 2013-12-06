package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Item;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-05T23:17:45")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Long> id;
    public static volatile SingularAttribute<Venda, Date> dataVenda;
    public static volatile ListAttribute<Venda, Item> itens;
    public static volatile SingularAttribute<Venda, Double> totalDaVenda;

}