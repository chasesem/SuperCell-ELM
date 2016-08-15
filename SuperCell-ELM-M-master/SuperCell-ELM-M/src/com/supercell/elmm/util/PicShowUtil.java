package com.supercell.elmm.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.stream.FileImageInputStream;

public class PicShowUtil {
	public static void getPic(OutputStream os,String imgPath) throws IOException{
		String path=imgPath;
		FileImageInputStream files = new FileImageInputStream(new File(path));
		byte[] buff = new byte[2048];
		while (files.read(buff)!=-1) {
			os.write(buff);
		}
	}
}
