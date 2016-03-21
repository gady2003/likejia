package com.wei42.weixin.service;

import com.wei42.weixin.domain.menu.Button;
import com.wei42.weixin.domain.menu.Menu;
import com.wei42.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

public class MenuService {
	
	public static Logger log = Logger.getLogger(MenuService.class);

	/**
	 * 菜单创建（POST） 限100（次/天）
	 */
	public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 菜单查询
	 */
	public static String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param jsonMenu
	 *            json格式
	 * @return 状态 0 表示成功、其他表示失败
	 */
	public static Integer createMenu(String jsonMenu) {
		int result = 0;
		String token = WeixinUtil.getToken();
		if (token != null) {
			// 拼装创建菜单的url
			String url = MENU_CREATE.replace("ACCESS_TOKEN", token);
			// 调用接口创建菜单
			JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", jsonMenu);

			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getInt("errcode");
					log.error("创建菜单失败 errcode:" + jsonObject.getInt("errcode")
							+ "，errmsg:" + jsonObject.getString("errmsg"));
				}
			}
		}
		return result;
	}

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @return 0表示成功，其他值表示失败
	 */
	public static Integer createMenu(Menu menu) {
		return createMenu(JSONObject.fromObject(menu).toString());
	}


	/**
	 * 查询菜单
	 * 
	 * @return 菜单结构json字符串
	 */
	public static JSONObject getMenuJson() {
		JSONObject result = null;
		String token = WeixinUtil.getToken();
		if (token != null) {
			String url = MENU_GET.replace("ACCESS_TOKEN", token);
			result = WeixinUtil.httpsRequest(url, "GET", null);
		}
		return result;
	}

	/**
	 * 查询菜单
	 * @return Menu 菜单对象
	 */
	public static Menu getMenu() {
		JSONObject json = getMenuJson().getJSONObject("menu");
		System.out.println(json);
		Menu menu = (Menu) JSONObject.toBean(json, Menu.class);
		return menu;
	}

	public static void main(String[] args) {
		
//		Button btn1 = new Button("商城", "view", null, "http://wap.koudaitong.com/v2/home/8m5g97v0", null);
//
//		Button sb3 = new Button("询价", "view", null, "http://121.40.249.61/xj.html", null);
//		Button sb4 = new Button("大客户采购", "view", null, "http://121.40.249.61/dkhcg.html", null);
//		Button sb5 = new Button("行情", "click", "hq", null, null);
//		Button btn2 = new Button("商机行情", null, null, null, new Button[] {
//				sb3, sb4,sb5 });
//
//
//		Button btn3 = new Button("我的订单", "view", null, "http://121.40.249.61/myorder.html", null);

        Button sb1 = new Button("家电维修","view",null,"http://hellomachine.cn/index.htm",null);
        Button sb2 = new Button("家电保养","view",null,"http://hellomachine.cn/index.htm",null);
        Button sb3 = new Button("家电回收","view",null,"http://hellomachine.cn/index.htm",null);
        Button btn1 = new Button("狸客服务", null, null, null, new Button[] {
                sb1, sb2,sb3 });

        Button sb4 = new Button("狸客入驻", "view", null, "http://hellomachine.cn/index.htm", null);
        Button sb5 = new Button("狸客服务", "view", null, "http://hellomachine.cn/index.htm", null);
        Button btn2 = new Button("工程师", null, null, null, new Button[] {
                sb4, sb5 });

        Button sb6 = new Button("我的订单", "view", null, "http://hellomachine.cn/page/client/14.htm", null);
        Button sb7 = new Button("个人信息", "view", null, "http://hellomachine.cn/page/client/25.htm", null);
        Button btn3 = new Button("个人中心", null, null, null, new Button[]{sb6,sb7});

		Menu menu = new Menu(new Button[] { btn1, btn2, btn3 });
		createMenu(menu);
		getMenu();
	}

}
