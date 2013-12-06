package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-05T23:17:45")
@StaticMetamodel(Despesa.class)
public class Despesa_ { 

    public static volatile SingularAttribute<Despesa, Long> id;
    public static volatile SingularAttribute<Despesa, Date> dat;
    public static volatile SingularAttribute<Despesa, Double> valor;
    public static volatile SingularAttribute<Despesa, SimpleDateFormat> formatador;
    public static volatile SingularAttribute<Despesa, String> status;
    public static volatile SingularAttribute<Despesa, String> descricao;
    public static volatile SingularAttribute<Despesa, String> dataF;

}