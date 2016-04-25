package mb;

import dao.DaoFactory;
import dominio.RepresentanteEmpresa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "representanteEmpresaConverter")
public class RepresentanteEmpresaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            RepresentanteEmpresa representanteEmpresa;
            DaoFactory daofactory = new DaoFactory();
            representanteEmpresa = daofactory.getRepresentanteEmpresaDao().findById(Integer.valueOf(value));
            return representanteEmpresa;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof RepresentanteEmpresa) {

            RepresentanteEmpresa representanteEmpresa = (RepresentanteEmpresa) value;

            return String.valueOf(representanteEmpresa.getId());
        }
        return "";
    }
}
