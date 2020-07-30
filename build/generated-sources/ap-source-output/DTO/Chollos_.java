package DTO;

import DTO.Comentario;
import DTO.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-07T13:40:45")
@StaticMetamodel(Chollos.class)
public class Chollos_ { 

    public static volatile SingularAttribute<Chollos, String> seccion;
    public static volatile SingularAttribute<Chollos, String> foto;
    public static volatile ListAttribute<Chollos, Comentario> comentarioList;
    public static volatile SingularAttribute<Chollos, Integer> codChollo;
    public static volatile SingularAttribute<Chollos, Usuario> idUsuario;
    public static volatile SingularAttribute<Chollos, String> titulo;
    public static volatile SingularAttribute<Chollos, String> enlace;
    public static volatile SingularAttribute<Chollos, Date> fechaChollo;
    public static volatile SingularAttribute<Chollos, String> cuerpo;
    public static volatile SingularAttribute<Chollos, Integer> likes;

}