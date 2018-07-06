package br.com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.sistema.dao.CarroDAO;
import br.com.sistema.modelo.Carro;

@FacesConverter(forClass = Carro.class)
public class CarroConverter implements Converter {

	@Inject
	private CarroDAO carroDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Carro retorno = null;

		if (StringUtils.isNoneBlank(value)) {
			retorno = this.carroDAO.buscarPeloCodigo(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Long codigo = ((Carro) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}
