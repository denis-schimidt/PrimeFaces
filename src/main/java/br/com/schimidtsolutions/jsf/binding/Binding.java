package br.com.schimidtsolutions.jsf.binding;


public interface Binding<T> {

	public abstract void fromEntity(T entidade);

	public abstract T toEntity();

}