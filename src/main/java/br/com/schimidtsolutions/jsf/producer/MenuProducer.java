package br.com.schimidtsolutions.jsf.producer;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

import com.google.common.collect.ImmutableList;

public class MenuProducer {

	@Produces @ApplicationScoped
	List<MenuElement> newMapTodosItensMenuOrdenado(){
		
		MenuElement gerarNotaFiscal = new DefaultMenuItem( "Nota Fiscal", "ui-icon-cart", "notafiscal.xhtml" );
		MenuElement listagemNotas = new DefaultMenuItem( "Listagem", "ui-icon-script", "listarnotafiscal.xhtml" );
		MenuElement produto = new DefaultMenuItem( "Produto", "ui-icon-suitcase", "produto.xhtml" );
		DefaultSeparator separador = new DefaultSeparator();
        DefaultMenuItem sair = new DefaultMenuItem( "Sair", "ui-icon-power" );
        sair.setCommand( "#{loginBean.logOut()}" );
        
        return ImmutableList.of( gerarNotaFiscal, listagemNotas, produto, separador, sair );
	}
	
	@Produces @ViewScoped
	MenuModel newMenuModel(){
		MenuModel menuModel = new DefaultMenuModel();
		menuModel.addElement( new DefaultSubMenu( "Sistema de Notas Fiscais" ) );
				
		return menuModel; 
	}
}
