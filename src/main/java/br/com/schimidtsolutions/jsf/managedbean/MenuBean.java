package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

@Named
@ViewScoped
public class MenuBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject @SessionScoped
	private LoginBean loginBean;
	
	@Inject
	private MenuModel menuModel;
	
	@Inject 
	private List<MenuElement> menuElements;
	
	public boolean isExibirMenuBar(){
		return loginBean.isLogado();
	}
	
	public void atualizarExibicaoMenu( String urlTela ){

		DefaultSubMenu submenu = obterPrimeiroNivelSubmenu();
		
		for( MenuElement element : menuElements ){
			
			if( element instanceof MenuItem ){
				MenuItem itemMenu = (MenuItem) element;
				
				if( StringUtils.isNotBlank( itemMenu.getUrl() ) &&
						itemMenu.getUrl().toString().equals( urlTela ) ){
					continue;
				}
			}
			
			submenu.addElement( element );
		}
	}

	private DefaultSubMenu obterPrimeiroNivelSubmenu() {
		return (DefaultSubMenu) menuModel.getElements().get( 0 );
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}
}
