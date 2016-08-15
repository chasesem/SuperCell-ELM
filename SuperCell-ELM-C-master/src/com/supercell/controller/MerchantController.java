package com.supercell.controller;

import com.supercell.entity.Merchant;
import com.supercell.misc.JSONUtil;
import com.supercell.misc.data.LegalMerchant;
import com.supercell.service.MerchantService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZHENGNE2
 */

@Controller
@RequestMapping("merchant")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    @Value("${normal_merchant_url}")
    private String normalMerchantUrl;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Object getAllMerchant() {
        String legalMerchantsURL = normalMerchantUrl;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(legalMerchantsURL);
        Response resp = target.request().get();
        String legalMerchantsString = resp.readEntity(String.class);
        client.close();
        LegalMerchant[] legalMerchants = (LegalMerchant[]) JSONUtil.convertToObject(legalMerchantsString, LegalMerchant[].class);
        List<Merchant> allMerchants = merchantService.getAllMerchant();
        Map<Integer, Merchant> allMerchantsMap = new HashMap<>();
        List<Merchant> merchants = new ArrayList<>();
        if (legalMerchants != null) {
            // 将所有商户的信息放入map中
            for (Merchant merchant : allMerchants) {
                allMerchantsMap.put(merchant.getId(), merchant);
            }
            // 从map中取出所有的商户信息
            for (LegalMerchant legalMerchant : legalMerchants) {
                Merchant merchant = allMerchantsMap.get(legalMerchant.getMerchantId());
                // 删除敏感信息
                if (merchant != null) {
                    merchant.setPassword(null);
                    merchant.setIdCardPicPath(null);
                    merchant.setLicensePicPath(null);
                    merchants.add(merchant);
                }
            }
            // 返回商户列表
            return merchants;
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "{merchantId}", method = RequestMethod.GET)
    public Object getMerchant(@PathVariable("merchantId") Integer merchantId) {
        Merchant merchant = merchantService.getMerchant(merchantId);
        if (merchant != null) {
            merchant.setIdCardPicPath(null);
            merchant.setLicensePicPath(null);
            merchant.setPassword(null);
            return merchant;
        } else {
            return false;
        }
    }
}
