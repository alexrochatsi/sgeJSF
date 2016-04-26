package mb;

import dao.DaoFactory;
import dominio.Coordenador;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "coordenadorConverter")
public class CoordenadorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Coordenador coordenador;
            DaoFactory daofactory = new DaoFactory();
            coordenador = daofactory.getCoordenadorDao().findById(Integer.valueOf(value));
            return coordenador;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Coordenador) {

            Coordenador coordenador = (Coordenador) value;

            return String.valueOf(coordenador.getId());
        }
        return "";
    }
}
