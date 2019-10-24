package com.deskbill.tools;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import org.apache.commons.beanutils.BeanUtils;
@SuppressWarnings({ "rawtypes" })
public class ComBoxModelUtils<T> extends AbstractListModel implements ComboBoxModel{

	private static final long serialVersionUID = -4251619172027631369L;
	private List<T> list;
	private String name;
	//private Class<?> c;
	private String item;
	
	
	public ComBoxModelUtils(List<T> list,String name) {
		super();
		this.list = list;
		this.name = name;
		//this.c = c;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		try {
			String property = BeanUtils.getProperty(list.get(index), name);
			return property;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		//list.get(index)
		item=(String)anItem; 
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return item;
	}




}
