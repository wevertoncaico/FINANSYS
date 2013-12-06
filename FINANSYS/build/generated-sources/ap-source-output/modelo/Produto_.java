package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-05T23:17:45")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile SingularAttribute<Produto, Long> id;
    public static volatile SingularAttribute<Produto, Double> preco;
    public static volatile SingularAttribute<Produto, Integer> qntEstoque;
    public static volatile SingularAttribute<Produto, String> nome;

}