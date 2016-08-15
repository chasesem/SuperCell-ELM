/**
 * 展示商家的所有菜品
 */
package com.supercell.controller;

import com.supercell.entity.Dishes;
import com.supercell.misc.JSONUtil;
import com.supercell.misc.data.RecommendedDishes;
import com.supercell.service.DishesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author ZHENGNE2
 */

@Controller
@RequestMapping("dishes")
public class DishesController {
    @Value("${Dishes}")
    private String dishesAttr;
    @Value("${recommended_dishes_url}")
    private String recommendedDishesUrl;
    @Resource
    private DishesService dishesService;

    @RequestMapping(value = "viewDishes", method = RequestMethod.GET)
    public String viewDishes() {
        return "viewDishes";
    }

    @ResponseBody
    @RequestMapping(value = "get/{dishId}")
    public Object getDish(@PathVariable("dishId") Integer dishId) {
        Dishes dishes = dishesService.getDishes(dishId);
        return (dishes != null) ? dishes : false;
    }

    @RequestMapping(value = "{merchantId}", method = RequestMethod.GET)
    public void getDishesOfMerchant(
            @PathVariable("merchantId") Integer merchantId,
            HttpSession httpSession,
            HttpServletResponse response) {
        List<Dishes> dishes = dishesService.getAllDishesOfMerchant(merchantId);
        if (dishes != null) {
            httpSession.setAttribute(dishesAttr, dishes);
            JSONUtil.writeJSONToFrontEnd(response, JSONUtil.convertToJSON(dishes));
        } else {
            JSONUtil.writeJSONToFrontEnd(response, JSONUtil.convertToJSON(false));
        }
    }

    @ResponseBody
    @RequestMapping(value = "recommended", method = RequestMethod.GET)
    public Object getRecommendedDishes() {
        String recommendedDishesURL = recommendedDishesUrl;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(recommendedDishesURL);
        Response resp = target.request().get();
        String recommendedString = resp.readEntity(String.class);
        client.close();
        RecommendedDishes[] recommendedDishes = (RecommendedDishes[]) JSONUtil.convertToObject(recommendedString, RecommendedDishes[].class);
        if (recommendedDishes != null) {
            // 设置Dishes的图片路径
            for (RecommendedDishes dish : recommendedDishes) {
                dish.setDishesImgPath(dishesService.getDishes(dish.getDishesId()).getDishesPicPath());
            }
            // 返回推荐菜品列表
            return recommendedDishes;
        } else {
            return false;
        }
    }
}
