package com.deskbill.app;

import com.deskbill.controller.MainFrameContrller;

public class MainApp {
	public static void main(String[] args) {
		try
	    {
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	    }
		new MainFrameContrller().setVisible(true);
	}
}
