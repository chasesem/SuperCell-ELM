package com.supercell.elmm.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.stream.FileImageInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supercell.elmm.global.Global;
import com.supercell.elmm.util.PicShowUtil;

@Controller
@RequestMapping("/pic")
public class PicController {
	@Value("${IMG_SAVE_PATH}")
	private String imgSavePath;
	
	@RequestMapping("/pic.do/{imgPath}")
	public void getPic(OutputStream os,@PathVariable String imgPath) throws IOException{
		PicShowUtil.getPic(os, imgSavePath+"\\"+imgPath);
	}
}
