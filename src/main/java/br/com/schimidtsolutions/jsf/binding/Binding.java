package br.com.schimidtsolutions.jsf.binding;

import java.io.Serializable;


public interface Binding<Entity,DTOBinding> extends Serializable{

	public abstract DTOBinding fromEntity(Entity entidade);

	public abstract Entity toEntity();

}