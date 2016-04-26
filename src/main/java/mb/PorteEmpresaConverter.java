package mb;

import dao.DaoFactory;
import dominio.PorteEmpresa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "porteEmpresaConverter")
public class PorteEmpresaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            PorteEmpresa porteEmpresa;
            DaoFactory daofactory = new DaoFactory();
            porteEmpresa = daofactory.getPorteEmpresaDao().findById(Integer.valueOf(value));
            return porteEmpresa;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof PorteEmpresa) {

            PorteEmpresa porteEmpresa = (PorteEmpresa) value;

            return String.valueOf(porteEmpresa.getId());
        }
        return "";
    }
}
